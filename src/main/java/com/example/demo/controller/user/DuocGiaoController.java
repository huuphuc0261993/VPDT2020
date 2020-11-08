package com.example.demo.controller.user;

import com.example.demo.model.PhongBan;
import com.example.demo.service.DuocGiaoService;
import com.example.demo.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/duocgiao")
public class DuocGiaoController {
    @Autowired
    private DuocGiaoService duocGiaoService;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @GetMapping("")
    public ModelAndView getView() {

        ModelAndView modelAndView = new ModelAndView("user/DuocGiao");
        return modelAndView;

    }
}
