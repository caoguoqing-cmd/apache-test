package com.example.apache.controller;

import com.example.apache.aspect.HotWind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caogq
 * @date 2021/8/9 9:48
 */
@RestController
@Slf4j
public class AspectController {
    @GetMapping("/one")
    @HotWind
    public void teeeee(@RequestParam("name") String name,@RequestParam("id") Integer id) {
        log.info("须晴日，看红装素裹,name:{},id:{}",name,id);
    }

    @GetMapping("/two")
    public Student gkhjkh(){
        Student student = new Student().setId("1");
        return student;
    }
}
