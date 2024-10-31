package com.hly.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hly.dao.DAO;
import com.hly.entity.Category;
import com.hly.entity.Product;

/**
 * Servlet implementation class DetailControl
 */
@WebServlet(name = "detail", urlPatterns = { "/detail" })
public class DetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("pid");

		DAO dao = new DAO();
		Product p = dao.getProductByID(id);
		List<Category> listC = dao.getAllCategory();
		Product lastProduct = dao.getLastProduct();

		request.setAttribute("detail", p);
		request.setAttribute("listCC", listC);
		request.setAttribute("lP", lastProduct);
		request.getRequestDispatcher("Detail.jsp").forward(request, response);
	}

}
