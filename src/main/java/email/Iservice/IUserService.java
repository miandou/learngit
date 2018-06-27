package email.Iservice;

import email.bean.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7 0007.
 */

public interface IUserService
{
	
	//����Ա����û����ɹ�����ID�����ɹ�����0
    public int insert(User user);
    
    //����Աɾ���û����ɹ�����ID�����ɹ�����0
    public int delete(int id);
    
    //�����û�Ȩ�ޣ�1�ǹ���Ա��0����ͨ�û�
    public void updatePower(int id,int power);
    
    //����/����û���1�ǽ��ã�0�ǽ��
    public void updateFilter(int id,int filter); 
    
    //��ѯ�û�
    public User getById(int id);
    
    //��ѯ�û�
    public User getByName(String username);
    
    //�û���¼�����ص�¼���
    public boolean login(User user);
    
    //�û�ע�ᣬ����ע����
    public boolean register(User user);
    
    public List<User> getUsersPC(int id);
    
    public int countUser();

	boolean userlogin(User user);
}
