package com.hly.control;

import jakarta.servlet.ServletConfig;
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
 * Servlet implementation class HomeControl
 */
@WebServlet(name = "home", urlPatterns = { "/home" })
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public HomeControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product lastProduct = dao.getLastProduct();
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.setAttribute("lP", lastProduct);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        
    
    
    }
  


}
