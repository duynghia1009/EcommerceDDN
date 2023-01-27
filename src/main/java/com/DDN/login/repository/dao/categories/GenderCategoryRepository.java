package com.DDN.login.repository.dao.categories;

import com.DDN.login.model.categories.GenderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderCategoryRepository extends JpaRepository<GenderCategory, Integer> {
    @Query(value="SELECT g from GenderCategory g")
    List<GenderCategory> getAllData();

    GenderCategory findByType(String gender);
}
