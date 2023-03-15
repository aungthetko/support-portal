package com.demo.supportportal.resources;

import com.demo.supportportal.exception.EmailExistException;
import com.demo.supportportal.exception.EmailNotFoundException;
import com.demo.supportportal.exception.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = {"/" , "/user"})
public class UserResources extends ExceptionHandling {

    @GetMapping("/home")
    public String sayHello() throws EmailExistException{
        // return "Hello World";
        throw new EmailExistException("This email is already taken");
    }

}
