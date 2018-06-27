package email.dao;

import email.bean.Email;
import email.bean.Email_File;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
public interface EmailDao
{
    public int insert(Email entity);
    public int insertEmailFile(Email_File email_file);

    public void deleteById(int email_id);
    
    //ɾ���û����ϵ��Ƿ��ʼ�
    public void deleteOldestEmail(int user_id);
    
    //ɾ���û����ϵ�n���ʼ�
  	public void deleteOldestEmails(int user_id,int number);
    
    public void updateReadFlag(int email_id);
    public void updateAllReadFlag(int user_id);
    public List<Email> getEmails(int user_id,int since_id,int number);
    public Email getById(int email_id);


    public int countByUserId(int user_id);

    public Email getEmailByNumber(int user_id,int number);

    public List<Email> getEmailsByUserId(int user_id);

    public int setDeleteFlag(int user_id,int number);

    public void deleteEmail(int user_id);
    
    public int getState(@Param("send_by") String send_by, @Param("time")String time);

}
