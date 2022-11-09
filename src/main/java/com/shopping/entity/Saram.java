package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sarams")

public class Saram {
    @Id
    @GenericGenerator(name ="id", strategy = "auto")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id ;

    @Column(nullable = false, length = 50)
    private String name ;

    @Column(nullable = false)
    private  String address ;

    @Column(nullable = false, name = "salary")
    private  Integer salary ;
}