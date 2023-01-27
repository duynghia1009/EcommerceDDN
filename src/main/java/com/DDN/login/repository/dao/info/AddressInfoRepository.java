package com.DDN.login.repository.dao.info;

import com.DDN.login.model.info.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfo, Integer> {

}
