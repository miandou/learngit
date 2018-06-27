package email.dao;

import email.bean.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */
public interface LogDao
{
    public int insert(Log entity);
    public List<Log> getLogsByProtocol(int type,int id);
    public List<Log> getLogsByProtocolPageBySinceId(int type,int since_id,int number);
    public List<Log> getLogsByProtocolAndDate(int type,String date_from,String date_to,int id);
    public List<Log> getLogsByProtocolAndOperate(int protocol,int operate,int id);
    public void deleteById(int id);
    public void deleteAll(int protocol);
    public int countlog(int protocol);
}
