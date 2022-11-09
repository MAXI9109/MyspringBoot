package com.shopping.dto;

import com.shopping.constant.OrderStatus;
import com.shopping.entity.Order;
import com.shopping.entity.OrderProductDto;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Getter@Setter

public class OrderHistDto { // OrderHistDto 01
    private Long orderId ; // 주문 아이디(송장 번호)
    private String orderDate ; // 주문 일자
    private OrderStatus orderStatus ; // 주문상태

    private List<OrderProductDto> orderProductDtoList =new ArrayList<>();

    public OrderHistDto(Long orderId) {
        this.orderId = orderId;
    }


    public void addOrderProductDto(OrderProductDto dto){
        orderProductDtoList.add(dto) ;
    }

    public OrderHistDto(Order order) {
        this.orderId = order.getId() ;
        String pattern = "yyyy-MM-dd HH:mm"; //주문 일자 보여 주는 형식 지정성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern) ;
        this.orderDate = order.getOrderDate().format(formatter) ;
        this.orderStatus= order.getOrderStatus() ;
    }

}


