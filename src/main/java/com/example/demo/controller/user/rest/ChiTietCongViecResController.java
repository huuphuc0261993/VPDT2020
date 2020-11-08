package com.example.demo.controller.user.rest;

import com.example.demo.model.*;
import com.example.demo.model.view.TaoCongViecView;
import com.example.demo.repository.TinhTrangRepository;
import com.example.demo.service.ChiTietService;
import org.hibernate.validator.internal.util.classhierarchy.ClassHierarchyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/api/chitietcongviec")
public class ChiTietCongViecResController {
    @Autowired
    ChiTietService chiTietService;
    @Autowired
    private TinhTrangRepository tinhTrangRepository;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ChiTiet>> getView(@PathVariable("id") Long id) {

        ChiTiet chiTiets = chiTietService.findById(id);

        Long idCongViec = chiTiets.getCongViec().getId();

        List<ChiTiet> listChiTiet = chiTietService.showCongViec(idCongViec);
        if (chiTiets == null) {
            return new ResponseEntity<List<ChiTiet>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChiTiet>>(listChiTiet, HttpStatus.OK);
    }
    @RequestMapping(value = "/giaHan/{id}", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public HttpStatus giaHan(@PathVariable("id")Long id, @RequestBody ChiTiet chiTiet){
        ChiTiet chiTiets = chiTietService.findById(id);
        chiTiets.setNgayGiaHan(chiTiet.getNgayGiaHan());
        CongViec congViec = chiTiets.getCongViec();
        congViec.setTinhTrang(tinhTrangRepository.findById(4L).orElse(null));
        chiTiets.setXacNhanThongTin(0);
        chiTietService.save(chiTiets);

        return HttpStatus.OK;
    }
    @RequestMapping(value = "/dexuat/{id}", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public HttpStatus dexuat(@PathVariable("id")Long id){
        ChiTiet chiTiets = chiTietService.findById(id);
        CongViec congViec = chiTiets.getCongViec();
        congViec.setTinhTrang(tinhTrangRepository.findById(6L).orElse(null));
        chiTiets.setXacNhanThongTin(0);
        chiTietService.save(chiTiets);
        return HttpStatus.OK;
    }
    @RequestMapping(value = "/chuyengiao/{idCongViec}/{idNhanVien}", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public HttpStatus chuyengiao(@PathVariable("idCongViec")Long idCongViec,@PathVariable("idNhanVien")Long idNhanVien){
        ChiTiet chiTiets = chiTietService.findById(idCongViec);
        chiTiets.setThongTinChuyenGiao(idNhanVien);
        CongViec congViec = chiTiets.getCongViec();
        congViec.setTinhTrang(tinhTrangRepository.findById(5L).orElse(null));
        chiTiets.setXacNhanThongTin(0);
        chiTietService.save(chiTiets);
        return HttpStatus.OK;
    }

}
