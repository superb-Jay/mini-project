package com.fast.miniproject.product.dto;


import lombok.Getter;

import com.fast.miniproject.product.entity.Basket;

@Getter
public class BasketResponseDTO {

    private Long basketId;
    private Long productId;
    private String brand;
    private String logo;
    private String name;
    private int price;

    public BasketResponseDTO(Basket basket) {
        this.basketId = basket.getBasketId();
        this.productId = basket.getProduct().getProductId();
        this.brand = basket.getProduct().getBrand();
        this.logo = basket.getProduct().getLogo();
        this.name = basket.getProduct().getName();
        this.price = basket.getProduct().getPrice();
    }
}
