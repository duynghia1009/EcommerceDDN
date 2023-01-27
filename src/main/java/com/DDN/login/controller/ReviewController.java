package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.dto.ReviewDto;
import com.DDN.login.model.Reviews;
import com.DDN.login.repository.ReviewRepository;
import com.DDN.login.security.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addReviews(@ModelAttribute ReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<Map<String, Object>> getAllReviews(@RequestParam int page, @RequestParam int pageSize) {
        List<Reviews> reviews = new ArrayList<>();
        Pageable pagingSort = PageRequest.of(page,pageSize);
        Page<Reviews> pageReviews;

        pageReviews = reviewRepository.findAll(pagingSort);

        reviews = pageReviews.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviews);
        response.put("currentPage", pageReviews.getNumber());
        response.put("totalItems", pageReviews.getTotalElements());
        response.put("totalPages", pageReviews.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Reviews>> findReviewsById(@PathVariable Integer id) {
        List<Reviews> reviews = reviewService.getReviewsByProductId(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReviewsById(@PathVariable Integer reviewId) {
        reviewRepository.deleteById(reviewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Review has been deleted"), HttpStatus.OK);
    }

//    @PutMapping("/update/{reviewId}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("reviewId") Integer reviewId, @RequestBody ReviewDto reviewDto ) {
//
//    }

}
