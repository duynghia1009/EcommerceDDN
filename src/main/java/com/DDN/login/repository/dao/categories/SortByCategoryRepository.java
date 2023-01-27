package com.DDN.login.repository.dao.categories;

import com.DDN.login.model.categories.SortByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortByCategoryRepository extends JpaRepository<SortByCategory, Integer> {
    @Query(value = "SELECT s FROM SortByCategory s")
    List<SortByCategory> getAllData();

    SortByCategory findByType(String type);
}
