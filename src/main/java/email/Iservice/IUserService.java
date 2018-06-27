package email.Iservice;

import email.bean.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7 0007.
 */

public interface IUserService
{
	
	//管理员添加用户，成功返回ID，不成功返回0
    public int insert(User user);
    
    //管理员删除用户，成功返回ID，不成功返回0
    public int delete(int id);
    
    //更改用户权限，1是管理员，0是普通用户
    public void updatePower(int id,int power);
    
    //禁用/解禁用户，1是禁用，0是解禁
    public void updateFilter(int id,int filter); 
    
    //查询用户
    public User getById(int id);
    
    //查询用户
    public User getByName(String username);
    
    //用户登录，返回登录结果
    public boolean login(User user);
    
    //用户注册，返回注册结果
    public boolean register(User user);
    
    public List<User> getUsersPC(int id);
    
    public int countUser();

	boolean userlogin(User user);
}
