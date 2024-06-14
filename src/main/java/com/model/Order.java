package com.model;

import java.time.LocalDate;

public class Order extends Product{
	public Order(int orderId, int uid, int quantity, LocalDate date, LocalDate deliveryDate, String productName,
			String category, String userName, LocalDate procurementDay) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.deliveryDate = deliveryDate;
		this.productName = productName;
		this.category = category;
		this.userName = userName;
		this.procurementDay = procurementDay;
	}

	private int orderId;
	private int uid;
	private int quantity;
	private LocalDate date;
	private LocalDate deliveryDate;
	private String productName;
    private String category;
    private String userName;
    private LocalDate procurementDay;
	
	public Order() {
	}
	
	public Order(int orderId, int uid, int quantity, LocalDate date, LocalDate deliveryDate) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.deliveryDate = deliveryDate;
	}

	public Order(int uid, int quantity, LocalDate date,LocalDate deliveryDate) {
		super();
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.deliveryDate = deliveryDate;
	}
	
	

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int qunatity) {
		this.quantity = qunatity;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getProcurementDay() {
		return procurementDay;
	}

	public void setProcurementDay(LocalDate procurementDay) {
		this.procurementDay = procurementDay;
	}
}