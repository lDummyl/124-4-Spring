package com.example.demo.web;

import com.example.demo.dto.UserDetails;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MyController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserDetails getSomeData(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<UserDetails> getAll() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public String getSomeOtherData(@RequestBody UserDetails userDetails) {
        log.info(userDetails.toString());
        userService.add(userDetails);
        return "Ok!";
    }

    @GetMapping
    @RequestMapping("/simplehtml")
    public String sayHtmll() {
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
    }

}
