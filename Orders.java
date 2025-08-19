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
@WebServlet("/orders")
public class Orders extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name=req.getParameter("item");
        String qua=req.getParameter("quantity");
        String amount=req.getParameter("amount");
        Connection con = null;
        ServletContext s=getServletContext();
        Integer id=(Integer) s.getAttribute("id");
        PreparedStatement ps = null;
       

        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps = con.prepareStatement("insert into ssborder values(?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3,qua);
            ps.setString(4,amount);
            ps.executeUpdate();
            out.println("<script>");
            out.println("window.location.href='orders.html';");
            out.println("</script>");
            
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
