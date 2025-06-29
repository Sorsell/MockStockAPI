package com.example.demo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {
    
    @GetMapping("/ping")
    public String ping() {
        return "pong!";
    }
}
