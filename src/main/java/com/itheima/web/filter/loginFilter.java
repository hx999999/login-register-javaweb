package com.itheima.web.filter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        String[] urls ={"/login.jsp","/imgs/","/css/","/loginServlet","/register.jsp","/registerServlet","/checkCodeServlet"};

        String s = req.getRequestURL().toString();
        for (String url : urls) {
            if(s.contains(url)){
                chain.doFilter(request, response);
                return ;
            }
        }


        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user!= null) {
            chain.doFilter(request, response);
        }
        else{
            req.setAttribute("login_msg","你尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req,response);
        }
    }
}
