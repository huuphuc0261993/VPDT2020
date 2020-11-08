package com.example.demo.service.impl;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.QuanLyCongViecRepository;
import com.example.demo.service.QuanLyCongViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuanLyCongViecServiceImpl implements QuanLyCongViecService {
    @Autowired
    QuanLyCongViecRepository quanLyCongViecRepository;


    @Override
    public List<ChiTiet> chiTietList() {
        return quanLyCongViecRepository.chiTietList();
    }

    @Override
    public Iterable<ChiTiet> listQuanLy() {
        return quanLyCongViecRepository.listQuanLy();
    }


}
