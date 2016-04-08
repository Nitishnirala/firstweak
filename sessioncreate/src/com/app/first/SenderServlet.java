package com.app.first;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SenderServlet extends HttpServlet {

    /**
	 * @author 
	 * name nitish kumar nirala
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        if(pass!=null&&name!=null)
        {
            //creating a session
            HttpSession session = request.getSession();
            session.setAttribute("user", name);
            session.setAttribute("password",pass);
       response.sendRedirect("login.jsp");
        }
    }
}