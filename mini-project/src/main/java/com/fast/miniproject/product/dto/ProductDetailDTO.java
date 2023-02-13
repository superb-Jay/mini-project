package com.fast.miniproject.product.dto;

import com.fast.miniproject.product.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
public class ProductDetailDTO {

    private Long productId;
    private int price;
    private String brand;
    private String logo;
    private String name;
    private double rate;
    private String detail;

    public ProductDetailDTO(Product product){
        this.productId=product.getProductId();
        this.price=product.getPrice();
        this.brand=product.getBrand();
        this.logo=product.getLogo();
        this.name=product.getName();
        this.rate=product.getRate();
        this.detail=product.getDetail();
    }
}
