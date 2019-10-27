package com.silverbarsmarketplace.LiveOrderBoard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.silverbarsmarketplace.application.constant.Constants;
import com.silverbarsmarketplace.application.dto.OrderDto;
import com.silverbarsmarketplace.application.entity.Order;
import com.silverbarsmarketplace.application.repository.OrderRepository;
import com.silverbarsmarketplace.application.service.OrderService;

/**
 * The {@code OrderServiceTest} class is a Test class for testing various service methods of OrderService service component.
 * @author Utkarsh Kumar
 
 */

public class OrderServiceTest {

	private OrderRepository orderRepository;
	
	private OrderService orderService = new OrderService();
	
	
	@Before
	public void setup() {
		orderRepository = mock(OrderRepository.class);
		orderService.setOrderRepository(orderRepository);
	}
	
	@Test
	public void testGetBuyOrders(){
		
		List<Order> orderEntityList = new ArrayList<>();
		
		Order order1 = new Order();
		order1.setOrderQuantity(10);
		order1.setPricePerKg(10);
		
		Order order2 = new Order();
		order2.setOrderQuantity(20);
		order2.setPricePerKg(10);
		
		Order order3 = new Order();
		order3.setOrderQuantity(50);
		order3.setPricePerKg(50);
		
		orderEntityList.add(order1);
		orderEntityList.add(order2);
		orderEntityList.add(order3);
		
		when(orderRepository.findByorderType(Constants.BUY_ORDER_TYPE)).thenReturn(orderEntityList);
		List<OrderDto> orderDTOList = orderService.getOrders(Constants.BUY_ORDER_TYPE);
		
		Assert.assertEquals(50,orderDTOList.get(0).getOrderQuantity(), 0);
		Assert.assertEquals(30,orderDTOList.get(1).getOrderQuantity(), 0);

	}
	
	@Test
	public void testGetSellOrders(){
		
		List<Order> orderEntityList = new ArrayList<>();
		
		Order order1 = new Order();
		order1.setOrderQuantity(10);
		order1.setPricePerKg(10);
		
		Order order2 = new Order();
		order2.setOrderQuantity(20);
		order2.setPricePerKg(10);
		
		Order order3 = new Order();
		order3.setOrderQuantity(50);
		order3.setPricePerKg(50);
		
		orderEntityList.add(order1);
		orderEntityList.add(order2);
		orderEntityList.add(order3);
		
		when(orderRepository.findByorderType(Constants.SELL_ORDER_TYPE)).thenReturn(orderEntityList);
		List<OrderDto> orderDTOList = orderService.getOrders(Constants.SELL_ORDER_TYPE);
		
		Assert.assertEquals(30,orderDTOList.get(0).getOrderQuantity(), 0);
		Assert.assertEquals(50,orderDTOList.get(1).getOrderQuantity(), 0);

	}
	
}
