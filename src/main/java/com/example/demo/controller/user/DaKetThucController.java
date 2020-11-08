package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/daketthuc")
public class DaKetThucController {

    @GetMapping("")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("user/DaKetThuc");
        return modelAndView;
    }
}
