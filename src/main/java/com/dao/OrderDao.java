package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import com.model.Order;
import com.model.Product;


public class OrderDao implements AutoCloseable {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date, delivery_date) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getUid());
            pst.setInt(3, model.getQuantity());
            pst.setDate(4,  java.sql.Date.valueOf(model.getDate()));
            pst.setDate(5, java.sql.Date.valueOf(model.getDeliveryDate()));
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
        	// you can use inner join 
            query = "select * from orders where u_id=? order by orders.o_id desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getDate("o_date").toLocalDate());
                order.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
        	String query = "SELECT orders.o_id, orders.p_id, orders.u_id, orders.o_quantity, orders.o_date, orders.delivery_date, " +
                    "goods.name AS product_name, goods.category, goods.price, users.fullname AS user_name, " +
                    "DATEDIFF(orders.delivery_date, CURDATE()) AS days_to_delivery " +  
                    "FROM orders " +
                    "INNER JOIN goods ON orders.p_id = goods.id " +
                    "INNER JOIN users ON orders.u_id = users.id " +
                    "ORDER BY days_to_delivery ASC, orders.o_id DESC";  

            PreparedStatement pst = this.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("o_id"));
                order.setId(rs.getInt("p_id"));
                order.setUid(rs.getInt("u_id"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getDate("o_date").toLocalDate());
                order.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
                order.setProductName(rs.getString("product_name"));
                order.setCategory(rs.getString("category"));
                order.setUserName(rs.getString("user_name"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Order> createProcurementList() {
        List<Order> procurementList = getAllOrders();

        for (Order order : procurementList) {
            LocalDate deliveryDate = order.getDeliveryDate();
            LocalDate procurementDay = deliveryDate.minusDays(1);
            order.setProcurementDay(procurementDay);
        }

        procurementList.sort(Comparator.comparing(Order::getDeliveryDate));

        return procurementList;
    }


    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from orders where o_id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }

	@Override
	public void close() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
    }
}