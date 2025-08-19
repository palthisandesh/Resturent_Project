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
@WebServlet("/cart")
public class Cart extends HttpServlet {
	 protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        doPost(req, res);
	 }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        ServletContext c=getServletContext();
        Integer id=(Integer)c.getAttribute("id");
        int amount=0;
        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            PreparedStatement p=con.prepareStatement("select sum(price) as totalamount from ssborder where num=?");
            p.setInt(1, id);
            ResultSet r2=p.executeQuery();
            if(r2.next()) {
            	amount=r2.getInt("totalamount");
            }
            if(amount!=0) {
            	 ps = con.prepareStatement("select * from ssborder where num=?");
                 ps.setInt(1, id);
                 ResultSet r=ps.executeQuery();
                 if(r!=null) {
                 	out.println("<!DOCTYPE html>\r\n"
                 			+ "<html lang=\"en\">\r\n"
                 			+ "<head>\r\n"
                 			+ "    <meta charset=\"UTF-8\">\r\n"
                 			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                 			+ "    <title>Document</title>\r\n"
                 			+ "    <style>\r\n"
                 			+ "        body{\r\n"
                 			+ "            background-color: rgb(20, 100, 206);\r\n"
                 			+ "            width: 100%;\r\n"
                 			+ "\r\n"
                 			+ "        }\r\n"
                 			+ "        table{\r\n"
                 			+ "            width: 100%;\r\n"
                 			+ "            margin-bottom: 30px;\r\n"
                 			+ "            \r\n"
                 			+ "        }\r\n"
                 			+ "        table td{\r\n"
                 			+ "           text-align: center;\r\n"
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
                 			+ "        .pay{\r\n"
                 			+ "            text-align: center;\r\n"
                 			+ "            border: 2px solid black;\r\n"
                 			+ "            width: 20px;\r\n"
                 			+ "            height: 20px;\r\n"
                 			+ "            text-align: center;\r\n"
                 			+ "            margin: auto;\r\n"
                 			+ "            \r\n"
                 			+ "            margin-left: 600px;\r\n"
                 			+ "            padding: 7px;\r\n"
                 			+ "            border-radius: 5px;\r\n"
                 			+ "            background-color: red;\r\n"
                 			+ "            text-decoration: none;\r\n"
                 			+ "        }"
                 			+ "       .amount{\r\n"
                 			+ "             margin-left: 86%;\r\n"
                 			+ "        }\r\n"
                 			+ "    </style>\r\n"
                 			+ "</head>\r\n"
                 			+ "<body>\r\n"
                 			+ "    <table>\r\n"
                 			+ "        <h2>SSB RESTURENT</h2>\r\n"
                 			+ "        <tr>\r\n"
                 			+ "            <th>ITEM NAME </th>\r\n"
                 			+ "            <th>QUALITY</th>\r\n"
                 			+ "            <th>PRICE</th>\r\n"
                 			+ "            \r\n"
                 			+ "        </tr>");
                     while(r.next()) {
                     	out.println("<tr>");
                     	out.println("<td>"+r.getString(2)+"</td>");
                     	out.println("<td>"+r.getString(3)+"</td>");
                     	out.println("<td>"+r.getString(4)+"</td>");
                     }
                     
                     out.println("</table>");
                    
                     	out.println("<hr>\r\n"
                     			+ "    <div class=\"amount\">\r\n"
                     			+ "        <h4>RS:"+amount+"</h4>\r\n"
                     			+ "    </div>");
                     
                     out.println("<a href=\"payment.html\" class=\"pay\" style=\"text-align: center;\">PROCED TO PAY</a>\r\n"
                     	+ "<a href='MainPage.html'>BACK</a>"
                     		+ "    \r\n"
                     		+ "</body>\r\n"
                     		+ "</html>");
                 }
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
            			+ "        <h2>NO TO ADD CART</h2>\r\n"
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
