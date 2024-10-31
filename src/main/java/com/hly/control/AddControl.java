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
 * Servlet implementation class AddControl
 */
@WebServlet(name = "add", urlPatterns = { "/add" })
public class AddControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String  price = request.getParameter("price");
		String title = request.getParameter("title");
		String desciption = request.getParameter("desciption");
		String category = request.getParameter("category");
		
		
		HttpSession sesstion = request.getSession();
		Account a = (Account) sesstion.getAttribute("acc");
		int id = a.getId();
		
		
		DAO dao = new DAO();
		dao.insertProduct(name, image, price, title, desciption, category, id);
		
		response.sendRedirect("manager");
		
		
	}

}
