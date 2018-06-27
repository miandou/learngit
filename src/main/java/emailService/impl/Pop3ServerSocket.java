package emailService.impl;

import email.bean.Filter_Ip;
import email.bean.Sys_Parameter;
import email.service.impl.LogService;
import email.service.impl.Pop3Service;
import email.service.impl.SysService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class Pop3ServerSocket
{
    private static ServerSocket serverSocket;
    private static Sys_Parameter sysParameter;
    private static List<Pop3Socket> socketList= Collections.synchronizedList(new LinkedList<Pop3Socket>());
    private static Pop3Thread pop3Thread=null;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    private class Pop3Thread extends  Thread
    {
        @Override
        public void run()
        {
            try
            {
                serverSocket=new ServerSocket(sysParameter.getPort_pop3());
                while(true)
                {
                    Socket socket=serverSocket.accept();
                    Filter_Ip filter_ip=sysService.getFilterByIp(socket.getInetAddress().getHostAddress());
                    if(filter_ip==null)
                    {
                        Pop3Socket pop3Socket=new Pop3Socket(socket,pop3Service,logService);
                        pop3Socket.start();
                        socketList.add(pop3Socket);
                    }
                    else socket.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private SysService sysService;
    private Pop3Service pop3Service;
    private LogService logService;

    public Pop3ServerSocket(SysService sysService,Pop3Service pop3Service)
    {
        this.sysService=sysService;
        this.pop3Service=pop3Service;
        open();
    }

    private void setParameter()
    {
        sysParameter=this.sysService.getAll();
        Pop3Socket.setDOMAIN(sysParameter.getName_domain());
    }


    public static void removeSocket(Pop3Socket pop3Socket)
    {
        if(pop3Socket!=null)
        {
            pop3Socket.releaseSocket();
            socketList.remove(pop3Socket);
        }
    }

  public void close()
  {
      try {
          serverSocket.close();
          for(Pop3Socket pop3Socket:socketList)
          {
              pop3Socket.releaseSocket();
          }
          socketList.clear();
      } catch (IOException e) {
          e.printStackTrace();
      }
      pop3Thread=null;
  }

  public boolean open()
  {
      if(pop3Thread!=null) return false;
      setParameter();
      if(sysParameter.isFlag_pop3())
      {
          (pop3Thread=new Pop3Thread()).start();
      }
      return true;
  }
}
