package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity extends BaseTimeEntity {
    @CreatedBy // Entity를 생성 할때 등록자 id를 기록하곘습니다.
    @Column(updatable =false)
    private  String createdBy ;

    @LastModifiedBy // Entity를 수정할 때 수정자 id 를 기록하겠습니다.
    private String modifiedBy ; //낙타봉 표기법
}
