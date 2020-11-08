package com.example.demo.controller.admin;

import com.example.demo.model.CongViec;
import com.example.demo.model.ThongBao;
import com.example.demo.model.TinhTrang;
import com.example.demo.repository.TinhTrangRepository;
import com.example.demo.service.CongViecService;
import com.example.demo.service.ThongBaoService;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/thongbao")
public class ThongBaoController {

    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private CongViecService congViecService;

    @Autowired
    private TinhTrangRepository tinhTrangRepository;

    @GetMapping("/admin")
    public ModelAndView getView() {
        Iterable<ThongBao> thongBaos = thongBaoService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("admin/thongBao/ThongBao");
        modelAndView.addObject("thongBaos", thongBaos);
        modelAndView.addObject("thongBao", new ThongBao());
        return modelAndView;
    }


    @GetMapping("/user")
    public ModelAndView getViewUer() {
        Iterable<ThongBao> thongBaos = thongBaoService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("user/ThongBaoUser");
        modelAndView.addObject("thongBaos", thongBaos);
        modelAndView.addObject("thongBao", new ThongBao());
        return modelAndView;
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) throws ParseException {


        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/thongbao/admin";
        }
        return "redirect:/thongbao/user";
    }

}
