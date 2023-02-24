package com.fast.miniproject.product.dto;


import com.fast.miniproject.product.entity.Basket;
import lombok.Getter;

@Getter
public class BasketResponseDTO {

    private Long basketId;
    private Long productId;
    private String brand;
    private String logo;
    private String name;
    private int price;
    private String detail;
    private double rate;

    public BasketResponseDTO(Basket basket) {
        this.basketId = basket.getBasketId();
        this.productId = basket.getProduct().getProductId();
        this.brand = basket.getProduct().getBrand();
        this.logo = basket.getProduct().getLogo();
        this.name = basket.getProduct().getName();
        this.price = basket.getProduct().getPrice();
        this.detail = basket.getProduct().getDetail();
        this.rate = basket.getProduct().getRate();
    }
}
