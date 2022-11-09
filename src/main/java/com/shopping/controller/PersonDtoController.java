package com.shopping.controller;

import com.shopping.entity.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/person")
public class PersonDtoController {
    @GetMapping(value = "/ex01")
    public String PersonDto(Model model) {
        model.addAttribute("data,", "임의의 문자열을 저장해주는메소드");
        return "person/viewEx01";
    }


    @GetMapping(value = "/ex02")
    public String PersonDto02(Model model) {
        PersonDto bean = new PersonDto();
        bean.setName("고요");
        bean.setSalary(50000);
        bean.setAddress("용마산");
        bean.setId("먹방의 장인 2고요");

        model.addAttribute("bean", bean);

        return "person/viewEx02";
    }


    private List<PersonDto> getPersonDtoData(int gaesu) {
        List<PersonDto> itemDtoList = new ArrayList<PersonDto>();
        for (int i = 1; i <= gaesu; i++) {
            PersonDto bean = new PersonDto();
            bean.setId("꽁꽁밤이" + i);
            bean.setSalary(12000 * i);
            bean.setAddress("주소는 자유" + i);
            bean.setAge(3);
            bean.setName("밤이" + i);

            itemDtoList.add(bean);
        }
        return itemDtoList;
    }

    @GetMapping(value = "/ex03")
    public String PersonDto03(Model model) {
        List<PersonDto> itemDtoList = this.getPersonDtoData(10);

        model.addAttribute("itemDtoList",itemDtoList);
        return "person/viewEx03";

    }
    @GetMapping(value = "/ex04")
    public String thymeleafExam04(Model model) {
        List<PersonDto> itemDtoList = this.getPersonDtoData(5);

        model.addAttribute("itemDtoList",itemDtoList);
        return "person/viewEx04";

    }
}