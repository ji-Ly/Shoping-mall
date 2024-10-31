package com.hly.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hly.context.Context;
import com.hly.entity.Account;
import com.hly.entity.Category;
import com.hly.entity.Product;
import com.oracle.wls.shaded.org.apache.bcel.generic.NEW;

//DAO có nhienj vụ lấy data từ database lên
public class DAO {

	
	
	
	// laays tat ca san pham tu db
	public List<Product> getAllProduct() {

		List<Product> list = new ArrayList<>();
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Category> getAllCategory() {

		List<Category> list = new ArrayList<>();
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product getLastProduct() {

		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select top 1 * from product order by id desc");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6));
			}

		} catch (Exception e) {

		}
		return null;
	}

	public List<Product> getAllProductByCID(String cid) {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product where cateid = ?");
			preparedStatement.setString(1, cid);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product getProductByID(String id) {

		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Product> searchByName(String txtSearch) {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product where [name] like ?");
			preparedStatement.setString(1, "%"+txtSearch+"%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public Account login(String user, String pass) {
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from account where [user] = ? and pass = ?");
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return new Account(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4),
						rs.getInt(5)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Account checkAccountExist(String user) {
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from account where [user] = ? ");
			preparedStatement.setString(1, user);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return new Account(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4),
						rs.getInt(5)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void signUp(String user, String pass) {
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into account values( ? , ? ,0 ,0 )");
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Product> getAllProductBySellID(int sid) {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product where sell_id = ?");
			preparedStatement.setInt(1, sid);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void deleteProduct(String id) {
		try {
			Connection connection = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");
			preparedStatement.setString(1, id);
		
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void insertProduct(String name, String image, String price, String title, String desciption, String category, int id) {
		try {
			Connection connection  = Context.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT [dbo].[product] ([name], [image], [price], [title], [description], [cateID], [sell_ID]) VALUES (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, image);
			preparedStatement.setString(3, price);
			preparedStatement.setString(4, title);
			preparedStatement.setString(5, desciption);
			preparedStatement.setString(6, category);
			preparedStatement.setInt(7, id);
			
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		for (Product o : new DAO().getAllProduct()) {
//			System.out.println(o);
//		}

//		for (Category o : new DAO().getAllCategory()) {
//			System.out.println(o);
//			
//		}

		// System.out.println(new DAO().getLastProduct());

//		

	//	System.out.println(new DAO().getProductByID("1"));
		
//		for (Product string : new DAO().searchByName("2")) {
//			System.out.println(string);
//		}
		
		
		
	}
}
