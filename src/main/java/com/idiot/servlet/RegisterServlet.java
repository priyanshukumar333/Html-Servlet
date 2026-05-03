package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String bName=request.getParameter("bookName");
		String bEdition=request.getParameter("bookEdition");
		String bPrice=request.getParameter("bookPrice");
		
		//JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Mysql@1234");
			String query="Insert into bookdata(BOOKName,BOOKEdition,BOOKPrice) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,bName);
			ps.setString(2,bEdition);
			ps.setString(3,bPrice);
			  int i=ps.executeUpdate();
			  if (i>0) {
				  pw.print("successful");
				System.out.print("successful");
			} else {
				  pw.print(" Not successful");

				System.out.print("not successful");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pw.println("<br>");

		pw.println("<a href ='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href ='booklist'>Book List</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
