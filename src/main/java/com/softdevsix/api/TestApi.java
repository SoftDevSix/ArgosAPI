package com.softdevsix.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApi {

//    @GetMapping("/{name}")
//    public String test(@PathVariable String name) {
//        return "Hello World - " + name;
//    }
}