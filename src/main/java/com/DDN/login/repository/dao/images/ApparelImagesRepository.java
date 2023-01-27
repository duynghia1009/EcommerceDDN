package com.DDN.login.repository.dao.images;

import com.DDN.login.model.images.ApparelImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApparelImagesRepository extends JpaRepository<ApparelImages, Integer> {
    @Query(value = "SELECT DISTINCT c FROM ApparelImages c")
    List<ApparelImages> getAllData();
}
