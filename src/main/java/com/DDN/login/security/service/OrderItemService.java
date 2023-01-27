package com.DDN.login.security.service;


import com.DDN.login.repository.OrderItemRepository;
import com.DDN.login.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public void addOrderedProducts(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
