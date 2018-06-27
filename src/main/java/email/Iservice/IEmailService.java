package email.Iservice;

import email.bean.Email;

public interface IEmailService {

	
	public int sendEmail(Email email);
	
	public void deleteEmal(int email_id);
	
	//删除用户最老的那封邮件
	public void deleteOldestEmail(int user_id);
	
	//删除用户最老的n封邮件
	public void deleteOldestEmails(int user_id,int number);
	
	public void getEmailById(int email_id);
	
	//将所有的邮件都标记为已阅读
	public void setAllReadState();
	
	
}
