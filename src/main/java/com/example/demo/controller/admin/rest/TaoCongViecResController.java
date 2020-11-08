package com.example.demo.controller.admin.rest;

import com.example.demo.model.*;
import com.example.demo.model.view.TaoCongViecView;
import com.example.demo.repository.ChiTietRepository;
import com.example.demo.repository.CongViecRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.CongViecService;
import com.example.demo.service.TaoCongViecService;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/taocongviec")
public class TaoCongViecResController {
    @Autowired
    private TaoCongViecService taoCongViecService;
    @Autowired
    private CongViecService congViecService;
    @Autowired
    CongViecRepository congViecRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    private ChiTietService chiTietService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public HttpStatus create(@Valid @RequestBody TaoCongViecView taoCongViecView) {
        System.out.println(taoCongViecView);
        CongViec congViec = new CongViec();

        congViec.setTenCongViec(taoCongViecView.getTenCongViec());
        congViec.setNoiDung(taoCongViecView.getNoiDung());
        congViec.setNgayBatDau(taoCongViecView.getNgayBatDau());
        congViec.setNgayKetThuc(taoCongViecView.getNgayKetThuc());
        congViec.setTinhTrang(new TinhTrang(1L));


//        ép kiểu từ int lên long

        List<Integer> idNhanVien=taoCongViecView.getTatCaNhanVien();
        List<Long> idNhanVienLong = new ArrayList<>();
        System.out.println(taoCongViecView);
        for (Integer a: idNhanVien) {
            idNhanVienLong.add(Long.valueOf(a.toString()));
        }
        System.out.println(taoCongViecView.getNvChinh());
// save nhân viên tại bảng chi tiết
        List<ChiTiet> chiTiets = new ArrayList<>();
        for (Long i:idNhanVienLong) {
            ChiTiet chiTiet = new ChiTiet();
            if(i == taoCongViecView.getNvChinh()){
                chiTiet.setNvChinh(1);
            }
            chiTiet.setCongViec(congViec);
            chiTiet.setNhanVien(nhanVienRepository.findById(i).orElse(null));
            chiTiets.add(chiTiet);

        }
        congViec.setChiTietCongViecList(chiTiets);
        congViec.getChiTietCongViecList();
        congViecService.save(congViec);


        return HttpStatus.OK;
    }


}
