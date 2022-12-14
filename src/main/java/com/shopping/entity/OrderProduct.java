package com.shopping.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@Setter@Getter@ToString
public class OrderProduct extends BaseEntity{ // order01
    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ; // order01

    @ManyToOne(fetch =FetchType.LAZY ) // 한개의 상품은 여러 개의 주문에 포함이 될수 있습니다.//OrderProduct02
    @JoinColumn(name = "product_id")
    private  Product product ;

    @ManyToOne(fetch = FetchType.LAZY) // 한개의 상품은 여러 개의 주문에 포함이 될수 있습니다.//OrderProduct02
    @JoinColumn(name = "order_id")
    private  Order order;

    private  int count ; // 수량
    private  int orderPrice ; //주문 가격

//    private LocalDateTime regDate ; // 작성 일자
//    private LocalDateTime updateDate ; // 수정 일자


    //  Order Product04 ) 주문할 상춤 정보와 주문 수량을 이용하여 OrderProduct 객체를 생성해 줍니다
    public  static  OrderProduct createOrderProduct(Product product, int count){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setCount(count);
        orderProduct.setOrderPrice(product.getPrice());

        product.removeStock(count); // 재고 수량감소

        return orderProduct ;

    }

    //  Order Product04) 금액 = 가격 * 수량
    public  int getTotalPrice(){
        return orderPrice * count ;
    }



    //  Order Product05) 주문 취쇠시 해당 상품의 재고 수량을 다시 늘려 줍니다.
    public void cancel(){
        this.getProduct().addStock(count);

    }


}



