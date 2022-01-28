package com.example.demo.web;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "test", url = "https://ethgasstation.info")
public interface MyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/json/ethgasAPI.json")
    String getHello();

}
