package email.controller;

import email.bean.Sys_Parameter;
import email.bean.User;
import email.dao.SysParameterDao;
import email.service.impl.SysService;
import email.service.impl.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/server")

public class ServerController
{
	@Autowired
    private SysService sysService;
	
	 @RequestMapping("/SMTPClose")
	    public String SMTPClose(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	sysService.updateSMTPState(false);
	    	list(sys,request);
	    	return "/server/startstop";
	    }
	 
	 @RequestMapping("/SMTPOpen")
	    public String SMTPOpen(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	sysService.updateSMTPState(true);
	    	list(sys,request);
	    	return "/server/startstop";
	    }
	 
	 @RequestMapping("/POP3Close")
	    public String POP3Close(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	sysService.updatePOP3Sate(false);
	    	list(sys,request);
	    	return "/server/startstop";
	    }
	 
	 @RequestMapping("/POP3Open")
	    public String POP3Open(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	sysService.updatePOP3Sate(true);
	    	list(sys,request);
	    	return "/server/startstop";
	    }
	 
	 @RequestMapping("/list")
	    public String list(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	Sys_Parameter p=sysService.getAll();
	    	if(p.isFlag_smtp()==true) {
	    		request.setAttribute("smtpopen", "openstmp");
	    	}
	    	else if(p.isFlag_smtp()==false) {
	    		request.setAttribute("smtpclose", "closestmp");
	    	}
	    	if(p.isFlag_pop3()==true) {
	    		request.setAttribute("pop3open", "openpop3");
	    	}
	    	else if(p.isFlag_pop3()==false) {
	    		request.setAttribute("pop3close", "closepop3");
	    	}
	        return "server/startstop";
	    }
	 
	 @RequestMapping("/emailsize")
	    public String emailsize(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	Sys_Parameter p=sysService.getAll();
	    	int size=p.getSize();
	    	request.setAttribute("emailsize", size);
	        return "server/emailsize";
	    }
	 
	 @RequestMapping("/updateemailsize")
	    public String updateemailsize(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	sysService.updateEmailSize(sys.getSize());
	    	emailsize(sys,request);
	        return "server/emailsize";
	    }
	 
	 @RequestMapping("/sendemail")
	    public String sendemail(Sys_Parameter sys,HttpServletRequest request)
	    {
	    	String title=(String)request.getParameter("title");
	    	String content=(String)request.getParameter("content");
	    	HttpSession session=request.getSession();
        	int user_id=(Integer)session.getAttribute("user_id");
        	sysService.sendemail(title, content, user_id);
        	request.setAttribute("sendsuccessfully", "success");
	        return "server/SendEmails";
	    }
}
