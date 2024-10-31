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
 * Servlet implementation class CategoryControl
 */
@WebServlet(name = "category", urlPatterns = { "/category" })
public class CategoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cateID = request.getParameter("cid");
		DAO dao = new DAO();
		List<Product> list = dao.getAllProductByCID(cateID);
		List<Category> listC = dao.getAllCategory();
		Product lastProduct = dao.getLastProduct();

		request.setAttribute("listP", list);
		request.setAttribute("listCC", listC);
        request.setAttribute("lP", lastProduct);
        request.setAttribute("tag", cateID);
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

}
