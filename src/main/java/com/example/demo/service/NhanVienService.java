package com.example.demo.service;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NhanVienService {
    Iterable<NhanVien>findAllByIsDeletedEquals(int isDeleted);
    NhanVien findByUsername(String username);
    void save(NhanVien nhanVien);
    List<NhanVien> listNhanVien(@Param("mpb")Long mpb);
    List<NhanVien> listNhanVienUser(@Param("mpb")Long mpb, @Param("mnv")Long mnv);
    NhanVien findById(Long id);


}
