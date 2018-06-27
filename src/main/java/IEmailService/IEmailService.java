package IEmailService;

import java.util.HashSet;

public interface IEmailService {
	
	//����ִ�гɹ�
		static int OK=250;
		//��֤�ɹ�
		static int OK_LOGIN=235;
		//Bye
		static int BYE=221;
		
		//�ȴ��û�������֤��Ϣ
		static int WAIT_MORE=334;
		
		//�������
		static int ERROR_NOIMPLEMENTS=502;
		//������ʽ����
		static int ERROR_PARAMETERFORMAT=501;
		//��������
		static int ERROR_PARAMETER=504;
		//��֤ʧ��
		static int ERROR_LOGIN=535;
		//ִ��ʧ��
		static int ERROR_EXCUTE=503;
		//�﷨����
		static int ERROR_BAD=500;
		
		//ָ���ʶ
		static int HELO_EHLO=0;
		static int EHLO=99;//
		static int LOGIN=1;
		static int MAIL_FROM=2;
		static int MAIL_TO=3;
		static int DATA=4;
		static int SUBJECT=5;
		static int CRLF=6;
		static int DOT=7;
		static int QUIT=8;
	
	//��ý��õ�IP����
	HashSet<String> getFilterIp();
	
	//��ý��õ��û�username����
	HashSet<String> getFilterName();
	
	//��ӽ��õ�IP��������ӽ�����Ѵ��ڻ���IP��ʽ�����ʧ�ܣ�
	boolean addFilterIp(String ip);
	
	//ɾ�����õ�IP������ɾ���Ľ��
	boolean deleteFilterIp(String ip);
	
	//��ӽ��õ�username��������ӽ�����ѽ��õĻ��߲����ڵ�username��ʧ�ܣ�
	boolean addFilterName(String name);
	
	//ɾ�����õ�username������ɾ�����
	boolean deleteFilterName(String name);
	
	//�����ʼ������������ؿ������
	boolean startService();
	
	//�ر��ʼ������������عرս��
	boolean stopService();
	
	//����SMTP�����ؿ������
	boolean startSMTP();
	
	//�ر�SMTP,���عرս��
	boolean stopSMTP();
	
	//����POP3�����ؿ������
	boolean startPOP3();
	
	//�ر�POP3,���عرս��
	boolean stopPOP3();
	
	//����SMTP�˿ڣ����ظ��Ľ������ֻ���ڹر���SMTPЭ����߷�����֮����ܸ��ģ�
	boolean setSMTPPort(int port);
	
	//����POP3�˿ڣ����ظ��Ľ������ֻ���ڹر���POP3Э����߷�����֮����ܸ��ģ�
	boolean setPOP3Port(int port);
	
	//�������������ظ��Ľ������ֻ���ڹر��˷�����֮����ܸ��ģ�����һ�����ƣ�
	boolean setDomain(String domain);
	
	
	
}
