package com.fast.miniproject.product.dto;

import com.fast.miniproject.product.entity.Orders;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class OrderHistory {
    private Long orderId;
    private LocalDateTime purchaseDate;
    ArrayList<PurchasedProductDto> purchasedProductList;

    public OrderHistory(ArrayList<PurchasedProductDto> purchasedProducts, Orders orders) {
        this.orderId = orders.getOrderId();
        this.purchaseDate = orders.getPurchaseDate();
        this.purchasedProductList = purchasedProducts;
    }

}
