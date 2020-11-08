package com.example.demo.controller.user.rest;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.CongViecService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class ChartResController {

    @Autowired
    ChiTietService chiTietService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    CongViecService congViecService;
    @Autowired
    TinhTrangService tinhTrangService;

    @RequestMapping(value = "", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public HttpStatus chart(){
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Long>> showView(HttpServletRequest request) {
        // Lấy Id của nhân viên đang đăng nhập hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        NhanVien nv = nhanVienService.findByUsername(username);
        request.getSession().setAttribute("userId", nv.getMnv());
        Long userId = (Long) request.getSession().getAttribute("userId");

        List<Long> thongke = new ArrayList<>();
        Long quaHan = 2L;
        Long ketThuc = 3L;

        // dang thuc hien
        thongke.add(chiTietService.dangthuchien(quaHan,ketThuc,userId));
        // dung han
        thongke.add(chiTietService.count(ketThuc,userId));
        // qua han
        thongke.add(chiTietService.count(quaHan,userId));

        return new ResponseEntity<List<Long>>(thongke, HttpStatus.OK);
    }
}
