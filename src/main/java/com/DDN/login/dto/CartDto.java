package com.DDN.login.dto;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private long totalPrice;

    public CartDto(List<CartItemDto> cartItemDtoList, long totalPrice){
        this.cartItems  = cartItemDtoList;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
