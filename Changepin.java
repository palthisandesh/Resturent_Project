package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/pin")
public class Changepin extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String pin=req.getParameter("pass");
		String cpin=req.getParameter("cpass");
		Connection con=null;
		PreparedStatement ps=null;
		ServletContext s=getServletContext();
        Integer id=(Integer) s.getAttribute("id");
		if(!pin.equals(cpin)) {
			out.println("<script>");
    		out.println("alert('please enter correct pin not match')");
    		out.println("window.history.href='changepin.html';");
    		out.println("</script>");
		}
		else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		        ps=con.prepareStatement("update ssblogin set passwords=? where num=?");
			    ps.setString(1,pin);
			    ps.setInt(2,id);
			    ps.executeUpdate();
			    out.println("<script>");
	    		out.println("alert('successfully changed')");
	    		out.println("window.location.href='Main.html';");
	    		out.println("</script>");
	    		
		        
		        
			}
			catch(Exception e) {
				System.out.println(e);
			}
			finally {
				try {
					if(con!=null)
						con.close();
					if(ps!=null)
						ps.close();
					    
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
			
		}

}

