package email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import email.bean.User;
import email.service.impl.SysService;
import email.service.impl.UserService;
import net.sf.json.JSONObject;

@Controller
public class AndroidUserController {
	
	@Autowired
    private UserService userService;
	@Autowired
	private SysService sysService;
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public Object userLogin(User user){
		boolean bool=userService.userlogin(user);
		JSONObject result;
        if(bool==true){
        	User u=userService.getByName(user.getUsername());
        	u.setUsername(u.getUsername()+"@"+sysService.getAll().getName_domain());
        	result=JSONObject.fromObject(u);
        }
        else{
        	result=JSONObject.fromObject(null);
        }
		return result;
	}
	
	
}
