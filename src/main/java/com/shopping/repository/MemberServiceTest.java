package com.shopping.repository;

import com.shopping.Service.MemberService;
import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional // 테스트가 완료되고 나면 자동으로 롤백 이 됩니다...
public class MemberServiceTest {
    @Autowired
    MemberService memberService ;

    @Autowired(required = false)
    PasswordEncoder passEncoder ;

    public Member createMember() {
        // dto는 차후에 폼 양식에서 가입하는 내용이 됩니다.
        MemberFormDto dto = new MemberFormDto();
        dto.setAddress("우리집 어항");
        dto.setName("고요");
        dto.setPassword("1234");
        dto.setEmail("asdf@naver.com");

        return Member.createMember(dto, passEncoder);

    }
        @Test
        @DisplayName("가즈아!!!!!!!!!!!!!")
        public  void saveMember(){
        // member는 폼 양식에서 기입한 내용입니다.
            Member member = this.createMember() ;

           // savedMember는 JPA를 이용하여 실제 데이터 베이스에 들어간 내용입니다.
            Member savedMember = this.memberService.saveMember(member) ;


            // Assertions.assertEquals(before, after) : 두 개의 값을 비교합니다.
            Assertions.assertEquals(member.getEmail(), savedMember.getEmail());
            Assertions.assertEquals(member.getAddress(), savedMember.getAddress());
            Assertions.assertEquals(member.getName(), savedMember.getName());
            Assertions.assertEquals(member.getRole(), savedMember.getRole());
            Assertions.assertEquals(member.getPassword(), savedMember.getPassword());


        }
        @Test
        @DisplayName("겹치는건 내 뱃살 뿐이야..")
        public void saveDuqlicatedTest() {
             Member member01 = this.createMember() ; // 이미 가입된 회원 정보
             Member member02 = this.createMember() ; // 이번에 가입할 회원 정보

            this.memberService.saveMember(member01) ;


            Throwable err = Assertions.assertThrows(IllegalStateException.class, () -> {this.memberService.saveMember(member02) ;

            });

            System.out.println("너 뒤지면 다 나와 ");
            Assertions.assertEquals("있다고 조카 크래파스야",err.getMessage());
            err.printStackTrace();

        }
    }



