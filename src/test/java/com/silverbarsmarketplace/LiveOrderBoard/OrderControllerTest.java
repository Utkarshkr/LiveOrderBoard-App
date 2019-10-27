package com.silverbarsmarketplace.LiveOrderBoard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silverbarsmarketplace.application.LiveOrderBoardApplication;
import com.silverbarsmarketplace.application.constant.Constants;
import com.silverbarsmarketplace.application.entity.Order;

/**
 * The {@code OrderControllerTest} class is a Test class for testing various controller requests.
 * @author Utkarsh Kumar
 
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LiveOrderBoardApplication.class)
@WebAppConfiguration
public class OrderControllerTest {
		
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetBuyOrders() throws Exception {
		
		MvcResult result=mockMvc.perform( MockMvcRequestBuilders
	      .get("/getbuyorders")
	      .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);		
	}
		
	@Test
	public void testGetSellOrders() throws Exception {
		
		MvcResult result=mockMvc.perform( MockMvcRequestBuilders
			      .get("/getsellorders")
			      .accept(MediaType.APPLICATION_JSON)).andReturn();
		
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);		
    }
	
	@Test
	public void testRegisterOrder() throws Exception {
		
		Order orderEntity = new Order();
		orderEntity.setOrderQuantity(10);
		orderEntity.setOrderType(Constants.SELL_ORDER_TYPE);
		orderEntity.setPricePerKg(20);
		orderEntity.setUserId("TestUser");
		
		String inputJson = mapToJson(orderEntity);

	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/registerorders")
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
	   int status = mvcResult.getResponse().getStatus();
	   Assert.assertEquals(200, status);
	 
	}
	
	@Test
	public void testCancelOrder() throws Exception {
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.delete("/cancelorder/1")).andReturn();
	    int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);		
    }
	
	public String mapToJson(Object obj) throws JsonProcessingException {
		
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	      
	}
}	
