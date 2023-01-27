package com.DDN.login.repository.dao.categories;

import com.DDN.login.model.categories.ApparelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApparelCategoryRepository extends JpaRepository<ApparelCategory, Integer> {
    @Query(value="SELECT c FROM ApparelImages c where c.apparelCategory.type=?1 and" +
            " c.genderCategory.type=?2 ")
    ApparelCategory findByClothesTypeAndGender(String clothesType, String gender);

    @Query(value = "SELECT c FROM ApparelCategory c")
    List<ApparelCategory> getAllData();

    ApparelCategory findByType(String title);
}
