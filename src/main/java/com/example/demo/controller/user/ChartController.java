package com.example.demo.controller.user;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.model.TinhTrang;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.CongViecService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user/chart")
public class ChartController {
    @Autowired
    ChiTietService chiTietService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    CongViecService congViecService;
    @Autowired
    TinhTrangService tinhTrangService;


@GetMapping("/view")
public ModelAndView getView() {
    ModelAndView modelAndView = new ModelAndView("user/Chart");
    return modelAndView;

}
}
