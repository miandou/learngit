package email.service.impl;

import java.util.HashSet;

import email.bean.Email;
import org.springframework.beans.factory.annotation.Autowired;

import email.Iservice.IEmailService;
import email.dao.EmailDao;
import email.dao.FilterIpDao;
import email.dao.SysParameterDao;
import email.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

	@Autowired
    private SysParameterDao sysParameterDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailDao emailDao;


	@Override
	public int sendEmail(Email email) {
		return emailDao.insert(email);
	}

	@Override
	public void deleteEmal(int email_id) {

	}

	@Override
	public void deleteOldestEmail(int user_id) {

	}

	@Override
	public void deleteOldestEmails(int user_id, int number) {

	}

	@Override
	public void getEmailById(int email_id) {
		
	}

	@Override
	public void setAllReadState() {

	}
	
	public String getState(String send_by,String time) {
		return ""+emailDao.getState(send_by, time);
	}
}
