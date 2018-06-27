package email.controller;

import com.alibaba.fastjson.JSONObject;
import email.bean.Email;
import email.bean.User;
import email.service.impl.EmailService;
import email.service.impl.SysService;
import email.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Controller
@RequestMapping("/android")
public class AndroidController
{
    @Autowired
    private UserService userService;
    @Autowired
    private SysService sysService;
    @Autowired
    private EmailService emailService;

    @RequestMapping("/login")
    public void login(User user, HttpServletResponse response)
    {
        checkUsername(user);
        boolean bool=userService.login(user);
        if(bool==true){
            User u=userService.getByName(user.getUsername());
            u.setUsername(u.getUsername()+"@"+sysService.getAll().getName_domain());
            try{
                response.getWriter().print(JSONObject.toJSONString(u));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/sendEmail")
    public void login(User user, Email email, HttpServletResponse response, HttpServletRequest request)
    {
        checkUsername(user);
        boolean bool=userService.login(user);
        if(bool==true){
            String receiver=request.getParameter("receiver");
            if(receiver.indexOf("@")>=0) receiver=receiver.substring(0,receiver.indexOf("@"));
            User u=userService.getByName(receiver);
            email.setUser_id(u.getId());
            emailService.sendEmail(email);
            try{
                response.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try{
                response.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @RequestMapping("/getState")
	@ResponseBody
	public String getState(Email email){
    	System.out.println(email.getSend_by()+" "+email.getSend_date());
    	return emailService.getState(email.getSend_by(), email.getSend_date());
    	
    }
	
    private void checkUsername(User user)
    {
        if(user.getUsername().indexOf("@")>=0)
        {
            user.setUsername(user.getUsername().substring(0,user.getUsername().indexOf("@")));
        }
    }

}
