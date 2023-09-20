package com.green.Shop.test;

import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//html, js에서 넘어오는 데이터를 받는 방식
@Controller
@RequestMapping("/test1")
@RequiredArgsConstructor
public class TestController1 {
    //의존성 주입을 통한 객체 생성 첫번째 방식
    //필드를 통한 의존성 주입
    //@Resource(name="test")
    //private TestService testService;

    //2. 생성자를 통한 의존성 주입
    private final TestService testService;

//    public TestController1(TestService testService){
//        this.testService = testService;
//    }




    @GetMapping("/test1")
    public String test1(){
        testService.test();
        return "test/test1/test1";
    }

    @PostMapping("/test2")
    public String test2(@RequestParam(name = "name1", required = false) String name
                        , @RequestParam int age
                        , @RequestParam(required = false, defaultValue = "java") String name2
                        , String name3
                        ,  ItemVO itemVO){
        System.out.println(name);
        System.out.println(age);
        System.out.println(name2);
        return "test/test1/test1";
    }

}








