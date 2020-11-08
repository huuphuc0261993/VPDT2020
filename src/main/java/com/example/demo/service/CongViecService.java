package com.example.demo.service;

import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import com.example.demo.model.ThongBao;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CongViecService {
    Iterable<CongViec> findByIsDeletedEquals(int id);
    void save(CongViec congViec);
    CongViec idCongViec();
    List<CongViec> tinhTrangCongViec();
}
