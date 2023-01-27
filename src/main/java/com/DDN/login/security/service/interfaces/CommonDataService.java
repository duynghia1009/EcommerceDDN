package com.DDN.login.security.service.interfaces;

import com.DDN.login.dto.ProductDto;
import com.DDN.login.dto.filter.BrandsAndApparelsAndGenderDTO;
import com.DDN.login.dto.filter.ProductInfoDTO;
import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.payload.filter.FilterAttributesResponse;
import com.DDN.login.payload.filter.HomeTabsDataResponse;
import com.DDN.login.payload.filter.MainScreenResponse;
import com.DDN.login.payload.filter.SearchSuggestionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;


public interface CommonDataService {
    MainScreenResponse getHomeScreenData(String apiName);

    FilterAttributesResponse getFilterAttributesByProducts(String queryParams);

    ProductInfoDTO getProductsByCategories(String queryParams);

    HashMap<Integer, ProductInfo> getProductsById(String queryParams);

    HomeTabsDataResponse getBrandsAndApparelsByGender(String apiName);

    SearchSuggestionResponse getSearchSuggestionList();

    void addProduct(ProductDto productDto, MultipartFile[] file);

     BrandsAndApparelsAndGenderDTO getCategoryList();

     List<ProductInfo> getAllProduct();
}
