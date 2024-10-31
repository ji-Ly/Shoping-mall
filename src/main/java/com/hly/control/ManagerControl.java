package com.hly.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.hly.dao.DAO;
import com.hly.entity.Account;
import com.hly.entity.Category;
import com.hly.entity.Product;

/**
 * Servlet implementation class ManagerControl
 */
@WebServlet(name = "manager", urlPatterns = { "/manager" })
public class ManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("acc");
		int id = a.getId();
		
		DAO dao = new DAO();
		List<Product> list = dao.getAllProductBySellID(id);
		  List<Category> listC = dao.getAllCategory();

		  request.setAttribute("listCC", listC);
		request.setAttribute("listP", list);
		request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
