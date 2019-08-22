package com.example.jenkinsmysqldemo.controller;

import com.example.jenkinsmysqldemo.dao.GreetingDao;
import com.example.jenkinsmysqldemo.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    public GreetingDao greetingDao;

    @PostMapping()
    public Greeting createGreeting(@RequestBody Greeting greeting){
        return greetingDao.createGreeting(greeting);
    }

    @GetMapping("/{id}")
    public Greeting getGreeting(@PathVariable("id") int id){
        return greetingDao.getGreetingById(id);
    }
}
