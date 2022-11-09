package com.shopping.Service;

import com.shopping.entity.Member;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor // final이나 @NotNull 이 있는 변수에 생성자를 만들어 줍니다.
@Transactional // 서비스 계층에서 오류 발생시 이전 상태로 되돌려 줍니다.
public class MemberService implements UserDetailsService { // Memberservice02


    private  final  MemberRepository memberRepository ;//MemberService01

    // 회원 중복 체크 이후에 회원 정보를  저장합니다.
    public Member saveMember(Member member){// MemberService01
        this.validateDuplicateMember(member);
        return this.memberRepository.save(member)  ;
    }


    private void validateDuplicateMember(Member member){ //MemberService01
        // 동일한 이메일이 존재하는지 검사하고, 존재 하면 예외를 발생시킵니다.
        Member findMenber = memberRepository.findByEmail(member.getEmail()) ;
        if(findMenber!=null) {
            String errorMessage = "있다고 조카 크래파스야" ;
            throw new IllegalStateException(errorMessage);
        }

    }
    // loadUserByUsername() 메소드의 매개 변수에는 실질적 기본키에 해당하는 email을 넣어 주도록 합니다.
    @Override //Memberservice02
    public UserDetails loadUserByUsername(String email) throws
    UsernameNotFoundException {
        Member member = this.memberRepository.findByEmail(email) ;
        System.out.println("email");
        System.out.println(email);
        if(member == null){
            throw new UsernameNotFoundException(email) ;
        }


        // UserDetails 인터페이스의 구현체 클래스 User 객체를 생성하여 반환해 줍니다.
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString()) //  enum 타입이라서 문자로 만들어 주기 위해 문자로 처리함
                .build();
    }
}