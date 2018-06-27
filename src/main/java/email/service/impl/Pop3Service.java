package email.service.impl;


import email.bean.Email;
import email.bean.User;
import email.dao.EmailDao;
import email.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
@Service
@Transactional(readOnly = true)
public class Pop3Service
{

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailDao emailDao;


    //根据用户名找用户
    public User getUser(String username)
    {
        User user=userDao.getByUserName(username.substring(0,username.indexOf('@')));
        return user;
    }


    //返回某用户其邮件的数量
    public int countEmails(int user_id)
    {
        return emailDao.countByUserId(user_id);
    }

    public List<Email> getEmailsByUserId(int user_id)
    {
        return emailDao.getEmailsByUserId(user_id);
    }


    public Email getEmail(int user_id, int number)
    {
        return emailDao.getEmailByNumber(user_id,number-1);
    }

    @Transactional(readOnly = false)
    public boolean delete(int user_id,List<Integer> number)
    {
        for(Integer i:number)
        {
            emailDao.setDeleteFlag(user_id,i-1);
        }
        emailDao.deleteEmail(user_id);
        return false;
    }




       /*
    //某用户某邮件的byte
    public int countEmailByte(int user_id,int number)
    {
        return 0;
    }
*/

    /*
    //返回某用户所有邮件的byte之和
    public int countEmailsBytes(int user_id)
    {
        return 0;
    }
*/
}
