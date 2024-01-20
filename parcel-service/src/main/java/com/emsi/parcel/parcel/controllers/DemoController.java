package com.emsi.parcel.parcel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")

public class DemoController {
    @GetMapping
    public ResponseEntity<String> greetings(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/test")
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
