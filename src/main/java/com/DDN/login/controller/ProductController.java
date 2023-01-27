package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.dto.ReviewDto;
import com.DDN.login.model.Product;
import com.DDN.login.repository.ProductRepository;
import com.DDN.login.security.service.CategoryService;
import com.DDN.login.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductRepository productRepository;

//
//    @GetMapping("/")
//    public ResponseEntity<List<ProductDto>> getProducts(){
//        List<ProductDto> body = productService.listProducts();
//        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
//        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
//        if(!optionalCategory.isPresent()){
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
//        }
//        Category category = optionalCategory.get();
//        productService.addProduct(productDto,category);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
//    }

//    @PutMapping("/update/{productID}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody ProductDto productDto){
//        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
//        if(!optionalCategory.isPresent()){
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
//        }
//        Category category = optionalCategory.get();
//        productService.updateProduct(productID,productDto,category);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
//    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            if(sort[0].contains(",")) {
                for(String sortOrder: sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));

                }
            } else {
                orders.add(new Sort.Order(getSortDirection(sort[1]),sort[0]));
            }
            List<Product> products = new ArrayList<Product>();
            Pageable pagingSort = PageRequest.of(page,size,Sort.by(orders));
            Page<Product> pageProducts;
            if(name == null)
                pageProducts = productRepository.findAll(pagingSort);
            else
                pageProducts = productRepository.findByNameContaining(name, pagingSort);

            products = pageProducts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("products", products);
            response.put("currentPage", pageProducts.getNumber());
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDto> findById(@PathVariable Integer id){
//        ProductDto productDto = productService.getProductById(id);
//        return new ResponseEntity<>(productDto, HttpStatus.OK);
//    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") int productId){
            productService.deleteProduct(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been removed"), HttpStatus.OK);
    }

    @PostMapping("/newReviews")
    public ResponseEntity<ApiResponse> createReviews(@RequestBody ReviewDto reviewDto) {
        productService.createReviews(reviewDto);
        return new ResponseEntity<>(new ApiResponse(true, "Review has been created"), HttpStatus.OK);
    }




}
