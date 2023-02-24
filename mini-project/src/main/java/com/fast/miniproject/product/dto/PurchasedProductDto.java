package com.fast.miniproject.product.dto;

import com.fast.miniproject.product.entity.PurchasedProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PurchasedProductDto {

    private Long purchasedProductId;

    private int purchasedProductPrice;

    private String purchasedProductBrand;

    private String purchasedProductLogo;

    private String purchasedProductName;

    private double purchasedProductRate;

    private String purchasedProductDetail;

    private Long originalProductId;

    public PurchasedProductDto(PurchasedProduct purchasedProduct) {
        this.purchasedProductId = purchasedProduct.getPurchasedProductId();
        this.purchasedProductPrice = purchasedProduct.getPurchasedProductPrice();
        this.originalProductId = purchasedProduct.getProduct().getProductId();
        this.purchasedProductBrand = purchasedProduct.getPurchasedProductBrand();
        this.purchasedProductLogo = purchasedProduct.getPurchasedProductLogo();
        this.purchasedProductName = purchasedProduct.getPurchasedProductName();
        this.purchasedProductRate = purchasedProduct.getPurchasedProductRate();
        this.purchasedProductDetail = purchasedProduct.getPurchasedProductDetail();
    }
}
