package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/passwordlog")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		String user=req.getParameter("user");
		ServletContext s=getServletContext();
		s.setAttribute("username",user);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
	        ps=con.prepareStatement("select username from ssblogin where username=?");
		    ps.setString(1,user);
		    ResultSet r=ps.executeQuery();
		    if(r.next()) {
		    	out.println("<script>");
		    	out.println("window.location.href='loginchangepin.html';");
		    	out.println("</script>");
		    }
		    else {
		    	out.println("<script>");
		    	out.println("alert('user does not exit');");
		    	out.println("</script>");
		    }
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
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
