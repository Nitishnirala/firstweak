package com.app.first;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReceiverServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("null");
    String pass=(String)session.getAttribute("null");
//        out.println("Hello This is your user name="+user);
    }
}
