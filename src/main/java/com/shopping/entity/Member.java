package com.shopping.entity;


import com.shopping.constant.Role;
import com.shopping.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter@Setter@ToString
public class Member extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;

    private String name ;

    @Column(unique = true) // 실질적인 프라이머리 키
    private String email ;
    private String password ;
    private String address ;

    @Enumerated(EnumType.STRING)
    private Role role ; // 일반 사용자 ,관리자 모드 구분

    //풀 화면에서 넘어오는 dto 객체를 이용하여 해당 회원에 대한 비번 암호화를 처리해주는 메소드입니다.
    public static Member createMember(MemberFormDto dto, PasswordEncoder passwordEncoder){
        Member member = new Member() ;

        member.setName(dto.getName());
        member.setAddress(dto.getAddress());
        member.setEmail(dto.getEmail());
        member.setRole(Role.USER);//차후 관리자와 구분 필요~~
        //member.setId();

        String password = passwordEncoder.encode(dto.getPassword()) ; // 비번 암호화
        member.setPassword(password);

        return member ;


    }

}
