package com.example.demo.controller.admin.rest;

import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thongbao")
public class ThongBaoResController {
    @Autowired
    ThongBaoService thongBaoService;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<ThongBao>> showView() {
        Iterable<ThongBao> thongBaos = thongBaoService.findAllByIsDeletedEquals(0);
        if (thongBaos == null) {
            return new ResponseEntity<Iterable<ThongBao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<ThongBao>>(thongBaos, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public HttpStatus create(@RequestBody ThongBao thongBao) {
        thongBaoService.save(thongBao);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ThongBao> update(@PathVariable("id") Long id) {
        ThongBao currentThongBao =thongBaoService.findById(id);
        if (currentThongBao == null) {
            System.out.println("Thongbao with id " + id + " not found");
            return new ResponseEntity<ThongBao>(HttpStatus.NOT_FOUND);
        }
        try {
            thongBaoService.save(currentThongBao);
        }catch (Exception ex){
            return new ResponseEntity<ThongBao>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ThongBao>(currentThongBao, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ThongBao> getProduct(@PathVariable("id") long id) {
        ThongBao thongBao = thongBaoService.findById(id);
        thongBao.setIsDeleted(1);
        thongBaoService.save(thongBao);
        if (thongBao == null) {

            return new ResponseEntity<ThongBao>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ThongBao>(thongBao, HttpStatus.OK);
    }
}
