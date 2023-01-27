package com.DDN.login.repository.dao.info;

import com.DDN.login.model.info.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Integer> {
}
