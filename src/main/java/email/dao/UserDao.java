package email.dao;

import email.bean.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
public interface UserDao
{
    public int insert(User entity);

    public int deleteById(int id);

    public List<User> getUsers(int since_id,int number);

    public User getById(int id);
    
    public User getByUserName(String username);
    
    public void upToAdmin(int user_id);
    
    public void updateDisable(int user_id,int disable_flag);
    
    public void updateFilter(int user_id,int filter);

    public void updatePassword(int user_id,String password);

    public void updateNickName(int user_id,String nickName);
    
    public List<User> getUsersPC(int id);
    
    public int countUser();
    
    public List<User> getAll();
}
