package com.fast.miniproject.product.dto;

import com.fast.miniproject.product.entity.Wishlist;
import lombok.Getter;

@Getter
public class WishlistResponseDTO {
    private Long wishlistId;
    private Long productId;
    private String brand;
    private String logo;
    private String name;
    private int price;


    public WishlistResponseDTO(Wishlist wishlist) {
        this.wishlistId = wishlist.getWishlistId();
        this.productId = wishlist.getProduct().getProductId();
        this.brand = wishlist.getProduct().getBrand();
        this.logo = wishlist.getProduct().getLogo();
        this.name = wishlist.getProduct().getName();
        this.price = wishlist.getProduct().getPrice();
    }
}
