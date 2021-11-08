package com.example.swaggerapp.controller;

import com.example.swaggerapp.pojo.Message;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api
@RequestMapping("/messages")
@RestController
public class MessageController {

    @GetMapping("/{id}")
    public Message message(@PathVariable int id) {
        return new Message("Spring Boot Works", id);
    }

    @GetMapping
    public List<Message> messages() {
        return new ArrayList<>();
    }

}
