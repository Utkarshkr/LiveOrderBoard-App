package com.silverbarsmarketplace.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The {@code Order} class is the Model class for persisting and fetching orders.
 * @author Utkarsh Kumar
 */

@Entity
@Table(name="USER_ORDERS")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDER_ID",unique=true,nullable=false)
	@JsonIgnore
    private int orderId;
	
	@Column(name="USER_ID",unique=false,nullable=false)
    private String userId;
	
	@Column(name="ORDER_TYPE",unique=false,nullable=false)
    private String orderType;
	
	@Column(name="ORDER_QUANTITY",unique=false,nullable=false)
    private long orderQuantity;
	
	@Column(name="ORDER_PRICE",unique=false,nullable=false)
    private long pricePerKg;
	
	/**
     * Getters and Setters for Order attributes.
     */
	
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
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	

}
