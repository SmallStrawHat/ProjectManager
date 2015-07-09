package com.express.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Checklog
 */
//@WebFilter("*.jsp")
public class Checklog implements Filter {

    /**
     * Default constructor. 
     */
    public Checklog() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//参数为false时，如存在会话，则返回该会话，否则返回NULL；
		HttpSession session = req.getSession(false);
		if(session == null )
		{
			if(!req.getServletPath().equals("/signin.jsp"))
			{
				RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
				dispatch.forward(request, response);
				return ;
			}
		}
		else
		{
			String account = (String)session.getAttribute("account");
			if(account == null)
			{
				RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
				dispatch.forward(request, response);
				return ;
			}
			else
			{
				if(req.getServletPath().equals("/signin.jsp"))
				{
					//已经登录，跳到index.jsp
					RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
					dispatch.forward(request, response);
					return ;
				}
			}
			
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
