package com.DDN.login.security.service;

import com.DDN.login.repository.WishListRepository;
import com.DDN.login.model.WishList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    public void createdWishList(WishList wishList){
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Long userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
