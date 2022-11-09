package com.shopping.repository;

import com.shopping.entity.Saram;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaramRepositoryTest {
    @Autowired
    SaramRepository saramRepository ;

    @Test
    @DisplayName("테스트 용")
    public void createSaramRempositoryTest(){
        Saram saram= new Saram() ;
        saram.setName("이순신");
        saram.setId("lee");
        saram.setAddress("용산구 이태원동 ");
        saram.setSalary(12345);

        Saram savedItem = saramRepository.save(saram) ;
        System.out.println(savedItem.toString());
    }

}
