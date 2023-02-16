package com.fast.miniproject.product.dto;


import com.fast.miniproject.product.entity.Basket;
import com.fast.miniproject.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketAddRequestDTO {
    private long productId;

}
