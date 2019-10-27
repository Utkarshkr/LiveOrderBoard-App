package com.silverbarsmarketplace.application.dto;

/**
 * The {@code OrderDto} class is the DTO class for Order Entity.
 * @author Utkarsh Kumar
 */

public class OrderDto {
	
	private int orderId;
	private String userId;
	private String orderType;
	private long orderQuantity;
	private long pricePerKg;
	
	/**
     * Getters and Setters for OrderDto attributes.
     */
	 
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public long getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(long orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public long getPricePerKg() {
		return pricePerKg;
	}
	public void setPricePerKg(long pricePerKg) {
		this.pricePerKg = pricePerKg;
	}
	 
	 

}
