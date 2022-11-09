package com.shopping.entity;


import com.shopping.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter@Getter@ToString
public class Order extends BaseEntity { // order01
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // order01

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id") // Cart01
    private Member member;//Cart가 Member를 참조하고 있습니다.

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate ;

//    private LocalDateTime orderDate ;
//    private LocalDateTime regDate ;
//    private LocalDateTime updateDate ;

    // order02) 양 방향 매핑을 위하여 추가됨
    //1 개의 'Order'에는 여러 개의 'OrderProduct'가 담길수 있습니다.
    // "order"는 多 쪽에 있는 해당 변수의 이름
    // Order03) for cascade 옵션
    // Order04) for cascade 옵션
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // order07)
    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }


    // order07)
    public static Order createOrder(Member member,List<OrderProduct> orderProductList){
        Order order = new Order() ;
        order.setMember(member); // 주문자가 누구인지 지정

        //  주문에 담길 모든 상품 정보 등록
        for(OrderProduct orderProduct : orderProductList){
            order.addOrderProduct(orderProduct);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order ;
    }


    // order07)
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getTotalPrice();

        }
        return totalPrice;
    }
        // order07) 주문 상태를 '취소'로 변경하고, 주문 취소된 수량을 재고에 더 해 줍니다.
        public void cancelOrder(){
            this.orderStatus = OrderStatus.CANCEL;
            // 이번 주문시 주문헀던 모든 상품들에 대한 재고를 각각 더해 줘야 합니다.
            for (OrderProduct bean : this.orderProducts){
                bean.cancel();
            }
        }
    }
