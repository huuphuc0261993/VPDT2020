package com.example.demo.controller.admin.rest;

import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.PhongBanService;
import com.example.demo.service.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/phongban")
public class PhongBanResController {
    @Autowired
    PhongBanService phongBanService;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<PhongBan>> showView() {
        Iterable<PhongBan> phongBans = phongBanService.findAllByIsDeletedEquals(0);
        if (phongBans == null) {
            return new ResponseEntity<Iterable<PhongBan>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<PhongBan>>(phongBans, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public HttpStatus create(@RequestBody PhongBan phongBan){
        phongBanService.save(phongBan);
        return HttpStatus.OK;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<PhongBan>edit(@PathVariable("id")Long id){
        PhongBan phongBan = phongBanService.findById(id);
        if (phongBan == null) {
            System.out.println("Phong ban with id " + id + " not found");
            return new ResponseEntity<PhongBan>(HttpStatus.NOT_FOUND);
        }
        try {
            phongBanService.save(phongBan);
        }catch (Exception ex){
            return new ResponseEntity<PhongBan>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PhongBan>(phongBan, HttpStatus.OK);
    }
    @RequestMapping(value = "/edit", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public HttpStatus edit(@RequestBody PhongBan phongBan){
        phongBanService.save(phongBan);
        return HttpStatus.OK;
    }
}

