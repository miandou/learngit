package email.dao;

import email.bean.Sys_Parameter;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
public interface SysParameterDao
{
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
}
