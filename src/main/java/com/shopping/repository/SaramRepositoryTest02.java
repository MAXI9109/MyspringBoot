package com.shopping.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.entity.QSaram;
import com.shopping.entity.Saram;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class SaramRepositoryTest02 {
    @Autowired
    SaramRepository saramRepository;

    @Test
    @DisplayName("테스트 ")
    public void createSaramRepositoryTest02() {
        String[] id = {"aaa", "bbb", "ccc", "ddd"};
        String[] Name = {"쿼카", "치와와", "곰", "골드리트리버"};
        String[] address = {"성남풀밭", "구로", "용마산불곰", "인천마계도시"};
        int[] salary = {20000, 6000, 5533, 58220};

        for (int i = 0; i < 10; i++) {
            Saram saram = new Saram();

            saram.setId(id[i % id.length] + i);
            saram.setName(Name[i % Name.length]);
            saram.setSalary(salary[i % salary.length]);
            saram.setAddress(address[i % address.length]);

            Saram savedBean = this.saramRepository.save(saram);
            System.out.println(savedBean.toString());
        }

    }
    //급여가 5800미만이고, 주소에 '포'를 포함하는 회원을 조회하되, 이름의 역순으로 출력하는 queryDsl 구문을 작성하고 테스트해보세요
    @PersistenceContext
    EntityManager em ;
    @Test
    @DisplayName("query Dsl Test01")
    public  void queryDslTest01() {


        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QSaram qbean = QSaram.saram;
        JPAQuery<Saram> query = queryFactory
                .selectFrom(qbean)
                .where(qbean.salary.lt(5800))
                .where(qbean.address.like("%" +"용" +"%"))
                .orderBy(qbean.name.desc());

        List<Saram> itemList = query.fetch();
        for(Saram bean : itemList){
            System.out.println(bean.toString());




        }
    }
    @Test
    @DisplayName("query Dsl Test01")
    public void queryDslTest02() {

        QSaram qbean = QSaram.saram ;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String address = "박" ;
        booleanBuilder.and(qbean.address.like("%"+"address"+"%"));

        int salary = 6500 ;
        booleanBuilder.and(qbean.address.eq(address));
        int pageNumber = 2 - 1 ;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber,pageSize) ;

        Page<Saram> pageList = this.saramRepository.findAll(booleanBuilder,pageable);

        System.out.println("total element : " +pageList.getTotalElements());

        List<Saram> itemList = pageList.getContent();
        for(Saram bean : itemList){
            System.out.println(qbean.toString());
        }
    }


    }

