package com.DDN.login.security.service;

import com.DDN.login.model.Order;
import com.DDN.login.repository.OrderRepository;
import com.DDN.login.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    private OrderRepository orderRepository;

    public ByteArrayInputStream load() {
        List<Order> orders = orderRepository.findAll();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(orders);
        return in;
    }
}
