package com.example.demo.repository;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChiTietRepository extends PagingAndSortingRepository<ChiTiet, Long> {
    Iterable<ChiTiet> findByIsDeletedEquals(int isDeleted);

    //    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO chi_tiet (nv_chinh, cong_viec_id, nhan_vien_id) " +
//            "VALUES ( nv_chinh = :nv_chinh,cong_viec_id = :cong_viec_id, nhan_vien_id = :nhan_vien_id);", nativeQuery = true)
//    void create(@Param("nv_chinh")int nv_chinh,@Param("cong_viec_id")int cong_viec_id,@Param("nhan_vien_id")int nhan_vien_id);
    @Query(value = "select nhan_viens.full_name from nhan_viens inner join chi_tiet on chi_tiet.nhan_vien_id = nhan_viens.mnv where chi_tiet.cong_viec_id = :cong_viec_id and chi_tiet.nv_chinh = 0", nativeQuery = true)
    List<String> nhanVienLamViec(@Param("cong_viec_id")Long cong_viec_id);
    @Query(value = "select*from chi_tiet where cong_viec_id = :cong_viec_id and nv_chinh = :nv_chinh", nativeQuery = true)
    ChiTiet nhanVienChinh(@Param("cong_viec_id")Long cong_viec_id,@Param("nv_chinh")Long nv_chinh);
    @Query(value = "select*from chi_tiet where cong_viec_id = :cong_viec_id and is_deleted = 0", nativeQuery = true)
    List<ChiTiet> showCongViec(@Param("cong_viec_id")Long cong_viec_id);

    @Query(value = "SELECT COUNT(tinh_trang_id) AS tinhtrang FROM chi_tiet " +
            "inner join cong_viecs on chi_tiet.cong_viec_id = cong_viecs.id " +
            "where tinh_trang_id = :tinh_trang_id and nhan_vien_id = :nhan_vien_id", nativeQuery = true)
    Long count(@Param("tinh_trang_id")Long tinh_trang_id,@Param("nhan_vien_id")Long nhan_vien_id);

    @Query(value = "SELECT COUNT(tinh_trang_id) AS tinhtrang FROM chi_tiet \n" +
            "            inner join cong_viecs on chi_tiet.cong_viec_id = cong_viecs.id \n" +
            "            where (tinh_trang_id <> :idQuaHan and tinh_trang_id <> :idKetThuc) and nhan_vien_id = :idNhanVien", nativeQuery = true)
    Long dangthuchien(@Param("idQuaHan")Long idQuaHan,@Param("idKetThuc")Long idKetThuc,@Param("idNhanVien")Long idNhanVien);
}
