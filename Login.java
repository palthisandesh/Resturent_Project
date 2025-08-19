package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.sql.ResultSet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String user=req.getParameter("user");
        String pwd=req.getParameter("pwd");
        Connection con = null;
        PreparedStatement ps = null;
       

        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps = con.prepareStatement("select * from ssblogin where username = ? and passwords=?");
            ps.setString(1,user);
            ps.setString(2, pwd);
            ResultSet r=ps.executeQuery();
            if(r.next()) {
            	int id=r.getInt(1);
            	String u=r.getString(2);
            	String p=r.getString(3);
            	ServletContext c=getServletContext();
        		c.setAttribute("id", id);
        		c.setAttribute("name", u);
            	if(u.equals(user) && p.equals(pwd)) {
            		RequestDispatcher rs=req.getRequestDispatcher("Main.html");
                	rs.forward(req,res);
            	}
            	
            }
            else {
        		out.println("<script>");
        		out.println("alert('enter the correct details');");
        		out.println("window.location.href='Login.html';");
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
