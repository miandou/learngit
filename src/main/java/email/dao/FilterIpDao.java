package email.dao;

import email.bean.Filter_Ip;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
public interface FilterIpDao
{
    public int insert(Filter_Ip entity);

    public void deleteByIp(String ip);

    public int update(String oldIp,String newIp);

    public Filter_Ip getByIp(String ip);

    //page size is 6
    public List<Filter_Ip > getFilterIps(int page);

    public int countIp();
}
