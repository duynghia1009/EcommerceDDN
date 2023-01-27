package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.dto.CardToken;
import com.DDN.login.dto.PaymentStatus;
import com.DDN.login.dto.ProductDto;
import com.DDN.login.dto.filter.BrandsAndApparelsAndGenderDTO;
import com.DDN.login.dto.filter.ProductInfoDTO;
import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.payload.filter.FilterAttributesResponse;
import com.DDN.login.payload.filter.HomeTabsDataResponse;
import com.DDN.login.payload.filter.MainScreenResponse;
import com.DDN.login.payload.filter.SearchSuggestionResponse;
import com.DDN.login.repository.dao.categories.ApparelCategoryRepository;
import com.DDN.login.repository.dao.categories.PriceRangeCategoryRepository;
import com.DDN.login.repository.dao.categories.ProductBrandCategoryRepository;
import com.DDN.login.repository.dao.info.ProductInfoRepository;
import com.DDN.login.security.service.interfaces.CommonDataService;
import com.DDN.login.security.service.interfaces.LoadFakeDataService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/commondata")
public class CommonDataController {

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Autowired
    CommonDataService commonDataService;

    @Autowired
    LoadFakeDataService loadFakeDataService;

    @Autowired
    private ApparelCategoryRepository apparelCategoryRepository;
    @Autowired
    private ProductBrandCategoryRepository productBrandCategoryRepository;
    @Autowired
    private PriceRangeCategoryRepository priceRangeCategoryRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;


    @PostMapping("/payment")
    public ResponseEntity<PaymentStatus> chargeCustomer(@RequestBody CardToken cardToken) {

        Stripe.apiKey = apiKey;
        Stripe.setMaxNetworkRetries(2);

        Charge charge;
        PaymentStatus paymentStatus;

        try {
            ChargeCreateParams params =
                    ChargeCreateParams.builder()
                            .setAmount(cardToken.getAmount())
                            .setCurrency(cardToken.getCurrency())
                            .setDescription("Shopper Buy")
                            .setSource(cardToken.getId())
                            .build();

            charge = Charge.create(params);
           
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            paymentStatus = new PaymentStatus(timestamp.getTime(), false,
                    charge.getId(),
                    charge.getBalanceTransaction(),
                    charge.getReceiptUrl()
            );

        } catch (Exception e) {
            paymentStatus = new PaymentStatus();
            paymentStatus.setPayment_failed(true);
            System.out.println("Something went wrong with Stripe API");
            return ResponseEntity.badRequest().body(paymentStatus);
        }

        System.out.println("Payment is successful....");
//        System.out.println(paymentStatus.get();
        return ResponseEntity.ok(paymentStatus);
    }


    @GetMapping(value = "/products", params = "q")
    public ResponseEntity<?> getProductsByCategories(@RequestParam("q") String queryPrams) {
        ProductInfoDTO productInfoDTO = commonDataService.getProductsByCategories(queryPrams);
        if(productInfoDTO == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }
        return ResponseEntity.ok(productInfoDTO);
    }

    @GetMapping(value = "/products", params = "product_id")
    public ResponseEntity<?> getProductsById(@RequestParam("product_id") String queryParams) {
        HashMap<Integer, ProductInfo> resultMap = commonDataService.getProductsById(queryParams);
        if(resultMap == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/home")
    public ResponseEntity<?> getMainScreenData() {
        MainScreenResponse mainScreenResponse = commonDataService.getHomeScreenData("homeAPI");
        if(mainScreenResponse == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(mainScreenResponse);
    }

    @GetMapping("/tabs")
    public ResponseEntity<?> getHomeTabsDataResponse() {
        HomeTabsDataResponse homeTabsDataResponse = commonDataService.getBrandsAndApparelsByGender("tabsAPI");
        if (homeTabsDataResponse == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(homeTabsDataResponse);
    }

    @GetMapping(value = "/filter", params = "q")
    public ResponseEntity<?> getFilterAttributesByProducts(@RequestParam("q") String queryParams) {

        // TODO: Add support for productname parameter for filter selection.
        String[] splitParams = queryParams.split("=");
        if(splitParams.length >= 1 && splitParams[0].equals("productname")){
            queryParams="category=all";
        }

        FilterAttributesResponse result = commonDataService.getFilterAttributesByProducts(queryParams);

        if (result == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/search-suggestion-list")
    public ResponseEntity<?> getSearchSuggestionList() {
        SearchSuggestionResponse searchSuggestionList = commonDataService.getSearchSuggestionList();
        if (searchSuggestionList == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(searchSuggestionList);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        BrandsAndApparelsAndGenderDTO categoryList = commonDataService.getCategoryList();
        if (categoryList == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(categoryList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse> addProduct(@RequestHeader HttpHeaders headers, @ModelAttribute ProductDto productDto){

        commonDataService.addProduct(productDto, productDto.getImages());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam int page, @RequestParam int pageSize) {
//        List<ProductInfo> productInfos = commonDataService.getAllProduct();
//        return new ResponseEntity<List<ProductInfo>>(productInfos,HttpStatus.OK);
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        Pageable pagingSort = PageRequest.of(page,pageSize);
        Page<ProductInfo> pageProducts;

        pageProducts = productInfoRepository.findAll(pagingSort);

        products = pageProducts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageProducts.getNumber());
        response.put("totalItems", pageProducts.getTotalElements());
        response.put("totalPages", pageProducts.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductInfo> findById(@PathVariable Integer id){
        Optional<ProductInfo> productDto = productInfoRepository.findById(id);
        return new ResponseEntity<>(productDto.get(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") int productId){
        productInfoRepository.deleteById(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been removed"), HttpStatus.OK);
    }
}
