package com.dao;

import java.sql.*;
import java.util.*;

import com.model.Cart;
import com.model.Product;

public class ProductDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    

	public ProductDao(Connection con) {
		this.con = con;
	}
	
	
	public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {

            query = "select * from goods";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));

                products.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return  products;
    }
	
//	  public String getImageNameForProduct(int productId) {
//		  String imageName = null;
//	        
//	        try {
//	            // Prepare SQL query to retrieve image filename
//	            query = "SELECT image FROM goods WHERE id = ?";
//	            pst = this.con.prepareStatement(query);
//	            pst.setInt(1, productId);
//
//	            // Execute query and get the result set
//	            rs = pst.executeQuery();
//
//	            // If result set has data, retrieve the image filename
//	            while (rs.next()) {
//	                imageName = rs.getString("image");
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            // Handle SQL exception
//	        } finally {
//	            // Close resources
//	            try {
//	                if (rs != null) rs.close();
//	                if (pst != null) pst.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        return imageName;
//	    }
	
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from goods where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from goods where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from goods where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return products;
    }
}