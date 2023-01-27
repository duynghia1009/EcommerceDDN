package com.DDN.login.security.service;

import com.DDN.login.repository.ProductRepository;
import com.DDN.login.repository.ReviewRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.dto.ProductDto;
import com.DDN.login.dto.ReviewDto;
import com.DDN.login.exception.ProductNotExistException;
import com.DDN.login.model.Category;
import com.DDN.login.model.Product;
import com.DDN.login.model.Reviews;
import com.DDN.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserReposity userReposity;
    @Autowired
    private ReviewRepository reviewRepository;
//
//    public List<ProductDto> listProducts(){
//        List<Product> products = productRepository.findAll();
//        List<ProductDto> productDtos = new ArrayList<>();
//        for (Product product: products){
//            ProductDto productDto = getDtoFromProduct(product);
//            productDtos.add(productDto);
//        }
//        return productDtos;
//    }

    public void deleteProduct(int productId) throws ProductNotExistException {
        if(!productRepository.existsById(productId)) {
            throw new ProductNotExistException("Product id is invalid: " + productId);
        }
        productRepository.deleteById(productId);
    }

//    public static ProductDto getDtoFromProduct(Product product){
//        ProductDto productDto = new ProductDto(product);
//        return productDto;
//    }

    public static Product getProductFromDto(ProductDto productDto, Category category){
        Product product = new Product(productDto,category);
        return  product;
    }

    public void addProduct(ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto,category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productId, ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto,category);
        product.setId(productId);
        productRepository.save(product);
    }

//    public ProductDto getProductById(Integer productId) throws ProductNotExistException {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//        if(!optionalProduct.isPresent()){
//            throw new ProductNotExistException("Product id is invalid: " + productId);
//        }
//        Integer ratingSum = 0;
//        List<Reviews> reviewsList = optionalProduct.get().getReviews();
//        for(Reviews reviews : reviewsList) {
//            ratingSum += reviews.getRatings();
//        }
//        if(reviewsList.toArray().length != 0){
//            optionalProduct.get().setRatings(ratingSum / reviewsList.toArray().length);
//        }
//
//        ProductDto productDto = getDtoFromProduct(optionalProduct.get());
//        return productDto;
//    }

    public void createReviews(ReviewDto reviewDto) {
        Optional<User> user = userReposity.findById(reviewDto.getUserId());
        Optional<Product> product = productRepository.findById(reviewDto.getProductId());

        Reviews reviews = new Reviews();
        reviews.setComment(reviewDto.getComment());
        reviews.setRatings(reviewDto.getRatings());
//        reviews.setProduct(product.get());
//        reviews.setUser(user.get());
        reviewRepository.save(reviews);
    }

}
