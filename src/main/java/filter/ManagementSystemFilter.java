package filter;



import email.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
public class ManagementSystemFilter implements javax.servlet.Filter
{
   private static String[] noFilter={"/user/login"};
    private int power;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String param=filterConfig.getInitParameter("power");
        power= Integer.parseInt(param);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpSession session=request.getSession();
        User currUser=(User)session.getAttribute("currUser");
        boolean flag=true;
        for(String s: noFilter)
        {
            if(request.getServletPath().equals(s))
            {
                flag=false;break;
            }
        }
        if(!request.getServletPath().startsWith("/user/images")&&flag&&(currUser==null||currUser!=null&&currUser.getPower()<power))
        {

            if(currUser==null)
                servletRequest.getRequestDispatcher("/user/").forward(servletRequest,servletResponse);
            else
            {
                servletRequest.setAttribute("message","您的权限不足！");
                servletRequest.getRequestDispatcher("/user/").forward(servletRequest,servletResponse);
            }

            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}