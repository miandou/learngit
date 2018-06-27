package email.service.impl;

import java.util.List;

import email.bean.Filter_Ip;
import emailService.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import email.Iservice.ISysService;
import email.bean.Email;
import email.bean.Sys_Parameter;
import email.bean.User;
import email.dao.EmailDao;
import email.dao.FilterIpDao;
import email.dao.SysParameterDao;
import email.dao.UserDao;

import javax.annotation.Resource;


@Service
public class SysService implements ISysService {

	@Autowired
    private SysParameterDao sysParameterDao;
	
	@Autowired
	private FilterIpDao filterIpDao;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private EmailDao emailDao;

	@Autowired
	private emailService.impl.EmailService emailService;
	
	@Override
	public Sys_Parameter getAll() {
		// TODO Auto-generated method stub
		Sys_Parameter sys=sysParameterDao.getAll();
		return sys;
	}

	@Override
	public void updateSMTPPort(int newPort) {
		// TODO Auto-generated method stub
		sysParameterDao.updateSMTPPort(newPort);
		emailService.setSMTPPort(newPort);
	}

	@Override
	public void updatePOP3Port(int newPort) {
		// TODO Auto-generated method stub
		sysParameterDao.updatePOP3Port(newPort);
	}

	@Override
	public void updateDomain(String newDomain) {
		// TODO Auto-generated method stub
		sysParameterDao.updateDomain(newDomain);
		emailService.setDomain(newDomain);
	}

	@Override
	public void updatePOP3LogSize(int newLogSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSMTPLogSize(int newLogSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSMTPState(boolean open) {
		// TODO Auto-generated method stub
		sysParameterDao.updateSMTPState(open);
		if(open) emailService.startSMTP();
		else emailService.stopSMTP();
	}

	@Override
	public void updatePOP3Sate(boolean open) {
		// TODO Auto-generated method stub
		sysParameterDao.updatePOP3Sate(open);
		if(open) emailService.startPOP3();
		else emailService.stopPOP3();
	}

	@Override
	public void updateSMTPLogLocation(String location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePOP3LogLocation(String location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmailSize(int size) {
		// TODO Auto-generated method stub
		sysParameterDao.updateEmailSize(size);
	}

	public Filter_Ip getFilterByIp(String ip)
	{
		return filterIpDao.getByIp(ip);
	}


	@Override
	public boolean addFilterIp(Filter_Ip filter_ip) {
		// TODO Auto-generated method stub
		if(filterIpDao.getByIp(filter_ip.getIp())==null) {
			filterIpDao.insert(filter_ip);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int countIp() {
		return filterIpDao.countIp();
	}

	@Override
	public List<Filter_Ip> getFilterIps(int page) {
		List<Filter_Ip> ips=filterIpDao.getFilterIps(page);
		return ips;
	}

	@Override
	public int delete(String ip) {
		filterIpDao.deleteByIp(ip);
		return 0;
	}

	@Override
	public void addFilterUserByUserName(String name) {
		// TODO Auto-generated method stub

	}

	//Ⱥ���ʼ�
	public void sendemail(String title,String content,int user_id) {
		// TODO Auto-generated method stub
		User user=userDao.getById(user_id);
		Sys_Parameter sys=sysParameterDao.getAll();
		String sender=user.getUsername()+"@"+sys.getName_domain();
		List<User> us=userDao.getAll();
		for(User u:us){
			Email entity=new Email();
			entity.setContent(content);
			entity.setRead_flag(false);
			entity.setTitle(title);
			entity.setSend_by(sender);
			entity.setUser_id(u.getId());
			emailDao.insert(entity);
		}
	}
}
