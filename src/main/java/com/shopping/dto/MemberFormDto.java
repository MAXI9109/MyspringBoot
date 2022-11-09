package com.shopping.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


// cf) VO (Value Object), JAVA Bean
//DTO(Data Transfer Object)
// 회원 가입 화면(<form> 태그)에서 넘겨지는 파라미터를 저장할  Dto  클래스
@Getter@Setter
public class MemberFormDto {
    @NotBlank(message = "니이름 생각 안 나는 이상 적어라 --바보냐? ")
    private String name ;


    @NotEmpty(message = "입력좀해라 쫌 시키는데로  해라 짜잉나게..진짜 !!!!")
    @Length(min = 8, max = 16, message = "다른사이트랑 똒!!!같당 알지? 8~ 16 이제 눈감고 쳐도 알지 않냥?")
    private String password ;

    @Email(message = "그걸 틀리나?? 선생님들 집중해서 생각하세요~ 스튜핏~! .")
    private String email;

    @NotEmpty(message = "니가 사는그집~~  그집이 내 집이 였어야해." )
    private String address ;
}
