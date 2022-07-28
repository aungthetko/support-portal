package com.demo.supportportal.resources;

import com.demo.supportportal.exception.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResources extends ExceptionHandling {

    @GetMapping("/home")
    public String sayHello(){
        return "Hello World";
    }

}
