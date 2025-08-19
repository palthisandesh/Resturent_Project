package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/signup")
public class Sigup extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String user=req.getParameter("user");
        String pwd=req.getParameter("pwd");
        String cpwd=req.getParameter("cpwd");
        Connection con = null;
        PreparedStatement ps = null;
       

        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            if(pwd.equals(cpwd)) {
            	ps = con.prepareStatement("insert into ssblogin(username,passwords) values (?,?)");
                ps.setString(1,user);
                ps.setString(2, pwd);
                ps.executeUpdate();
                out.println("<script>");
        		out.println("alert('SUCCESSFULLY CREATED ACCOUNT');");
        		out.println("window.location.href='Login.html';");
        		out.println("</script>");
            }
            else {
            	out.println("<script>");
        		out.println("alert('please enter both same');");
        		out.println("window.location.href='signup.html';");
        		out.println("</script>");
            }
        }
        catch(Exception e) {
        	out.println("<script>");
    		out.println("alert('alredy user exist');");
    		out.println("window.location.href='signup.html';");
    		out.println("</script>");
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
