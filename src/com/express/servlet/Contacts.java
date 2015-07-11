package com.express.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.MemberInformation;

/**
 * Servlet implementation class Contacts
 */
@WebServlet("/Contacts")
public class Contacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contacts() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("account")==null)
		{
			response.sendRedirect("signin.jsp");
			return ;
		}
    	
    	String function = request.getParameter("functionMy");
    	
    	if(function.equals("addContacts"))
    	{
    		String userIDListReq[] = request.getParameterValues("userIDList");
    		Vector userIDList = new Vector(10,6);
    		for(int i=0;i<userIDListReq.length;i++)
    		{
    			//System.out.println(Integer.parseInt(userIDListReq[i]));
    			userIDList.add(Integer.parseInt(userIDListReq[i]));
    		}
    		//System.out.println(Integer.parseInt((String)session.getAttribute("account")));
    		int lag = com.business.Contacts.addStaff(Integer.parseInt((String)session.getAttribute("account")), userIDList);
    		if(lag==1)
    		{
    			RequestDispatcher dispatch = request.getRequestDispatcher("contacts.jsp");
    			dispatch.forward(request, response);
    			return ;
    		}
    		else
    		{
    			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
    			dispatch.forward(request, response);
    			return ;
    		}
    		
    	}
    	
    	if(function.equals("deleteContacts"))
    	{
    		String deleteContact = request.getParameter("accountID");
    		System.out.println(deleteContact);
    		int deleteContactID = Integer.parseInt(deleteContact);
    		int userID = Integer.parseInt((String)session.getAttribute("account"));
    		
    		System.out.println(deleteContactID);
    		
    		int lag = com.business.Contacts.deleteStaff(userID, deleteContactID);
    		if(lag==1)
    		{
    			response.sendRedirect("contacts.jsp");
    			return ;
    		}
    		else
    		{
    			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
    			dispatch.forward(request, response);
    			return ;
    		}
    	}

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
