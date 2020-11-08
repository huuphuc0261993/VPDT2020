package com.example.demo.controller.admin.rest;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.PhongBanService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienResController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<NhanVien>> showView(@PathVariable("id")Long id){
        List<NhanVien>nhanViens=nhanVienService.listNhanVien(id);
        List<NhanVien> nhanVienList = new ArrayList<>();
        for (NhanVien nv:nhanViens) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setFullName(nv.getFullName());
            nhanVien.setPhongBan(nv.getPhongBan());
            nhanVien.setMnv(nv.getMnv());
            nhanVienList.add(nhanVien);
        }
        return new ResponseEntity<List<NhanVien>>(nhanVienList, HttpStatus.OK);
    }

    @RequestMapping(value = "/viewuser/{id}",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<NhanVien>> showView(@PathVariable("id")Long id, HttpServletRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        NhanVien nvs = nhanVienService.findByUsername(username);
        request.getSession().setAttribute("userId", nvs.getMnv());
        Long userId = (Long) request.getSession().getAttribute("userId");


        List<NhanVien>nhanViens=nhanVienService.listNhanVienUser(id,userId);
        List<NhanVien> nhanVienList = new ArrayList<>();
        for (NhanVien nv:nhanViens) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setFullName(nv.getFullName());
            nhanVien.setPhongBan(nv.getPhongBan());
            nhanVien.setMnv(nv.getMnv());
            nhanVienList.add(nhanVien);
        }
        return new ResponseEntity<List<NhanVien>>(nhanVienList, HttpStatus.OK);
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<NhanVien>> showView() {
        Iterable<NhanVien> nhanViens = nhanVienService.findAllByIsDeletedEquals(0);
        if (nhanViens == null) {
            return new ResponseEntity<Iterable<NhanVien>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<NhanVien>>(nhanViens, HttpStatus.OK);
    }
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public HttpStatus create(@RequestBody NhanVien nhanVien, @PathVariable("id") Long id) {

        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        String rawPassword = nhanVien.getPassword();;
        String encodePassword =  encoder.encode(rawPassword);
        nhanVien.setPassword(encodePassword);
        nhanVien.setPhongBan(phongBanService.findById(id));
        nhanVienService.save(nhanVien);

        return HttpStatus.OK;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public NhanVien edit(@PathVariable("id")Long id){
        NhanVien nhanVien = nhanVienService.findById(id);
        if (nhanVien == null) {
            System.out.println("Nhan vien with id " + id + " not found");
            return null;
        }

        return nhanVien;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public HttpStatus edit(@RequestBody NhanVien nhanVien, @PathVariable("id")Long id){
        nhanVien.setPhongBan(phongBanService.findById(id));

        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        String rawPassword = nhanVien.getPassword();;
        String encodePassword =  encoder.encode(rawPassword);
        nhanVien.setPassword(encodePassword);

        nhanVienService.save(nhanVien);
        return HttpStatus.OK;
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<NhanVien> softDelete(@PathVariable("id") long id) {
        NhanVien nhanVien = nhanVienService.findById(id);
        nhanVien.setIsDeleted(1);
        nhanVienService.save(nhanVien);
        if (nhanVien == null) {

            return new ResponseEntity<NhanVien>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<NhanVien>(nhanVien, HttpStatus.OK);
    }
}
