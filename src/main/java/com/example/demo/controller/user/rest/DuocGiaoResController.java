package com.example.demo.controller.user.rest;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.repository.ChiTietRepository;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.DuocGiaoService;
import com.example.demo.service.NhanVienService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/api/duocgiao")
public class DuocGiaoResController {
    @Autowired
    private DuocGiaoService duocGiaoService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChiTietRepository chiTietRepository;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<ChiTiet>> showView(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        NhanVien nv = nhanVienService.findByUsername(username);
        request.getSession().setAttribute("userId", nv.getMnv());
        Long userId = (Long) request.getSession().getAttribute("userId");
        System.out.println(userId);

        List<Long> chiTiets = duocGiaoService.congViecCanLam(userId);
        List<ChiTiet> chiTietList = new ArrayList<>();
        for (Long item: chiTiets) {
            chiTietList.add(chiTietRepository.findById(item).orElse(null));
        }
        System.out.println(chiTietList);
        return new ResponseEntity<Iterable<ChiTiet>>(chiTietList, HttpStatus.OK);
    }

}


