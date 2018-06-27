package emailService.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import email.bean.*;
import email.service.impl.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import IEmailService.IEmailService;
import email.Iservice.ISysService;
import email.Iservice.IUserService;
import email.dao.UserDao;
import email.service.impl.SysService;

public class EmailService implements IEmailService {
	
	//????????????????????
	boolean isOpen;
	//???SMTP???????????
	private static boolean isSMTPOpen;
	//???POP3???????????
	boolean isPOP3Open;
	//SMPT???
	int pos_SMPT;
	//POP3???
	int pos_POP3;
	//??????????
	private static String domain="test.com";
	private Sys_Parameter sys;
	
	
	final Base64.Decoder decoder = Base64.getDecoder();
	final Base64.Encoder encoder = Base64.getEncoder();
	private static ExecutorService cachedThreadPool;
	private static ServerSocket serverSocket;
	private static List<Socket> sockets;

	ISysService sysService;

	private IUserService userService;

	private Pop3ServerSocket pop3ServerSocket;
	
	private email.Iservice.IEmailService emailService;

	private LogService logService;

	public EmailService(SysService sysService) {
		this.sysService=sysService;
		sys=this.sysService.getAll();
		domain=sys.getName_domain();
		if(sys.isFlag_smtp()){
			startSMTP();
		}
		else isSMTPOpen=false;

	}

	public Pop3ServerSocket getPop3ServerSocket() {
		return pop3ServerSocket;
	}

	public void setPop3ServerSocket(Pop3ServerSocket pop3ServerSocket) {
		this.pop3ServerSocket = pop3ServerSocket;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}




	private class SMTPService implements Runnable{

		private int port;
		
		public SMTPService(int port) {
			this.port=port;
		}
		
		@Override
		public void run()
		{
			try {
				serverSocket=new ServerSocket(port);
				cachedThreadPool=Executors.newCachedThreadPool();
				sockets=Collections.synchronizedList(new LinkedList<Socket>());
				Socket socket;
				while((socket=serverSocket.accept())!=null)
				{
					sockets.add(socket);
					Filter_Ip filter_ip=sysService.getFilterByIp(socket.getInetAddress().getHostAddress());
					if(filter_ip==null)
					{
						insertLog(socket,0);
						cachedThreadPool.execute(new SMTPHandler(socket));
					}
					else socket.close();

				}
			} catch (IOException e) {
				
			}
		}
		
	}
	
	private class SMTPHandler implements Runnable{

		Socket socket;
		
		public SMTPHandler(Socket socket) {
			this.socket=socket;
		}
		
