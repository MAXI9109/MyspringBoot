package com.shopping.controller;

import com.shopping.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")

public class ThymeleafExController {
    @GetMapping(value ="/ex01")
    public String thymeleafExam01(Model model) { //컨트롤러 메소드
        model.addAttribute("data", "타임 리프 1번 예시입니다.");
        //request.setAttribure("data", "타임 리프 1번 예시입니다.");
        return "thymeleafEx/viewEx01";
    }

    @GetMapping(value ="/ex02")
    public String thymeleafExam02(Model model) { //컨트롤러 메소드
        ProductDto bean = new ProductDto();
        bean.setName("사과");
        bean.setPrice(1234);
        bean.setDescription("옴뇸뇸");
        bean.setRegDate(LocalDateTime.now());

        model.addAttribute("bean", bean);

        return "thymeleafEx/viewEx02";
    }
        private List<ProductDto> getProductDtoData(int gaesu){
            // 샘플용 데이터 gaesu개 만큼 만들어서 반환해 줍니다.
            List<ProductDto> itemDtoList = new ArrayList<ProductDto>();
            for (int i = 1; i <= gaesu; i++) {
                ProductDto bean = new ProductDto();
                bean.setName("테스트 상품 " + i);
                bean.setPrice(1000 * i);
                bean.setDescription("상품 상세 설명" + i);
                bean.setRegDate(LocalDateTime.now());

                itemDtoList.add(bean);
            }
            return itemDtoList;
        }



    @GetMapping(value ="/ex03")//인터넷에 보내는 주소
    public String thymeleafExam03(Model model) { //컨트롤러 메소드
        List<ProductDto> itemDtoList = this.getProductDtoData(10);

        model.addAttribute("itemDtoList",itemDtoList);// 저장 공간에데 이름을 할당해주고
        return "thymeleafEx/viewEx03"; //보여지는 html 문서 viewEx03

    }
    @GetMapping(value ="/ex04")//인터넷에 보내는 주소
    public String thymeleafExam04(Model model) { //컨트롤러 메소드
        List<ProductDto> itemDtoList = this.getProductDtoData(5);

        model.addAttribute("itemDtoList",itemDtoList);// 저장 공간에데 이름을 할당해주고
        return "thymeleafEx/viewEx04"; //보여지는 html 문서 viewEx03

    }
    @GetMapping(value ="/ex05")//인터넷에 보내는 주소
    public String thymeleafExam05(Model model) { //컨트롤러 메소드
        List<ProductDto> itemDtoList = this.getProductDtoData(28);

        model.addAttribute("itemDtoList",itemDtoList);// 저장 공간에데 이름을 할당해주고
        return "thymeleafEx/viewEx05"; //보여지는 html 문서 viewEx03

    }
    @GetMapping(value ="/ex06")//인터넷에 보내는 주소
    public String thymeleafExam06() { //컨트롤러 메소드
        return "thymeleafEx/viewEx06"; //보여지는 html 문서 viewEx03

    }


    @GetMapping(value ="/ex07")//인터넷에 보내는 주소
    public String thymeleafExam07(String param1, String param2 , String param3 ,Model model) { //컨트롤러 메소드
        if(param1==null){param1="없어졌다";}
        if(param2==null){param2="dd";}
        if(param3==null){param3="aa";}

        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        model.addAttribute("param3",param3);
        return "thymeleafEx/viewEx07"; //보여지는 html 문서 viewEx03
    }


    @GetMapping(value ="/ex08")//인터넷에 보내는 주소
    public String thymeleafExam08() { //컨트롤러 메소드
        return "thymeleafEx/viewEx08"; //보여지는 html 문서 viewEx03
    }
}

