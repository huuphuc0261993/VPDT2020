package com.example.demo.controller.admin;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("admin/nhanVien/NhanVien");
        return modelAndView;
    }



}
