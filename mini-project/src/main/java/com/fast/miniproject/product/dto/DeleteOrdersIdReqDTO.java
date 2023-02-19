package com.fast.miniproject.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class DeleteOrdersIdReqDTO {
    private Long orderId;
}
