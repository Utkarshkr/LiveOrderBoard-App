package com.silverbarsmarketplace.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silverbarsmarketplace.application.dto.OrderDto;
import com.silverbarsmarketplace.application.entity.Order;
import com.silverbarsmarketplace.application.repository.OrderRepository;
import com.silverbarsmarketplace.application.service.OrderService;

/**
 * The {@code OrderController} class is a RestController component.It has various api
 * to handle getbuyorders request, getsellorders request,registerorders request and
 * cancelorder request.
 * @author Utkarsh Kumar
*/

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;

	 /**
	 * GET request for fetching Buy Orders.
	 * @return A list of Buy orders with orders of the same price merged together.
	 *         Orders will be sorted with highest price orders first.
	 */
	@GetMapping("/getbuyorders")
	public List<OrderDto> displayBuyOrder() {
		return orderService.getBuyOrders();
	}
	
	
	 /**
	 * GET request for fetching Sell Orders.
	 * @return A list of Sell orders with orders of the same price merged together.
	 * Orders will be sorted with lowest price orders first.
	 */
	@GetMapping("/getsellorders")
	public List<OrderDto> displaySellOrder() {
		return orderService.getSellOrders();
	}
	
    /**
	 * POST request for registering a new order.
	 * @return An order which gets successfully registered.
	 */
	@RequestMapping(value="/registerorders",method=RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public Order addOrder(@RequestBody OrderDto order) {
		return orderService.postOrders(order);
    }
	
	 /**
	 * DELETE request for cancelling a registered order.
     */	
   @DeleteMapping("/cancelorder/{id}")
   public void cancelOrder(@PathVariable(value = "id") int id){
	    orderService.deleteOrder(id);
	}
   
}
