package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.dto.*;
import com.DDN.login.exception.AuthenticationFailException;
import com.DDN.login.exception.OrderNotFoundException;
import com.DDN.login.exception.ProductNotExistException;
import com.DDN.login.model.Order;
import com.DDN.login.model.OrderItem;
import com.DDN.login.model.User;
import com.DDN.login.repository.OrderItemRepository;
import com.DDN.login.repository.OrderRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.security.service.AuthenticationService;
import com.DDN.login.security.service.OrderService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserReposity userReposity;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;



    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<StripeResponse> checkout(@RequestBody CartDto CartDto)  throws StripeException {
        Stripe.apiKey = apiKey;
        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount(CartDto.getTotalPrice() *100L)
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        StripeResponse stripeResponse = new StripeResponse(intent.getClientSecret());
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }


    @PostMapping("/saveOrder")
    public ResponseEntity<ApiResponse> placeOrder( @RequestBody CheckoutItemDto placeOrderDto)
        throws  ProductNotExistException {
        orderService.placeOrder(placeOrderDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been replaced"), HttpStatus.CREATED);
    }


    @GetMapping("/myOrder/{userId}")
    public ResponseEntity<List<OrderListDto>> getMyOrders(@PathVariable("userId") Long userId) throws AuthenticationFailException{
        Optional<User> user = userReposity.findById(userId);
        List<Order> orderList = orderService.listOrders(user.get());

        List<OrderListDto> orderResults = new ArrayList<>();

        for(int i = 0; i < orderList.size(); i++) {
            OrderListDto orderDtos = new OrderListDto();
            orderDtos.setOrderId(orderList.get(i).getId());
            orderDtos.setTax(orderList.get(i).getTax());
            orderDtos.setAddress(orderList.get(i).getAddress());
            orderDtos.setCity(orderList.get(i).getCity());
            orderDtos.setPhoneNo(orderList.get(i).getPhoneNo());
            orderDtos.setItemPrice(orderList.get(i).getItemPrice());
            orderDtos.setPaymentStatus(orderList.get(i).getPaymentStatus());
            orderDtos.setPaidAt(orderList.get(i).getPaidAt());
            orderDtos.setTotalPrice(orderList.get(i).getTotalPrice());
            orderDtos.setStatus(orderList.get(i).getStatus());
            orderDtos.setCreatedAt(orderList.get(i).getCreatedAt());
            orderDtos.setOrderItem(orderList.get(i).getOrderItems());
            orderResults.add(orderDtos);
        }
        return new ResponseEntity<>(orderResults,HttpStatus.OK);

    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderDetails(@PathVariable("orderId") Integer orderId) throws OrderNotFoundException{
        try {
            Order orderDto = orderService.getOrder(orderId);
            return new ResponseEntity<>(orderDto,HttpStatus.OK);
        } catch(OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orderItem/{orderId}")
    public ResponseEntity<Object> getOrderItemDetails(@PathVariable("orderId") Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        List<OrderItem> orderItems = orderItemRepository.findByOrder(order.get());

        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

//    @GetMapping("/allOrders")
//    public ResponseEntity<List<OrderListDto>> getAllOrders()
//        throws OrderNotFoundException {
//            List<OrderListDto> orderListDtos = orderService.listOrdersAdmin();
//            return new ResponseEntity<>(orderListDtos, HttpStatus.OK);
//    }

    @GetMapping("/allOrders")
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam int page, @RequestParam int pageSize) {

        List<Order> orders = new ArrayList<Order>();
        Pageable pagingSort = PageRequest.of(page,pageSize);
        Page<Order> pageOrders;

        pageOrders = orderRepository.findAll(pagingSort);

        orders = pageOrders.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        response.put("currentPage", pageOrders.getNumber());
        response.put("totalItems", pageOrders.getTotalElements());
        response.put("totalPages", pageOrders.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody OrderDto orderDto)
        throws OrderNotFoundException {
            Order order = orderService.getOrder(orderId);
            if(order.getStatus() == "Delivered"){
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "You have already delivered this order"), HttpStatus.OK);
            }
            if(orderDto.getStatus().equals("Shipped")) {
                List<OrderItem> orderItemList = order.getOrderItems();
                for (OrderItem orderItem : orderItemList) {
                    orderService.updateStock(orderItem.getProductInfo().getId(), orderItem.getQuantity());
                }
            }
            if(orderDto.getStatus() == "Delivered"){
                order.setUpdatedAt(new Date());
            }
            order.setStatus(orderDto.getStatus());
            orderService.saveOrder(order);
        return new ResponseEntity<>(new ApiResponse(true, "You have changed status order"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been removed"), HttpStatus.OK);
    }

}

