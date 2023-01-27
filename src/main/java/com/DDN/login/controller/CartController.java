package com.DDN.login.controller;

import com.DDN.login.common.ApiResponse;
import com.DDN.login.exception.AuthenticationFailException;
import com.DDN.login.exception.CartItemNotExistException;
import com.DDN.login.security.service.AuthenticationService;
import com.DDN.login.security.service.CartService;
import com.DDN.login.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;


//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addTocart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) throws ProductNotExistException, AuthenticationFailException {
//        authenticationService.authenticate(token);
//        User user = authenticationService.getUser(token);
//        Product product = productService.getProductById(addToCartDto.getProductId());
//        System.out.println("product to add" + product.getName());
//        cartService.addToCart(addToCartDto,product,user);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Add to Cart"), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException{
//        authenticationService.authenticate(token);
//        User user = authenticationService.getUser(token);
//        CartDto cartDto = cartService.listCartItems(user);
//        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
//    }

//    @PutMapping("/update/{cartItemId}")
//    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
//                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistException{
//        authenticationService.authenticate(token);
//        User user = authenticationService.getUser(token);
//        Product product = productService.getProductById(cartDto.getProductId());
//        cartService.updateCartItem(cartDto,user,product);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemID") int cartItemId,
                                                      @RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException{
        authenticationService.authenticate(token);
        long userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(cartItemId,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
