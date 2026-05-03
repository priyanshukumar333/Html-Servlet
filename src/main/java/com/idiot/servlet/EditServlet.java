package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/editScreen")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		String idParam = request.getParameter("id");

		if(idParam == null){
		    pw.println("ID not found");
		    return;
		}

		int id = Integer.parseInt(idParam);

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/book","root","Mysql@1234");

		    String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM bookdata WHERE id=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, id);

		    ResultSet rs = ps.executeQuery();

		    if(rs.next()){
		        pw.println("<form action='editurl?id="+id+"' method='post'>");
		        pw.println("<table border='2' align='center'>");

		        pw.println("<tr>");
		        pw.println("<td>Book Name</td>");
		        pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
		        pw.println("</tr>");

		        pw.println("<tr>");
		        pw.println("<td>Book Edition</td>");
		        pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
		        pw.println("</tr>");

		        pw.println("<tr>");
		        pw.println("<td>Book Price</td>");
		        pw.println("<td><input type='text' name='bookPrice' value='"+rs.getString(3)+"'></td>");
		        pw.println("</tr>");

		        pw.println("<tr>");
		        pw.println("<td><input type='submit' value='Update'></td>");
		        pw.println("<td><input type='reset' value='Cancel'></td>");
		        pw.println("</tr>");

		        pw.println("</table>");
		        pw.println("</form>");
		    } else {
		        pw.println("Record not found");
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
