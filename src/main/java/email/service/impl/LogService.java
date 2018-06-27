package email.service.impl;

import email.bean.Log;
import email.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */
@Service
@Transactional(readOnly = true)
public class LogService
{
    @Autowired
    private LogDao logDao;

    @Transactional(readOnly = false)
    public int insert(Log log)
    {
        return logDao.insert(log);
    }

    public List<Log> getLogsByProtocolPageBySinceId(int type,int since_id,int number)
    {
        return logDao.getLogsByProtocolPageBySinceId(type,since_id,number);
    }

    public List<Log> getLogsByProtocol(int type,int id)
    {
        return logDao.getLogsByProtocol(type,id);
    }

    public List<Log> getLogsByProtocolAndDate(int type,String date_from,String date_to,int id)
    {
        return logDao.getLogsByProtocolAndDate(type,date_from,date_to,id);
    }

    public List<Log> getLogsByProtocolAndOperate(int protocol,int operate,int id)
    {
        return logDao.getLogsByProtocolAndOperate(protocol,operate,id);
    }

    @Transactional(readOnly = false)
    public void deleteById(int id)
    {
        logDao.deleteById(id);
    }

    @Transactional(readOnly = false)
    public void deleteList(List<Integer> list)
    {
        for(Integer i:list)
        {
            logDao.deleteById(i);
        }
    }

    @Transactional(readOnly = false)
    public void deleteAll(int protocol)
    {
        logDao.deleteAll(protocol);
    }

    public int countlog(int protocol)
    {
        int count=logDao.countlog(protocol);
    	return count;
    }
}
