package com.fast.miniproject.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "purchased_product")
public class PurchasedProduct {

    @Id
    @Column(name = "purchased_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchasedProductId;

    @Column(name = "purchased_product_price")
    private int purchasedProductPrice;

    @Column(name = "purchased_product_brand")
    private String purchasedProductBrand;

    @Column(name = "purchased_product_logo")
    private String purchasedProductLogo;

    @Column(name = "purchased_product_name")
    private String purchasedProductName;

    @Column(name = "purchased_product_rate")
    private double purchasedProductRate;

    @Column(name = "purchased_product_rp_number")
    private String purchasedProductDetail;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "orders")
    private Orders orders;

    public PurchasedProduct(Product product) {
        this.purchasedProductPrice = product.getPrice();
        this.purchasedProductBrand = product.getBrand();
        this.purchasedProductLogo = product.getLogo();
        this.purchasedProductName = product.getName();
        this.purchasedProductRate = product.getRate();
        this.purchasedProductDetail = product.getDetail();
        this.product = product;
    }
}
