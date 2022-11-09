package com.shopping.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartProductDto { //CartProductDto01
    @NotNull(message = " 상품 아이디는 필수 입력 값입니다.")
    private  Long productId ; // CartProductDto 01
    @Min(value = 1, message = "상품은 최소 1개 이상 담아 주셔야 합니다. " )
    private int count ; // CartProductDto01
}
