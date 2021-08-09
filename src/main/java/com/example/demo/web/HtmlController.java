package com.example.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class HtmlController {


    @GetMapping( "/hello/{name}")
    public String hello(Model model, @PathVariable String name) {
        log.debug(name);
        model.addAttribute("name", name);
        return "hello";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam String textForm, Model model) {
        model.addAttribute("textForm", textForm);

        return "success";
    }


}
