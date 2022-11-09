package com.shopping.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public  class PersonDto {
    private String id ;
    private String name ;
    private String address ;
    private Integer salary ;
    private Integer age ;
}