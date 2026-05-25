package com.ems.service;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ems.dao.EmployeeDAO;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession(true);
		
		RequestDispatcher dispatcher = null;
		if(email.equals("admin@codegnan.com") && password.equals("admin@123")){
			session.setAttribute("email",email);
			dispatcher=request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request,response);
		}
		
		EmployeeDAO dao=new EmployeeDAO();
		try {
		boolean status=dao.checkLogin(email,password);

		if(status) {
			session.setAttribute("email",email);
			dispatcher=request.getRequestDispatcher("employee.jsp");
			dispatcher.forward(request, response);
		}else {
			dispatcher=request.getRequestDispatcher("login.jsp");
			request.setAttribute("status","Invalid credentials.....");
			dispatcher.forward(request,response);
		}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
