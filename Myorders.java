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
@WebServlet("/abc")
public class Myorders extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        ServletContext c=getServletContext();
        Integer id=(Integer)c.getAttribute("id");
        int count=0;
        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            PreparedStatement p=con.prepareStatement("select count(*) as totalamount from ssbpayment where num=?");
            p.setInt(1, id);
            ResultSet r2=p.executeQuery();
            if(r2.next()) {
            	count=r2.getInt("totalamount");
            }
            if(count!=0) {
            	ps = con.prepareStatement("select * from ssbpayment where num=?");
                ps.setInt(1, id);
                ResultSet r=ps.executeQuery();
            	out.println("<html lang=\"en\">\r\n"
            			+ "<head>\r\n"
            			+ "    <meta charset=\"UTF-8\">\r\n"
            			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            			+ "    <title>Document</title>\r\n"
            			+ "    <style>\r\n"
            			+ "        body{\r\n"
            			+ "            background-color: rgb(20, 100, 206);\r\n"
            			+ "        }\r\n"
            			+ "        table{\r\n"
            			+ "            border: 2px solid black;\r\n"
            			+ "            margin-left: 600PX;\r\n"
            			+ "            width: 200px;\r\n"
            			+ "            \r\n"
            			+ "        }\r\n"
            			+ "        table td{\r\n"
            			+ "            text-align: center;\r\n"
            			+ "            color: black;\r\n"
            			+ "        }\r\n"
            			+ "        h2{\r\n"
            			+ "           border: 2px solid black;\r\n"
            			+ "           width: 600px;\r\n"
            			+ "           height: 70px;\r\n"
            			+ "           text-align: center;\r\n"
            			+ "           margin: auto;\r\n"
            			+ "           margin-bottom: 20px;\r\n"
            			+ "           border-radius: 50%;\r\n"
            			+ "           margin-top: 20px;\r\n"
            			+ "           background-color: red;\r\n"
            			+ "        }\r\n"
            			+ "\r\n"
            			+ "    </style>\r\n"
            			+ "</head>\r\n"
            			+ "<body>\r\n"
            			+ "    <h2>SSB RESTURENT</h2>\r\n"
            			+ "    <table border=\"1\">\r\n"
            			+ "        <tr>\r\n"
            			+ "            <th>DATE</th>\r\n"
            			+ "            <th>AMOUNT</th>\r\n"
            			+ "        </tr>");
                while(r.next()) {
            		out.println("<tr>\r\n"
            				+ "            <td>"+r.getString(2)+"</td>\r\n"
            				+ "            <td>"+r.getInt(3)+"</td>\r\n"
            				+ "        </tr>");
            	}
            	out.println("</table>\r\n<br><br>"
            			+"   <a href='Main.html' style='margin-left:400px;'>BACK</a>   \r\n"
            			+ "</body>\r\n"
            			+ "</html>");
            
                
            }
            else {
            	out.println("<!DOCTYPE html>\r\n"
            			+ "<html lang=\"en\">\r\n"
            			+ "<head>\r\n"
            			+ "    <meta charset=\"UTF-8\">\r\n"
            			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            			+ "    <title>Document</title>\r\n"
            			+ "    <style>\r\n"
            			+ "        body{\r\n"
            			+ "             background-color: rgb(20, 100, 206);\r\n"
            			+ "             margin: auto;\r\n"
            			+ "             color: black;\r\n"
            			+ "        }\r\n"
            			+ "        div{\r\n"
            			+ "            margin: auto;\r\n"
            			+ "            margin-left: 600px;\r\n"
            			+ "            margin-top: 250px;\r\n"
            			+ "        }\r\n"
            			+ "        div a{\r\n"
            			+ "            color: black;\r\n"
            			+ "        }\r\n"
            			+ "\r\n"
            			+ "    </style>\r\n"
            			+ "</head>\r\n"
            			+ "<body>\r\n"
            			+ "    <div>\r\n"
            			+ "        <h2>NO ORDERS</h2>\r\n"
            			+ "         <a href=\"orders.html\">PLEASE CLICK TO ADD CART</a>\r\n"
            			+ "    </div>\r\n"
            			+ "</body>\r\n"
            			+ "</html>");
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
