package email.Iservice;

import email.bean.Email;

public interface IEmailService {

	
	public int sendEmail(Email email);
	
	public void deleteEmal(int email_id);
	
	//ɾ���û����ϵ��Ƿ��ʼ�
	public void deleteOldestEmail(int user_id);
	
	//ɾ���û����ϵ�n���ʼ�
	public void deleteOldestEmails(int user_id,int number);
	
	public void getEmailById(int email_id);
	
	//�����е��ʼ������Ϊ���Ķ�
	public void setAllReadState();
	
	
}
