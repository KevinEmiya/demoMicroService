package com.ky.demo.msvc.controller;

import com.ky.demo.msvc.model.Word;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class WordController {

    @PostMapping("")
    public void onMsg(@RequestBody Word word){
        System.out.println("received: " + word);
    }
}
