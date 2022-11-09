package com.shopping.entity;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderProductDto {
    private String name ; // 상품명
    private int count ; // 주문 수량
    private int orderPrice ; // 주문 금액
    private String imageUrl ; // 상품 이미지

    public OrderProductDto(OrderProduct orderProduct, String imageUrl) {
        this.name = orderProduct.getProduct().getName() ;
        this.count = orderProduct.getCount();
        this.orderPrice = orderProduct.getOrderPrice();
        this.imageUrl = imageUrl ;
    }
}
