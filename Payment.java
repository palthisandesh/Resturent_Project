package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/payment")
public class Payment extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ServletContext s=getServletContext();
        int id=(int)s.getAttribute("id");
        int amount=0;
        LocalDate da=LocalDate.now();
        String date=da.toString();

        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps = con.prepareStatement("delete from ssborder where num=?");
            ps.setInt(1,id);
            PreparedStatement p=con.prepareStatement("select sum(price) as totalamount from ssborder where num=?");
            p.setInt(1, id);
            ResultSet r2=p.executeQuery();
            if(r2.next()) {
            	amount=r2.getInt("totalamount");
            }
            ps1=con.prepareStatement("insert into ssbpayment values(?,?,?)");
            ps1.setInt(1,id);
            ps1.setString(2,date);
            ps1.setInt(3, amount);
            ps.executeUpdate();
            if(amount!=0) {
            	 ps1.executeUpdate();
            }
            
            out.println("<script>");
            out.println("alert('PAYMENT IS SUCCESSFULLY COMPLETED');");
            out.println("window.location.href='Main.html';");
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
