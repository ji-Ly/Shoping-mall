package com.hly.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.hly.dao.DAO;
import com.hly.entity.Account;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		DAO dao = new DAO();
		Account account = dao.login(user, pass);
		
		if(account == null) {
			request.setAttribute("msg", "Wrong user or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("acc", account);
			
			response.sendRedirect("home");
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
