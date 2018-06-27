package email.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import email.bean.Filter_Ip;
import email.bean.Sys_Parameter;
import email.bean.User;
import email.service.impl.SysService;

@Controller
@RequestMapping("/setting")
public class SettingController
{
    @Autowired
    private SysService sysService;
    
    //setSMTPPort
    @RequestMapping("/SMTPport")
    public String setSMTPPort(Sys_Parameter sys_parameter,Model model)
    {    
    	Sys_Parameter temp = sysService.getAll();
    	
    	if(temp.isFlag_smtp()) {
    		model.addAttribute("flag_smtp", "0");
    	}
    	else
    	{
    		sysService.updateSMTPPort(sys_parameter.getPort_smtp());
    		model.addAttribute("flag_smtp", "1");
    	}
    	
    	setModel(model,sys_parameter);
    	
    	return "setting/SetParam";
    }
    
    //setPOP3Port
    @RequestMapping("/POP3port")
    public String setPOP3Port(Sys_Parameter sys_parameter,Model model)
    {
    	Sys_Parameter temp = sysService.getAll();
    	
    	if(temp.isFlag_pop3()) {
    		model.addAttribute("flag_pop3", "0");
    	}
    	else
    	{
    		sysService.updatePOP3Port(sys_parameter.getPort_pop3());
    		model.addAttribute("flag_pop3", "1");
    	}
    	
    	setModel(model,sys_parameter);

    	return "setting/SetParam";
    }
    
    //setDomainName
    @RequestMapping("/DomainName")
    public String setDomainName(Sys_Parameter sys_parameter,Model model)
    {
    	Sys_Parameter temp = sysService.getAll();
    	
    	if(temp.isFlag_smtp()||temp.isFlag_pop3()) {
    		model.addAttribute("flag", "0");
    	}
    	else
    	{
    		sysService.updateDomain(sys_parameter.getName_domain());
    		model.addAttribute("flag", "1");
    	}
    	
    	setModel(model,sys_parameter);
    	
    	return "setting/SetParam";
    }
    
    //getSysParam
    @RequestMapping("/getSysParam")
    public String getSysParam(Model model,Sys_Parameter sys_parameter)
    {
    	//Sys_Parameter sys_parameter =sysService.getAll();
    	setModel(model,sys_parameter);
    	
    	return "setting/SetParam";
    }
    
    //setModel
    private void setModel(Model model,Sys_Parameter sys_parameter)
    {
    	sys_parameter =sysService.getAll();
    	model.addAttribute("smtpPort", sys_parameter.getPort_smtp());
    	model.addAttribute("pop3Port", sys_parameter.getPort_pop3());
    	model.addAttribute("domainName", sys_parameter.getName_domain());
    	model.addAttribute("smtpFlag", sys_parameter.isFlag_smtp());
    	model.addAttribute("pop3Flag", sys_parameter.isFlag_pop3());
    }
    
    //addIP
    @RequestMapping("/addIP")
    public String addIP(Filter_Ip filter_ip,HttpServletRequest request,Model model)
    {
    	HttpSession session=request.getSession();
    	Integer user_id=(Integer)session.getAttribute("user_id");
    	String ip=filter_ip.getIp();
    	filter_ip.setCreate_by(user_id);
    	
    	if(sysService.addFilterIp(filter_ip)==true) {
    		model.addAttribute("flag_add", "1");
    	}
    	else {
    		model.addAttribute("flag_add", "0");
    	}
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getParameter("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=pagesum;
        	since_id=String.valueOf(s);
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/iplist")
    public String list(Filter_Ip filter_ip,HttpServletRequest request)
    {
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/nextpage")
    public String nextpage(User user,HttpServletRequest request)
    {
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)+1;
        	since_id=String.valueOf(s);
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/previouspage")
    public String previouspage(Filter_Ip filter_ip,HttpServletRequest request)
    {
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)-1;
        	since_id=String.valueOf(s);
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/firstpage")
    public String firstpage(User user,HttpServletRequest request)
    {
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=1;
        	since_id=String.valueOf(s);
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/lastpage")
    public String lastpage(User user,HttpServletRequest request)
    {
    	Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getParameter("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=pagesum;
        	since_id=String.valueOf(s);
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        return "setting/FilterIP";
    }
    
    @RequestMapping("/delete")
    public String delete(Filter_Ip filter_ip,HttpServletRequest request)
    {
    	
    	String since_id=(String)request.getAttribute("since_id");
    	String ip=(String)request.getParameter("delip");
    	since_id=(String)request.getParameter("currentpage");
        sysService.delete(ip);
        if(since_id==null){
        	since_id="1";
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<Filter_Ip> ips=sysService.getFilterIps((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("ips", ips);
        	request.setAttribute("since_id", since_id);
        }
        
        Integer count=sysService.countIp();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
        return "setting/FilterIP";
    }
}

