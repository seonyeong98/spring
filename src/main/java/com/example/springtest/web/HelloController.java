package com.example.springtest.web;

import com.example.springtest.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     //외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                     //외부에서 name(@RequestParam("name")이란 이름으로 넘긴 parameter를 메소드 파라미터 name(String name)에 저장하게 됨
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
    //name, amount API를 호출하는 곳에서 넘겨준 값들
}
