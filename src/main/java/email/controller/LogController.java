package email.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import email.bean.Email;
import email.bean.Email_File;
import email.bean.Log;
import email.bean.Sys_Parameter;
import email.bean.User;
import email.dao.LogDao;
import email.service.impl.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Controller
@RequestMapping("/Log")
public class LogController
{
    @Autowired
    private LogService logservice;

    @RequestMapping("/SmtpList")
    public String SmtpList(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getAttribute("since_id");
    	since_id=(String)request.getParameter("currentpage");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(0);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
        return "/server/logSmtp";
    }
    
    @RequestMapping("/deleteSmtp")
    public String deleteSmtp(Log log, HttpServletRequest request)
    {
    	int id=Integer.parseInt(request.getParameter("delid"));
    	logservice.deleteById(id);
    	SmtpList(log, request);
        return "/server/logSmtp";
    }
    
    @RequestMapping("/deleteAllSmtp")
    public String deleteAllSmtp(Log log, HttpServletRequest request)
    {
    	logservice.deleteAll(0);
    	SmtpList(log, request);
        return "/server/logSmtp";
    }
    
    @RequestMapping("/nextSmtppage")
    public String nextSmtppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)+1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(0);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logSmtp";
    }
    
    @RequestMapping("/previousSmtppage")
    public String previousSmtppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)-1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(0);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logSmtp";
    }
    
    @RequestMapping("/firstSmtppage")
    public String firstSmtppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(0);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logSmtp";
    }
    
    @RequestMapping("/lastSmtppage")
    public String lastSmtppage(Log log, HttpServletRequest request)
    {
    	Integer count=logservice.countlog(0);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=pagesum;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(0, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        
    	return "/server/logSmtp";
    }
    
    @RequestMapping("/Pop3List")
    public String Pop3List(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getAttribute("since_id");
    	since_id=(String)request.getParameter("currentpage");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(1);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
        return "/server/logPop3";
    }
    
    @RequestMapping("/deletePop3")
    public String deletePop3(Log log, HttpServletRequest request)
    {
    	int id=Integer.parseInt(request.getParameter("delid"));
    	logservice.deleteById(id);
    	Pop3List(log, request);
        return "/server/logPop3";
    }
    
    @RequestMapping("/deleteAllPop3")
    public String deleteAllPop3(Log log, HttpServletRequest request)
    {
    	logservice.deleteAll(1);
    	Pop3List(log, request);
        return "/server/logSmtp";
    }
    
    @RequestMapping("/nextPoppage")
    public String nextPoppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)+1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(1);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logPop3";
    }
    
    @RequestMapping("/previousPoppage")
    public String previousPoppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)-1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(1);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logPop3";
    }
    
    @RequestMapping("/firstPoppage")
    public String firstPoppage(Log log, HttpServletRequest request)
    {
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=1;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=logservice.countlog(1);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	return "/server/logPop3";
    }
    
    @RequestMapping("/lastPoppage")
    public String lastPoppage(Log log, HttpServletRequest request)
    {
    	Integer count=logservice.countlog(1);
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getParameter("since_id");
        if(since_id==null){
        	since_id="1";
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=pagesum;
        	since_id=String.valueOf(s);
        	List<Log> logs=logservice.getLogsByProtocol(1, (Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("logs", logs);
        	request.setAttribute("since_id", since_id);
        }
        
    	return "/server/logPop3";
    }
}
