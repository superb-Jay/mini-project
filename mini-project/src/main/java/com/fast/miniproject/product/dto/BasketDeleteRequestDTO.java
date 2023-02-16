package com.fast.miniproject.product.dto;


import com.fast.miniproject.product.entity.Basket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketDeleteRequestDTO {
    private long basketId;

}
