package com.example.demo.controller.admin.rest;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.TinhTrangRepository;
import com.example.demo.service.*;
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
import java.util.List;

@RestController
@RequestMapping("/api/quanlycongviec")
public class QuanLyCongViecResController {
    @Autowired
    QuanLyCongViecService quanLyCongViecService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    ChiTietService chiTietService;
    @Autowired
    CongViecService congViecService;
    @Autowired
    TinhTrangService tinhTrangService;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ChiTiet>> showView() {
        // Lấy Id của nhân viên đang đăng nhập hiện tại
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        System.out.println(username);
//        NhanVien nv = nhanVienService.findByUsername(username);
//        request.getSession().setAttribute("userId", nv.getMnv());
//        Long userId = (Long) request.getSession().getAttribute("userId");

        List<ChiTiet> chiTiets = quanLyCongViecService.chiTietList();
        if (chiTiets == null) {
            return new ResponseEntity<List<ChiTiet>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChiTiet>>(chiTiets, HttpStatus.OK);
    }

    @RequestMapping(value = "/ketthuc/{idChiTiet}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public HttpStatus ketThuc(@PathVariable("idChiTiet") Long idChiTiet) {
        ChiTiet chiTiet = chiTietService.findById(idChiTiet);
        chiTiet.getCongViec().setTinhTrang(tinhTrangService.findById(3L));
        chiTiet.setXacNhanThongTin(1);
        chiTietService.save(chiTiet);
        return HttpStatus.OK;
    }
    @RequestMapping(value = "/giahan/{idChiTiet}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public HttpStatus giaHan(@PathVariable("idChiTiet") Long idChiTiet) {
        ChiTiet chiTiet = chiTietService.findById(idChiTiet);
        chiTiet.getCongViec().setNgayKetThuc(chiTiet.getNgayGiaHan());
        chiTiet.setXacNhanThongTin(1);
        chiTietService.save(chiTiet);
        return HttpStatus.OK;
    }
}
