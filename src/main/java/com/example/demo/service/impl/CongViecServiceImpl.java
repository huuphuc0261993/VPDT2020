package com.example.demo.service.impl;

import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.CongViecRepository;
import com.example.demo.service.CongViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongViecServiceImpl implements CongViecService {
    @Autowired
    private CongViecRepository congViecRepository;

    @Override
    public Iterable<CongViec> findByIsDeletedEquals(int id) {
        return congViecRepository.findAllByIsDeletedEquals(id);
    }

    @Override
    public void save(CongViec congViec) {
        congViec =congViecRepository.save(congViec);

    }

    @Override
    public CongViec idCongViec() {
        return congViecRepository.idCongViec();
    }

    @Override
    public List<CongViec> tinhTrangCongViec() {
        return congViecRepository.tinhTrangCongViec();
    }


}
