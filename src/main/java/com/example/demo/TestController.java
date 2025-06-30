package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @PostMapping("/import")
    public String importData(@RequestBody PolygonRequestDto request) {
        
        if (request.getTicker() == null || request.getResults() == null) {
            return "Invalid request data";
        }

        return "Ticker: " + request.getTicker() + ", results count: " + request.getResults().size();
    }
}