		@Override
		public void run() {
			try {
				readAndHandler(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	@Override
	public HashSet<String> getFilterIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<String> getFilterName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFilterIp(String ip) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFilterIp(String ip) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFilterName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFilterName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startService() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopService() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized boolean startSMTP() {
		if(isSMTPOpen){
			return false;
		}
		new Thread(new SMTPService(sys.getPort_smtp())).start();
		isSMTPOpen=true;
		return true;
	}
	
	//
	private void readAndHandler(Socket socket) throws IOException{
		int last_state=-1;
		BufferedReader is=null;
		PrintWriter os=null;
		try {
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os=new PrintWriter(socket.getOutputStream());
			String username="";
			String recv_account = "";
			os.write("220 welcome to test.com Email System\r\n");
			os.flush();
			String Line;
			while(true){
				Line=is.readLine();
				String[] result=Line.split("\\s+",2);
				String re="";
				int state=handlerCommand(result);
				if(state==QUIT){
					re=BYE+" "+"Bye"+"\r\n";
					os.write(re);
					os.flush();
					insertLog(socket,5,username);
					break;
				}
				if(state==ERROR_NOIMPLEMENTS){
					re=ERROR_NOIMPLEMENTS+" "+"Command No Implements"+"\r\n";
					os.write(re);
					os.flush();
					continue;
				}
				if(state==ERROR_PARAMETERFORMAT){
					re=ERROR_PARAMETERFORMAT+" "+"Request for parameter"+"\r\n";
					os.write(re);
					os.flush();
					continue;
				}
				switch (last_state) {
				case -1:
					if(state==HELO_EHLO){
						re=OK+" "+"OK"+"\r\n";
						last_state=HELO_EHLO;
					}
					else if(state==EHLO)
					{
						re=OK+"-"+"OK"+"\r\n";
						re+="250-smtp.toufudragon.top"+"\r\n";
						re+="250-AUTH LOGIN PLAIN"+"\r\n";
						re+="250-AUTH=LOGIN PLAIN"+"\r\n";
						re+="250-PIPELINING"+"\r\n";
						re+="250-SIZE 10485760"+"\r\n";
						re+="250 8BITMIME"+"\r\n";
						last_state=HELO_EHLO;
					}
					else{
						re=ERROR_EXCUTE+" "+"send HELO/EHLO first"+"\r\n";
					}
					break;
				case 0:
					if(state==LOGIN){
						re=WAIT_MORE+" "+"VXNlcm5hbWU6"+"\r\n";
						os.write(re);
						os.flush();
						Line=is.readLine();
						String account=handleUserName(Line);
						re=WAIT_MORE+" "+"UGFzc3dvcmQ6"+"\r\n";
						os.write(re);
						os.flush();
						Line=is.readLine();
						String pwd=new String(decoder.decode(Line));
						User user=new User();
						user.setUsername(account);
						user.setPassword(pwd);
						if(account!=null&&pwd!=null&&userService.login(user)){
							username=account;
							last_state=1;
							re=OK_LOGIN+" "+"Login successful"+"\r\n";
							insertLog(socket,1,username);
						}
						else{
							re=ERROR_LOGIN+" "+"Login failed"+"\r\n";
						}
					}
					else{
						re=ERROR_EXCUTE+" "+"Please login"+"\r\n";
					}
					break;
				case 1:
					if(state==MAIL_FROM){
						String[] info=result[1].split("@", 2);
						if(info.length==2&&info[1].equals(domain)&&info[0].equals(username)){
							re=OK+" "+"Mail"+" "+"OK"+"\r\n";
							os.write(re);
							os.flush();
							Line=is.readLine();
							String[] now_result=Line.split("\\s+",2);
							state=handlerCommand(now_result);
							if(state==MAIL_TO){
								String[] recv_info=now_result[1].split("@",2);
								if(recv_info.length==2&&recv_info[1].equals(domain)){
									recv_account=recv_info[0];
									re=OK+" "+"Mail"+" "+"OK"+"\r\n";
									last_state=2;
								}
								else{
									re=ERROR_EXCUTE+" "+"Excute error"+"\r\n";
								}
							}
							else{
								re=ERROR_EXCUTE+" "+"Excute error"+"\r\n";
							}
						}
						else{
							re=ERROR_BAD+" "+"Use yourself account"+"\r\n";
						}
					}
					else{
						re=ERROR_EXCUTE+" "+"Excute error"+"\r\n";
					}
					break;
				case 2:
					if(state==DATA){
						re=354+" "+"End data with <CR><LF>.<CR><LF>"+"\r\n";
						os.write(re);
						os.flush();
						String subject="";
						String context="";
						int contextState=0;
						while(!(Line=is.readLine()).equals(".")){
							if(contextState==0){
								String[] info=Line.split(":", 2);
								if(info[0].equals("from")){
									contextState=1;
									continue;
								}
								else{
									contextState=4;
								}
							}
							if(contextState==1){
								String[] info=Line.split(":", 2);
								if(info[0].equals("to")){
									contextState=2;
									continue;
								}
								else{
									contextState=4;
								}
							}
							if(contextState==2){
								String[] info=Line.split(":", 2);
								if(info[0].equals("subject")&&info.length==2){
									contextState=3;
									subject=info[1];
									continue;
								}
								else{
									contextState=4;
								}
							}
							if(contextState==3){
								contextState=4;
								if(Line.equals("")){
									continue;
								}
							}
							context+=Line+"\r\n";
						}
						Email email=new Email();
						User sendUser=userService.getByName(username);
						User recvUser=userService.getByName(recv_account);
						if(sendUser==null||recvUser==null){
							re=ERROR_BAD+"Mail failed!"+"\r\n";
						}
						else{
							email.setSend_by(sendUser.getUsername()+"@"+domain);
							email.setUser_id(recvUser.getId());
							email.setContent(context);
							email.setTitle(subject);
							emailService.sendEmail(email);
							re=OK+"Mail OK"+"\r\n";
							insertLog(socket,2,username,recvUser.getUsername());
						}
						last_state=1;
					}
				}
				os.write(re);
				os.flush();
			}
			is.close();
			os.close();
			socket.close();
		} catch (Exception e) {
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.write("Sorry,Server closed!\r\n");
				os.flush();
				os.close();
			}
			if(socket!=null&&!socket.isClosed()){
				socket.close();
			}
			sockets.remove(socket);
			e.printStackTrace();
		}
	}
	
	//????????????????????????????????????????????????????????????????????????????????
		private int handlerCommand(String result[]) {
			int re=ERROR_NOIMPLEMENTS;
			String args1=result[0].toLowerCase();
			if(result.length==1){
				if(args1.equals("data")){
					re=DATA;
				}
				else if(args1.equals("")){
					re=CRLF;
				}
				else if(args1.equals(".")){
					re=DOT;
				}
				else if(args1.equals("quit")){
					re=QUIT;
				}
				else if(args1.equals("helo")||args1.equals("ehlo")||args1.equals("mail")||args1.equals("auth")){
					re=ERROR_PARAMETERFORMAT;
				}
			}
			else if(result.length==2){
				String args2=result[1].toLowerCase();
				if((args1.equals("helo") || args1.equals("ehlo"))){
					if(!args2.equals("")){
						re=HELO_EHLO;
						if(args1.equals("ehlo")) re=EHLO;
					}
					else
						re=ERROR_BAD;
				}
				else if(args1.equals("auth")){
					if(args2.equals("login")){
						re=LOGIN;
					}
					else
						re=ERROR_BAD;
				}
				else if(args1.equals("mail")){
					if(args2.matches("from:\\s*<[^<>]+>")){
						int index=args2.indexOf("<");
						result[1]=result[1].substring(index+1, result[1].length()-1);
						re=MAIL_FROM;
					}
					else
						re=ERROR_BAD;
				}
				else if(args1.equals("rcpt")){
					if(args2.matches("to:\\s*<[^<>]+>")){
						int index=args2.indexOf("<");
						result[1]=result[1].substring(index+1, result[1].length()-1);
						re=MAIL_TO;
					}
					else
						re=ERROR_BAD;
				}
				else if(args1.equals("subject:")){
					re=SUBJECT;
				}
			}
			return re;
		}
		
		//?????????Base64?????????????????????????????????????????????????null
		private String handleUserName(String base64) {
			String result=null;
			String totalName=new String(decoder.decode(base64));
			String[] info=totalName.split("@", 2);
			if(info.length>1&&info[1].equals(domain)){
				result=info[0];
			}
			return result;
		}

	@Override
	public synchronized boolean stopSMTP() {
		if(!isSMTPOpen){
			return false;
		}
		try {
			serverSocket.close();
			isSMTPOpen=false;
			Iterator<Socket> it=sockets.listIterator();
			while(it.hasNext()){
				it.next().close();
				it.remove();
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean startPOP3() {
		return pop3ServerSocket.open();
	}

	@Override
	public boolean stopPOP3() {
		try
		{
			pop3ServerSocket.close();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean setSMTPPort(int port) {
		try
		{
			sys.setPort_pop3(port);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean setPOP3Port(int port) {

		return false;
	}

	@Override
	public boolean setDomain(String domain) {
		try
		{
			this.domain=domain;
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public email.Iservice.IEmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(email.Iservice.IEmailService emailService) {
		this.emailService = emailService;
	}


	private void insertLog(Socket socket,int oprType,String...args)
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
		else if(oprType==2)
		{
			log.setContent("发送邮件给"+args[1]);
			log.setOperate_type(2);
		}
		else if(oprType==5)
		{
			log.setContent("与邮件服务器断开连接");
			log.setOperate_type(5);
		}

		if(oprType>0)	log.setUsername(args[0]);
		else log.setUsername("");
		log.setIp(socket.getInetAddress().getHostAddress());
		log.setProtocol_type(0);
		logService.insert(log);
	}

}
