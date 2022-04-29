package com.example.springtest.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    /**
     * 웹 API를 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * 이 클래스로 HTTP, GET, POST등에 대한 API 테스트 가능*/
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                /**
                 * MockMvc를 통해 /hello주소로 HTTP GET 요청*
                 * 체이닝이 지원되어 아래와 같이 여러 검증기능을 이어서 선언 가능*/
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
