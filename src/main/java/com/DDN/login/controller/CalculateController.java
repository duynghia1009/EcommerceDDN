package com.DDN.login.controller;

import com.DDN.login.repository.OrderRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.repository.dao.info.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/calculate")
public class CalculateController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private UserReposity userReposity;

    @GetMapping("/getCount")
    public ResponseEntity<Map<String,Object>> getCountAll() {
        Map<String, Object> response = new HashMap<>();
        Long countProduct = productInfoRepository.count();
        Long countOrder = orderRepository.count();
        Long countUser = userReposity.count();
        response.put("countProduct", countProduct);
        response.put("countOrder", countOrder);
        response.put("countUser", countUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
