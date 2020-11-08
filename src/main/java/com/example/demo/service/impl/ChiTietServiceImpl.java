package com.example.demo.service.impl;

import com.example.demo.model.ChiTiet;
import com.example.demo.repository.ChiTietRepository;
import com.example.demo.service.ChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietServiceImpl implements ChiTietService {
    @Autowired
    private ChiTietRepository chiTietRepository;
    @Override
    public Iterable<ChiTiet> findByIsDeletedEquals(int id) {
        return chiTietRepository.findByIsDeletedEquals(id);
    }

    @Override
    public void save(ChiTiet chiTiet) {
        chiTietRepository.save(chiTiet);
    }

    @Override
    public ChiTiet findById(Long id) {
        return chiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<String> nhanVienLamViec(Long cong_viec_id) {
        return chiTietRepository.nhanVienLamViec(cong_viec_id);
    }

    @Override
    public ChiTiet nhanVienChinh(Long cong_viec_id, Long nv_chinh) {
        return chiTietRepository.nhanVienChinh(cong_viec_id,nv_chinh);
    }

    @Override
    public List<ChiTiet> showCongViec(Long cong_viec_id) {
        return chiTietRepository.showCongViec(cong_viec_id);
    }

    @Override
    public Long count(Long tinh_trang_id, Long nhan_vien_id) {
        return chiTietRepository.count(tinh_trang_id,nhan_vien_id);
    }

    @Override
    public Long dangthuchien(Long idQuaHan, Long idKetThuc, Long idNhanVien) {
        return chiTietRepository.dangthuchien(idQuaHan,idKetThuc,idNhanVien);
    }

//    @Override
//    public void create(int nv_chinh, int cong_viec_id, int nhan_vien_id) {
//        chiTietRepository.create(nv_chinh,cong_viec_id,nhan_vien_id);
//    }
}
