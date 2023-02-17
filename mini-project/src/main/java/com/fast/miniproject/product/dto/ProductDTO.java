package com.fast.miniproject.product.dto;

import com.fast.miniproject.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {


    private int price;

    private String brand;

    private String logo;

    private String name;

    private double rate;

    private String detail;
    private Long productId;

    public ProductDTO(Product product){
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.logo = product.getLogo();
        this.name = product.getName();
        this.rate = product.getRate();
        this.detail = product.getDetail();
        this.productId=product.getProductId();
    }

}
