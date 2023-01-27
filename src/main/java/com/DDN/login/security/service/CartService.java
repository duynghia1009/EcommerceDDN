package com.DDN.login.security.service;

import com.DDN.login.repository.CartRepository;
import com.DDN.login.dto.AddToCartDto;
import com.DDN.login.exception.CartItemNotExistException;
import com.DDN.login.model.Cart;
import com.DDN.login.model.Product;
import com.DDN.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartService() {
    }

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

//    public CartDto  listCartItems(User user){
//        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
//        List<CartItemDto> cartItems = new ArrayList<>();
//        for(Cart cart:cartList){
//            CartItemDto cartItemDto = getDtoFormatCart(cart);
//            cartItems.add(cartItemDto);
//        }
//        double totalPrice = 0;
//        for (CartItemDto cartItemDto:cartItems){
//            totalPrice += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
//        }
//        CartDto cartDto = new CartDto(cartItems,totalPrice);
//        return cartDto;
//    }

//    public static CartItemDto getDtoFormatCart(Cart cart){
//        CartItemDto cartItemDto = new CartItemDto(cart);
//        return  cartItemDto;
//    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product){
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id, long userId) throws CartItemNotExistException {
        if(!cartRepository.existsById(id)){
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        }
        cartRepository.deleteById(id);
    }

//    public void deleteCartItems(int userId){
//        cartRepository.deleteAll();
//    }

    public void deleteUserCartItems(User user){
        cartRepository.deleteByUser(user);
    }

}
