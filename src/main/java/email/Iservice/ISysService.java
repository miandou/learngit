package email.Iservice;

import java.util.List;

import email.bean.Filter_Ip;
import email.bean.Sys_Parameter;

public interface ISysService {

	
public Sys_Parameter getAll();
    
    public void updateSMTPPort(int newPort);
    
    public void updatePOP3Port(int newPort);
    
    public void updateDomain(String newDomain);
    
    public void updatePOP3LogSize(int newLogSize);
    
    public void updateSMTPLogSize(int newLogSize);
    
    public void updateSMTPState(boolean open);
    
    public void updatePOP3Sate(boolean open);
    
    public void updateSMTPLogLocation(String location);
    
    public void updatePOP3LogLocation(String location);
    
    public void updateEmailSize(int size);
    
    //public void addFilterIp(String ip);
    
    public void addFilterUserByUserName(String name);

   //	public void addFilterIp(String ip, int user_id);

	public boolean addFilterIp(Filter_Ip filter_ip);

	public int countIp();

	public List<Filter_Ip> getFilterIps(int page);

	public int delete(String ip);

    public Filter_Ip getFilterByIp(String ip);
}
