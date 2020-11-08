package com.example.demo.service;

import com.example.demo.model.ChiTiet;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChiTietService {
    Iterable<ChiTiet> findByIsDeletedEquals(int id);
    void save(ChiTiet chiTiet);
    ChiTiet findById(Long id);
    List<String> nhanVienLamViec(@Param("cong_viec_id")Long cong_viec_id);
    ChiTiet nhanVienChinh(@Param("cong_viec_id")Long cong_viec_id,@Param("nv_chinh")Long nv_chinh);
    List<ChiTiet> showCongViec(@Param("cong_viec_id")Long cong_viec_id);
    Long count(@Param("tinh_trang_id")Long tinh_trang_id,@Param("nhan_vien_id")Long nhan_vien_id);
    Long dangthuchien(@Param("idQuaHan")Long idQuaHan,@Param("idKetThuc")Long idKetThuc,@Param("idNhanVien")Long idNhanVien);
//    void create(@Param("nv_chinh")int nv_chinh, @Param("cong_viec_id")int cong_viec_id, @Param("nhan_vien_id")int nhan_vien_id);
}
