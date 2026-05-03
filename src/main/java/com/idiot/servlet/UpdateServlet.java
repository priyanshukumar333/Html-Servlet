package com.idiot.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/editurl")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    int id = Integer.parseInt(request.getParameter("id"));
		    String name = request.getParameter("bookName");
		    String edition = request.getParameter("bookEdition");
		    String price = request.getParameter("bookPrice");

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/book","root","Mysql@1234");

		        String query = "UPDATE bookdata SET BOOKNAME=?, BOOKEDITION=?, BOOKPRICE=? WHERE ID=?";
		        PreparedStatement ps = con.prepareStatement(query);

		        ps.setString(1, name);
		        ps.setString(2, edition);
		        ps.setString(3, price);
		        ps.setInt(4, id);

		        ps.executeUpdate();

		        response.sendRedirect("booklist");

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
