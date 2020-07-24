package com.whu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hedon Wang
 * @create 2020-07-24 23:16
 */
@Controller
public class PageController {

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
}
