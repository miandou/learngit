package email.service.impl;

import email.Iservice.IUserService;
import email.bean.User;
import email.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
//注解声明ServiceBean
@Service
public class UserService implements IUserService
{

    @Autowired
    private UserDao userDao;


    @Override
    public int insert(User user) {
        user.setDisable_flag(false);
        user.setIsfilter(false);
        userDao.insert(user);
        return user.getId();
    }


	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
		return 0;
	}

	public void updatePassword(int user_id, String password) {
		// TODO Auto-generated method stub
		userDao.updatePassword(user_id, password);
	}


	@Override
	public void updatePower(int id, int power) {
		// TODO Auto-generated method stub
		userDao.upToAdmin(id);
	}


	@Override
	public void updateFilter(int id, int filter) {
		// TODO Auto-generated method stub
		userDao.updateDisable(id,filter);
	}

	public void updateDisable(int id, int filter) {
		// TODO Auto-generated method stub
		userDao.updateFilter(id, filter);;
	}
	
	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getByName(String username) {
		// TODO Auto-generated method stub
		User user=userDao.getByUserName(username);
		return user;
	}


	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		User u=new User();
		if(userDao.getByUserName(user.getUsername())!=null){
			u=userDao.getByUserName(user.getUsername());
			if(u.getPassword().equals(user.getPassword())&&u.getPower()==1&&!u.isDisable_flag()){
				return true;
			}
		}		
		return false;
	}
	
	@Override
	public boolean userlogin(User user) {
		// TODO Auto-generated method stub
		User u=new User();
		if(userDao.getByUserName(user.getUsername())!=null){
			u=userDao.getByUserName(user.getUsername());
			if(u.getPassword().equals(user.getPassword())&&!u.isDisable_flag()){
				return true;
			}
		}		
		return false;
	}

	@Override
	public List<User> getUsersPC(int id) {
		// TODO Auto-generated method stub
		List<User> users=userDao.getUsersPC(id);
		return users;
	}
	
	@Override
	public int countUser(){
		return userDao.countUser();
	}
}
