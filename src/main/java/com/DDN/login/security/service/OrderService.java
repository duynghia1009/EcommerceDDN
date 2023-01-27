package com.DDN.login.security.service;

import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.repository.OrderItemRepository;
import com.DDN.login.repository.OrderRepository;
import com.DDN.login.repository.ProductRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.dto.CartItemDto;
import com.DDN.login.dto.CheckoutItemDto;
import com.DDN.login.dto.OrderListDto;
import com.DDN.login.exception.OrderNotFoundException;
import com.DDN.login.exception.ProductNotExistException;
import com.DDN.login.model.Order;
import com.DDN.login.model.OrderItem;
import com.DDN.login.model.Product;
import com.DDN.login.model.User;
import com.DDN.login.repository.dao.info.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserReposity userReposity;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    public List<Order> listOrders(User user) {

      return orderRepository.findByUser(user);

    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public List<OrderListDto> listOrdersAdmin() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderListDto> orderListDtos = new ArrayList<>();
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
            orderListDtos.add(orderDtos);
        }
        return orderListDtos;
    }

    public Order getOrder(int orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not Found");
    }

    public void placeOrder(CheckoutItemDto checkoutItemDto) {
        Optional<User> user = userReposity.findById(checkoutItemDto.getUserId());

        List<CartItemDto> cartItemDtoList = checkoutItemDto.getCartItemDtos();

        Order newOrder = new Order();
        newOrder.setCreatedAt(new Date());
        newOrder.setUser(user.get());
        newOrder.setTotalPrice(checkoutItemDto.getTotalPrice());
        newOrder.setTax(checkoutItemDto.getTax());
        newOrder.setStatus(checkoutItemDto.getStatus());
        newOrder.setState(checkoutItemDto.getState());
        newOrder.setCity(checkoutItemDto.getCity());
        newOrder.setAddress(checkoutItemDto.getAddress());
        newOrder.setShippingPrice(checkoutItemDto.getShippingPrice());
        newOrder.setPaymentStatus(checkoutItemDto.getPaymentStatus());
        newOrder.setPaidAt(new Date());
        newOrder.setItemPrice(checkoutItemDto.getItemPrice());
        newOrder.setPhoneNo(checkoutItemDto.getPhoneNo());
        newOrder.setUsername(user.get().getUsername());
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            Optional<ProductInfo> product = productInfoRepository.findById(cartItemDto.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setProductInfo(product.get());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setStock(cartItemDto.getStock());
            orderItem.setOrder(newOrder);
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setImageUrl(cartItemDto.getImageUrl());
            orderItem.setName(cartItemDto.getName());
            orderItemService.addOrderedProducts(orderItem);
        }

    }

    public void deleteOrder(Integer orderId) throws OrderNotFoundException {
        if(!orderRepository.existsById(orderId)) {
            throw new ProductNotExistException("Product id is invalid: " + orderId);
        }
        Optional<Order> order = orderRepository.findById(orderId);
        List<OrderItem> orderItemList = order.get().getOrderItems();
        for(int i = 0; i < orderItemList.size(); i++) {
            orderItemRepository.deleteById(orderItemList.get(i).getId());
        }
        orderRepository.deleteById(orderId);
    }

    public void updateStock(Integer productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setStock(product.get().getStock() - quantity);
        productRepository.save(product.get());
    }
}
