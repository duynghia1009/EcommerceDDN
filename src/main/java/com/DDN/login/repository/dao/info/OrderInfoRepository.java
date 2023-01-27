package com.DDN.login.repository.dao.info;

import com.DDN.login.model.info.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo,Integer> {
}
