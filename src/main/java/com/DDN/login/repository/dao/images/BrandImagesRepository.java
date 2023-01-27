package com.DDN.login.repository.dao.images;

import com.DDN.login.model.images.BrandImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandImagesRepository extends JpaRepository<BrandImages, Integer> {
    @Query(value = "SELECT DISTINCT b FROM BrandImages b")
    List<BrandImages> getAllData();

}
