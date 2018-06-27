package email.controller;

import email.bean.Filter_Ip;
import email.dao.FilterIpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Controller
@RequestMapping("/filter")
public class FilterController

{
    @Autowired
    FilterIpDao filterIpDao;

    @RequestMapping("/go")
    public String go()
    {
        List<Filter_Ip> list=filterIpDao.getFilterIps(0);
        for(Filter_Ip ip:list)
        {
            System.out.println("ip: "+ip.getIp()+"  createUser: "+ip.getCreateUser().getUsername());
        }

        list=filterIpDao.getFilterIps(6);
        for(Filter_Ip ip:list)
        {
            System.out.println("ip: "+ip.getIp()+"  createUser: "+ip.getCreateUser().getUsername());
        }
        return "index";
    }
}
