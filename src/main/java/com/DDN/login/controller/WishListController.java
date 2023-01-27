package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.model.Product;
import com.DDN.login.model.User;
import com.DDN.login.model.WishList;
import com.DDN.login.security.service.AuthenticationService;
import com.DDN.login.security.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

//    @GetMapping("/{token}")
//    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token){
//        long userId = authenticationService.getUser(token).getId();
//        List<WishList> body = wishListService.readWishList(userId);
//        List<ProductDto> productDtos = new ArrayList<ProductDto>();
//        for (WishList wishList: body){
//            productDtos.add(ProductService.getDtoFromProduct(wishList.getProduct()));
//        }
//        return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token){
        authenticationService.authenticate(token);
        User user =  authenticationService.getUser(token);
        WishList wishList = new WishList(user,product);
        wishListService.createdWishList(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
    }
}
