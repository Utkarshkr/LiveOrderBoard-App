package com.silverbarsmarketplace.application.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.silverbarsmarketplace.application.entity.Order;

/**
 * The {@code OrderRepository} class is the Repository class for handling different crud operations.
 * @author Utkarsh Kumar
 */

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
	
	 /**
	 * It fetches a list of Orders of a particular order type.
	 * @param orderType
	 * @return List of orders of given order type.
	 */
	List<Order> findByorderType(String orderType);

}
