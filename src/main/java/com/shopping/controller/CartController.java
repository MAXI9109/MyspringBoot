package com.shopping.controller;

import com.shopping.dto.CartProductDto;
import com.shopping.repository.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private  final CartService cartService ;


    @PostMapping(value = "/cart")
    public @ResponseBody ResponseEntity addCart(
            @RequestBody @Valid
            CartProductDto cartProductDto,
            BindingResult error,
            Principal principal){
        // cartProductDto는 커맨드 객체라고 하는 데 , 각 변수의 값을 저장하고 있는 화면에서 넘어온 객체



        // cartProductDto는 커맨드 객체라고 하는 데 , 각 변수의 값을 저장하고 있는 화면에서 넘어온 객체
    if(error.hasErrors()){ // dto 변수(filed)에 문제가 있는 경우
        StringBuilder sb = new StringBuilder() ;
        List<FieldError> fieldErrors =error.getFieldErrors() ;
        for(FieldError fe : fieldErrors){
            sb.append(fe.getDefaultMessage()) ;

        }
        return  new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST) ;
    }
        String email = principal.getName() ;

        try{
            Long cartProductId = this.cartService.addCart(cartProductDto, email) ;
            return new ResponseEntity<Long>(cartProductId, HttpStatus.OK);

        }catch (Exception err) {
            err.printStackTrace();
            return  new ResponseEntity<String>(err.toString(), HttpStatus.BAD_REQUEST) ;

        }
    }
}
