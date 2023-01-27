package com.DDN.login.security.service;

import com.DDN.login.dto.ReviewDto;
import com.DDN.login.model.Reviews;
import com.DDN.login.model.User;
import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.repository.ReviewRepository;
import com.DDN.login.repository.UserReposity;
import com.DDN.login.repository.dao.info.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private UserReposity userReposity;

    public void addReview(ReviewDto reviewDto) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(reviewDto.getProductId());
        Optional<User> user = userReposity.findById(reviewDto.getUserId());

        Reviews reviews = new Reviews();
        reviews.setRatings(reviewDto.getRatings());
        reviews.setComment(reviewDto.getComment());
        reviews.setProductInfo(productInfo.get());
        reviews.setUserInfo(user.get());

        reviewRepository.save(reviews);
    }

    public List<Reviews> getReviewsByProductId(Integer id) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(id);
        List<Reviews> reviews = reviewRepository.findByProductInfo(productInfo.get());
        return reviews;
    }
}
