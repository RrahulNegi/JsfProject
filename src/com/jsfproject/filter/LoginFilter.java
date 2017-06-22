package com.jsfproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsfproject.controller.LoginController;
@WebFilter(filterName = "LoginFilter", urlPatterns = {"*.xhtml"})
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
    	 try {

             // check whether session variable is set
             HttpServletRequest req = (HttpServletRequest) request;
             HttpServletResponse res = (HttpServletResponse) response;
             HttpSession ses = req.getSession(false);
             //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
             String reqURI = req.getRequestURI();
             if ( reqURI.indexOf("/index.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
                                        || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource") )
                    chain.doFilter(request, response);
             else   // user didn't log in but asking for a page that is not allowed so take user to login page
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");  // Anonymous user. Redirect to login page
       }
      catch(Throwable t) {
t.printStackTrace();     
}
    }
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
    public void destroy() {
        // Nothing to do here!
    }   
     
}