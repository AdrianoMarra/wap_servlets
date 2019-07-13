package com.pantanalshop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AuthFilter",
        urlPatterns = {"/checkout","/logout" },
        servletNames = { "LoginController","LogoutController" }
)

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getContextPath()+"/auth";
        boolean isLoggedIn = session !=null && session.getAttribute("user")!= null;
        boolean logInRequest = request.getRequestURI().equals(uri);
        if(isLoggedIn || logInRequest){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
