A # LiveOrderBoard-App
LiveOrderBoard Application for Silverbars Marketplace--

Approach :

1. Springboot framework has been used to implement this MVC application.
2. Following dependencies have been defined in POM.xml 
    spring-boot-starter-web
    spring-boot-starter-data-jpa
    spring-boot-starter-data-rest
    spring-boot-starter-test
    spring-boot-starter-security
    h2
    
3. Plugin in POM.xml: spring-boot-maven-plugin

4. Assumtions : Different interface for fetching Orders of Sell Ordertype and Buy Ordertype.

4. A controller component OrderController has been designed which receives requests for registering an order,fetching orders of SELL 
   order type,fetching orders of BUY order type,canceling a registered order and calls OrderService service methods for serving the
   requests.
   
5. A service component OrderService has been designed which gets calls from OrderController.
    
    Approach for registering an order : postOrders(OrderDto order) method has been designed which recieves an OrderDto object which is a DTO object for
                                        Order entiy and transforms it into Order entity to save it into inmemory H2 DB through repository
                                        save() method.
                                        
    Approach for canceling an order:   deleteOrder(int id) method has been designed which takes an id as argument,fethces the Order entity corresponding to the id
                                       and delete it using repository delete() method.
                                      
    Approach for fetching GET orders of SELL ordertype : getSellOrders() mehtod calls has been designed which calls getOrders(String ordertype) method sending the ordertype
                                      as SELL.It fetches all the orders of SELL order type using repository findByorderType() method.It then uses
                                      Streams collect() method,Collectors.groupingBy(),Collectors.summingLong() methods to get a map which has key as
                                      Order price and value as total quantity of orders of same price.It then sorts the map using Streams sorted() and collect()
                                      method to display the orders with Lowest prices are displayed first.Finally it transforms it into
                                      a list of OrderDto object to send response in JSON format.
                                      
   Approach for fetching GET orders of BUY ordertype : Its same as SELL ordertype except it uses Collections.reverse() method to reverse 
                                     the list to display the orders with Highest prices are displayed first.  
                                    

Steps to run->

1. Requirements : Jdk 1.8, Maven

2. Configure the project in IDE importing from github.

3. Running the application locally: To run this application on local machine execute the main method in the
 com.silverbarsmarketplace.application.LiveOrderBoardApplication class from your IDE.
 
4. URLs for api calls =

Authorisation:--

USERNAME: user
PASSWORD: COPY FROM IDE CONSOLE (sample : 95ff99fd-f804-45f0-9d11-b16b26a196e6 )

Registering a new Order = http://localhost:8090/registerorders 

Sample JSON for registering a BUY order

{
  "userId": "UTKARSH",
  "orderType": "BUY",
  "orderQuantity":20,
  "pricePerKg":20
}

Sample JSON for registering a SELL order
{
  "userId": "ASHISH",
  "orderType": "SELL",
  "orderQuantity":20,
  "pricePerKg":20
}

 Fetching all orders of Buy order type=http://localhost:8090/getbuyorders
 
 Fetching all orders of Sell order type = http://localhost:8090/getsellorders
 
 Canceling a registered order = http://localhost:8090/cancelorder/{id}
 
 



