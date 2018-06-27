package email.controller;

import com.alibaba.fastjson.JSONObject;
import email.bean.User;
import email.dao.UserDao;
import email.service.impl.UserService;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

	@RequestMapping("")
	public String loginPage(HttpServletRequest httpServletRequest)
	{
		HttpSession session=httpServletRequest.getSession();
		if(session.getAttribute("currUser")==null)
			return "user/login";
		return "user/main";
	}


    @RequestMapping("/login")
    public String login(User user,HttpServletRequest request,Model model)
    {
    	if(request.getSession().getAttribute("currUser")!=null)
    	{
			return "user/main";
		}
        boolean bool=userService.login(user);
        if(bool==true){
        	User u=userService.getByName(user.getUsername());
        	HttpSession session=request.getSession();
        	session.setAttribute("currUser",u);
        	session.setAttribute("user_id", u.getId());
        	return "user/main";
        }
        request.setAttribute("error", "error");
        return "user/login";
    }

    @RequestMapping("/quit")
    public void quit(User user,HttpServletRequest request,HttpServletResponse resp) throws IOException
    {
    	HttpSession session=request.getSession();
    	session.removeAttribute("user_id");
		session.removeAttribute("currUser");
    	resp.sendRedirect("login.jsp");
    }
    
    @RequestMapping("/save")
    public String save(User user,HttpServletRequest request)
    {
    	
    	String since_id=(String)request.getAttribute("since_id");
        userService.insert(user);
        if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
        return "user/PersonSys";
    }
    
    @RequestMapping("/delete")
    public String delete(User user,HttpServletRequest request)
    {
    	
    	String since_id=(String)request.getAttribute("since_id");
    	int id=Integer.parseInt(request.getParameter("delid"));
    	since_id=(String)request.getParameter("currentpage");
        userService.delete(id);
        if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        
        Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
        return "user/PersonSys";
    }
    
    @RequestMapping("/filter")
    public String filter(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getAttribute("since_id");
    	int id=Integer.parseInt(request.getParameter("filterid"));
    	int filter=Integer.parseInt(request.getParameter("filter"));
    	since_id=(String)request.getParameter("currentpage");
        userService.updateDisable(id, filter);
        if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/disable")
    public String disable(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getAttribute("since_id");
    	int id=Integer.parseInt(request.getParameter("disableid"));
    	int disable=Integer.parseInt(request.getParameter("disable"));
    	since_id=(String)request.getParameter("currentpage");
        userService.updateFilter(id, disable);
        if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/power")
    public String power(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getAttribute("since_id");
    	int id=Integer.parseInt(request.getParameter("powerid"));
    	since_id=(String)request.getParameter("currentpage");
        userService.updatePower(id, 1);;
        if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/updatepsd")
    public String updatepsd(User user,HttpServletRequest request)
    {
    	String newpsd=(String)request.getParameter("newpsd");
    	HttpSession session=request.getSession();
    	Integer user_id=(Integer)session.getAttribute("user_id");
    	userService.updatePassword(user_id, newpsd);
        return "user/right";
    }
    
    @RequestMapping("/list")
    public String list(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/nextpage")
    public String nextpage(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)+1;
        	since_id=String.valueOf(s);
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/previouspage")
    public String previouspage(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=Integer.parseInt(since_id)-1;
        	since_id=String.valueOf(s);
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/firstpage")
    public String firstpage(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	//HttpSession session=request.getSession();
    	String since_id=(String)request.getParameter("since_id");
    	//session.removeAttribute("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=1;
        	since_id=String.valueOf(s);
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
    
    @RequestMapping("/lastpage")
    public String lastpage(User user,HttpServletRequest request)
    {
    	Integer count=userService.countUser();
    	request.setAttribute("sum", count);
    	Integer pagesum=(count-1)/10+1;
    	request.setAttribute("pagesum", pagesum);
    	String since_id=(String)request.getParameter("since_id");
    	if(since_id==null){
        	since_id="1";
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        else{
        	int s=pagesum;
        	since_id=String.valueOf(s);
        	List<User> users=userService.getUsersPC((Integer.parseInt(since_id)-1)*10);
        	request.setAttribute("users", users);
        	request.setAttribute("since_id", since_id);
        }
        return "user/PersonSys";
    }
}
