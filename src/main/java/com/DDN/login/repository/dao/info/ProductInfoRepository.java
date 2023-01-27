package com.DDN.login.repository.dao.info;

import com.DDN.login.dto.filter.SearchSuggestionForThreeAttrDTO;
import com.DDN.login.dto.filter.SearchSuggestionForTwoAttrDTO;
import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.payload.filter.FilterAttributesResponse;
import com.DDN.login.payload.filter.HomeTabsDataResponse;

import org.javatuples.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer>{
    Pair<Long, List<ProductInfo>> getProductsByCategories(HashMap<String, String> conditionMap);

    List<ProductInfo> getProductsById(String[] product_ids_str);

    FilterAttributesResponse getFilterAttributesByProducts(HashMap<String, String> conditionMap);

    HomeTabsDataResponse  getBrandsAndApparelsByGender();

    List<SearchSuggestionForThreeAttrDTO>  getGenderApparelBrandByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getGenderAndApparelByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getGenderAndBrandByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getApparelAndBrandByIdAndName();


    @Query(value = "SELECT DISTINCT p.name FROM ProductInfo p")
    List<String>  getProductByName();

    @Query(value = "SELECT p FROM ProductInfo p WHERE p.name LIKE %:name%")
    List<ProductInfo> searchByName(@Param("name") String name);


}
