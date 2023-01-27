package com.DDN.login.repository.dao.categories;

import com.DDN.login.model.categories.PriceRangeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRangeCategoryRepository extends JpaRepository<PriceRangeCategory, Integer> {
    @Query(value = "SELECT p FROM PriceRangeCategory p")
    List<PriceRangeCategory> getAllData();

    PriceRangeCategory findByType(String type);
}
