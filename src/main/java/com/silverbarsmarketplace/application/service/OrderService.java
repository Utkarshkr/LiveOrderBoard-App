package com.silverbarsmarketplace.application.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverbarsmarketplace.application.constant.Constants;
import com.silverbarsmarketplace.application.dto.OrderDto;
import com.silverbarsmarketplace.application.entity.Order;
import com.silverbarsmarketplace.application.repository.OrderRepository;

/**
 * The {@code OrderService} class is a Service component.It has various service methods to handle getbuyorders service request,
 *  getsellorders service request,registerorders service request and cancelorder service request.
 * @author Utkarsh Kumar
 
 */

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	/**
	 * It calls getOrders() method to fetch a list of Sell orders with orders of the same price merged
	 *  together.
	 *  Orders will be sorted with lowest price orders first.
	 * @return List of Orders of Sell type order.
	 */
	public List<OrderDto> getSellOrders() {
		  return getOrders(Constants.SELL_ORDER_TYPE);
	}
    
	/**
	 * It calls getOrders() method to fetch a list of Buy orders with orders of the same price merged
	 * together. Orders will be sorted with highest price orders first.
	 * @return List of Orders of Buy type order.
	 */
    public List<OrderDto> getBuyOrders() {
    	 return getOrders(Constants.BUY_ORDER_TYPE);
    }
	
    /**
	 * Register a new order.
	 * @return An order which gets successfully registered.
     */
	public Order postOrders(OrderDto order) {

		try {
			Order orderEntity = new Order();
			orderEntity.setOrderQuantity(order.getOrderQuantity());
			orderEntity.setOrderType(order.getOrderType());
			orderEntity.setPricePerKg(order.getPricePerKg());
			orderEntity.setUserId(order.getUserId());

			return orderRepository.save(orderEntity);

		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			ex.printStackTrace();
			return null;

		}

	}
	
	/**
	 * Cancels an already registered order.
     */
	
	public void deleteOrder(int id) {
		try {
			Optional<Order> optionalOrderEntity = orderRepository.findById(id);
			Order orderEntity = optionalOrderEntity.get();
			orderRepository.delete(orderEntity);
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			ex.printStackTrace();

		}
	}
	

	/**
	 * It fetches a list of orders of a particular order type.
	 * Merges the orders with the same price into a single order and adds their quantities.
	 * If order type is sell it sorts the orders with lowest price first.
	 * If order type is buy it sorts the orders with highest price first.
	 * @param ordertype
	 * @return List of Orders
	 */
	public List<OrderDto> getOrders(String ordertype) {
		try {
			List<Order> orderEntityList = orderRepository
					.findByorderType(ordertype);

			Map<Long, Long> orderDTOMap = orderEntityList.stream().collect(
					Collectors.groupingBy(Order::getPricePerKg,
							Collectors.summingLong(Order::getOrderQuantity)));

			Map<Long, Long> sortedOrderDTOMap = orderDTOMap
					.entrySet()
					.stream()
					.sorted(Map.Entry.<Long, Long> comparingByKey())
					.collect(
							Collectors.toMap(Map.Entry::getKey,
									Map.Entry::getValue,
									(oldValue, newValue) -> oldValue,
									LinkedHashMap::new));

			List<OrderDto> orderDTOList = transformMapIntoDtoList(sortedOrderDTOMap);

			if (Constants.BUY_ORDER_TYPE.equals(ordertype))
				Collections.reverse(orderDTOList);

			return orderDTOList;

		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			ex.printStackTrace();
			return null;

		}
	}
    /**
     * It transforms a map of orders with key as order price and value as order quantity sum into a 
     * list of Orders.
     * @param map
     * @return
     */
	public List<OrderDto> transformMapIntoDtoList(Map<Long, Long> map) {
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		try {
			map.forEach((k, v) -> {
				OrderDto orderDtoObj = new OrderDto();
				orderDtoObj.setPricePerKg(k);
				orderDtoObj.setOrderQuantity(v);
				orderDtoList.add(orderDtoObj);
			});

		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			ex.printStackTrace();

		}
		return orderDtoList;
	}

	/**
	 * Setter method for setting repository for service OrderService
	 * @param orderRepository
	 */
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	

}

