package com.DDN.login.repository;


import com.DDN.login.model.Reviews;
import com.DDN.login.model.info.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByProductInfo(ProductInfo productInfo);
}
