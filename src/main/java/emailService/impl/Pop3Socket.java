package emailService.impl;

import email.bean.Email;
import email.bean.Log;
import email.bean.User;
import email.service.impl.LogService;
import email.service.impl.Pop3Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class Pop3Socket extends Thread
{
    //各个状态
    private static final int INIT=0;
    private static final int USER=1;
    private static final int PASS_SUCCESS=2;
    private static final String ERROR_UNKNOWN_MESSAGE="-ERR Unknown command ";
    private static final String FAIL_LOGIN_MESSAGE="-ERR Fail To Login";
    private static final String OK_MESSAGE="+OK";
    private static final String WELCOME_MESSAGE="+OK Welcome to Mail Pop3 Server";
    private static String DOMAIN;//域名
    private static final String hh="\r\n";

    private Pop3Service pop3Service;
    private LogService logService;


    private List<Integer> listDeleteFlag=new LinkedList<>();
    private int state=INIT;
    private User user=new User();
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintStream ps;
    public Pop3Socket(Socket s,Pop3Service pop3,LogService logService)
    {
        this.logService=logService;
        pop3Service=pop3;
        socket=s;
        try {
            socket.setSoTimeout(30000);
            ps=new PrintStream(socket.getOutputStream());
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        ps.println(WELCOME_MESSAGE);
        insertLog(socket,0,0);
        String content=null;
        while((content=readFromClient())!=null)
        {
            String temp=content.toLowerCase();
            if(temp.equals("quit"))
            {
                closeSocket();
            }
            else if(state==INIT&&temp.startsWith("user "))
            {
                handleINITState(content);
            }
            else if(state==USER&&temp.startsWith("pass "))
            {
                handleUSERState(content);
            }
            else if(state==PASS_SUCCESS)
            {
                handlePASSState(content);
            }
            else
            {
                errorMessage(content);
            }
        }
    }

    private void handleINITState(String str)
    {
        String[] strList=splitString(str);
        if(strList[0].equals("user")&&!strList[1].equals("")&&strList[1].endsWith("@"+DOMAIN))
        {
            state=USER;
            user.setUsername(strList[1]);
            ps.println(OK_MESSAGE);
        }
        else errorMessage(str);
    }

    private void handleUSERState(String str)
    {
        String[] strList=splitString(str);
        if(strList[0].equals("pass")&&!strList[1].equals(""))
        {
            user.setPassword(strList[1]);
            User userValid=pop3Service.getUser(user.getUsername());
            if(userValid!=null&&user.getUsername().equals(userValid.getUsername()+"@"+DOMAIN)
                    &&user.getPassword().equals(userValid.getPassword()))
            {
                state=PASS_SUCCESS;
                user=userValid;
                insertLog(socket,1,0);
                STAT();
            }
            else
            {
                state=INIT;
                user=new User();
                ps.println(FAIL_LOGIN_MESSAGE);
            }
        }
        else
        {
            user=new User();
            state=INIT;
            errorMessage(str);
        }

    }

    private void handlePASSState(String str)
    {
        String temp=str.toLowerCase();
        if(temp.equals("stat"))
        {
            STAT();
            return;
        }
        else if(temp.equals("noop"))
        {
            ps.println(OK_MESSAGE);
            return ;
        }
        else if(temp.equals("rset"))
        {
            listDeleteFlag.clear();
            ps.println(OK_MESSAGE);
            return;
        }

        try
        {
            if(temp.equals("list"))
            {
                LISTALL();
                return;
            }
            if(temp.equals("uidl"))
            {
                UIDLALL();
                return;
            }
            if(temp.startsWith("top"))
            {
                String[] strList=temp.split(" ");
                try{
                    TOP(Integer.parseInt(strList[1]),Integer.parseInt(strList[2]));
                }
                catch (Exception ex)
                {
                    ps.println(ERROR_UNKNOWN_MESSAGE);
                    return ;
                }
                return;
            }
            String[] strList=splitString(temp);
            if(strList==null||strList[1].equals("")&&!temp.equals("list")) {
                errorMessage(str);
                return;
            }
            if(strList[0].equals("list"))
            {
                 LIST(Integer.parseInt(strList[1]));
            }
            else if(strList[0].equals("uidl"))
            {
                UIDL(Integer.parseInt(strList[1]));
            }
            else if(strList[0].equals("retr"))
            {
                RETR(Integer.parseInt(strList[1]));
                insertLog(socket,3,Integer.parseInt(strList[1]));
            }
            else if(strList[0].equals("dele"))
            {
                DELE(strList);
            }
            else errorMessage(str);
        } catch (Exception e)
        {
            errorMessage(str);
        }

    }



    private void DELE(String[] strList)
    {
        int number=Integer.parseInt(strList[1]);
        int cnt=pop3Service.countEmails(user.getId());
        if(number>cnt)
        {
            errorMessage("-ERR Unknown Message");
        }
        else
        {
            listDeleteFlag.add(number);
            ps.println(OK_MESSAGE);
        }
    }

    //用户退出回话
    private void closeSocket()
    {
        ps.println(OK_MESSAGE);
        insertLog(socket,5,0);
        //如果已登录，可能要删除某些邮件
        if(state==PASS_SUCCESS)
        {
            if(listDeleteFlag.size()>0)
            {
                pop3Service.delete(user.getId(),listDeleteFlag);
                for(int i:listDeleteFlag)
                {
                    insertLog(socket,4,i);
                }

            }
        }
        releaseSocket();
        Pop3ServerSocket.removeSocket(this);
    }

    public void releaseSocket()
    {
        try {
            socket.close();
            bufferedReader.close();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取用户的输入
    private String readFromClient()
    {
        try
        {
            return bufferedReader.readLine();
        } catch (IOException e) {
            closeSocket();
        }
        return null;
    }

    //输出错误信息
    private void errorMessage(String str)
    {
        String temp=str.toLowerCase();
        ps.println(ERROR_UNKNOWN_MESSAGE+str);
        if(state==USER) state=INIT;
    }

    //分割用户的输入，用户密码可能包含空格
    private String[] splitString(String content)
    {
        String[] strList=new String[2];
        try
        {
            strList[0]=content.substring(0,content.indexOf(' '));
            strList[1]=content.substring(content.indexOf(' ')+1,content.length());
            strList[0]=strList[0].toLowerCase();
            return strList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static void setDOMAIN(String str)
    {
        DOMAIN=str;
    }

    private String getEmailString(Email email)
    {
        String str=new String();
/*
        str+="To: "+email.getOwner().getUsername()+"@"+DOMAIN+hh;
        str+="From: "+email.getSend_by()+hh;
        str+="Subject: "+email.getTitle()+hh;
        str+="Date: "+email.getSend_date()+hh;
        str+="Sender: "+email.getSend_by()+hh;
        str+=hh;*/
        str+=email.getContent()+hh+"."+hh;
        return str;
    }

    private void STAT()
    {
        List<Email> list=pop3Service.getEmailsByUserId(user.getId());
        int cnt=0;
        for(Email email:list)
        {
            String str=getEmailString(email);
            cnt+=str.getBytes().length;
        }
        ps.println(OK_MESSAGE+" "+list.size()+" "+cnt+"Bytes");
    }


    private void UIDLALL()
    {
        List<Email> list=pop3Service.getEmailsByUserId(user.getId());
        int sum=0;
        for(Email email:list)
        {
            String str=getEmailString(email);
            sum+=str.getBytes().length;
        }
        ps.println(OK_MESSAGE+" "+list.size()+" "+sum);
        for(int i=0;i<list.size();i++)
        {
            ps.println((i+1)+" "+list.get(i).getId());
        }
        ps.println(".");
    }


    private void LISTALL()
    {
        List<Email> list=pop3Service.getEmailsByUserId(user.getId());
        int sum=0;
        for(Email email:list)
        {
            String str=getEmailString(email);
            sum+=str.getBytes().length;
        }
        ps.println(OK_MESSAGE+" "+list.size()+" "+sum);
        for(int i=0;i<list.size();i++)
        {
            String str=getEmailString(list.get(i));
            int a=str.getBytes().length;
            ps.println((i+1)+" "+a);
        }
        ps.println(".");
    }

    private void LIST(int number)
    {
        Email email=pop3Service.getEmail(user.getId(),number);
        String str=getEmailString(email);
        ps.println(OK_MESSAGE+" "+number+" "+str.getBytes().length+"Bytes");
    }

    private void UIDL(int number)
    {
        Email email=pop3Service.getEmail(user.getId(),number);
        ps.println(OK_MESSAGE+" "+number+" "+email.getId());
    }

    private void TOP(int number,int line)
    {
        Email email=pop3Service.getEmail(user.getId(),number);
        //邮件头
        String str=new String();
        String content=email.getContent();
        String[] contents=content.split("\n");
        str+=getEmailHeader(contents,"Subject")+hh;
        str+=getEmailHeader(contents,"Date")+hh;
        str+=getEmailHeader(contents,"From")+hh;
        str+=getEmailHeader(contents,"To");
        ps.println("+OK");
        ps.println(str);
/*
        //邮件前n行
        if(line>0)
        {
            StringBuilder stringBuilder=new StringBuilder();
            String content=email.getContent();
            int cnt=0;
            for(int i=0;i<content.length();i++)
            {
                if(cnt==line) break;
                char a=content.charAt(i);
                stringBuilder.append(a);
                if(a=='\n') cnt++;
            }
            ps.println(stringBuilder.toString());
        }*/
        ps.println(".");
    }


    private String getEmailHeader(String[] content,String head)
    {
        for(int i=0;i<content.length;i++)
        {
            if(content[i].startsWith(head)) return content[i];
        }
        return head;
    }

    private void RETR(int number)
    {
        Email email=pop3Service.getEmail(user.getId(),number);
        String str=getEmailString(email);
        ps.println(OK_MESSAGE+hh);
        ps.println(str);
    }

    private void insertLog(Socket socket,int oprType,int target)
    {
        Log log=new Log();
        if(oprType==0)
        {
            log.setContent("连接到邮件服务器");
            log.setOperate_type(0);
        }
        else if(oprType==1)
        {
            log.setContent("登录到邮件服务器");
            log.setOperate_type(1);
        }
        else if(oprType==3)
        {
            log.setContent("查看了第"+target+"封邮件");
            log.setOperate_type(3);
        }
        else if(oprType==4)
        {
            log.setContent("删除了第"+target+"封邮件");
            log.setOperate_type(4);
        }
        else if(oprType==5)
        {
            log.setContent("与邮件服务器断开连接");
            log.setOperate_type(5);
        }
        log.setUsername(user.getUsername());
        log.setIp(socket.getInetAddress().getHostAddress());
        log.setProtocol_type(1);
        System.out.println("连接到邮件服务器");
        logService.insert(log);
    }

}
