package com.fast.miniproject.product.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_price")
    private int price;

    @Column(name = "product_brand")
    private String brand;

    @Column(name = "product_logo")
    private String logo;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_rate")
    private double rate;

    @Column(name = "detail")
    private String detail;

    @Builder
    public Product(int price, String brand, String logo, String name, double rate, String detail) {
        this.price = price;
        this.brand = brand;
        this.logo = logo;
        this.name = name;
        this.rate = rate;
        this.detail = detail;
    }
}
