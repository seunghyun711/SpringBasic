package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 일반 컨트롤러는 뷰 템플릿 페이지 반환 RestController는 RestAPI용 컨트롤러로 JSON을 반환한다.
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello!!!!!!!";
    }
}
