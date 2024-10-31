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
 * Servlet implementation class SearchControl
 */
@WebServlet(name = "search", urlPatterns = { "/search" })
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String txtSearch = request.getParameter("txt");
		
		DAO dao = new DAO();
		List<Product> list = dao.searchByName(txtSearch);
		   List<Category> listC = dao.getAllCategory();
	        Product lastProduct = dao.getLastProduct();
		
		request.setAttribute("listP", list);
		 request.setAttribute("listCC", listC);
	        request.setAttribute("lP", lastProduct);
	    	request.setAttribute("txtS", txtSearch);
		request.getRequestDispatcher("Home.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response); // Chuyển hướng xử lý về doGet
	}

}
