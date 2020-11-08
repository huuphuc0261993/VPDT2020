package com.example.demo.repository;

import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface CongViecRepository extends PagingAndSortingRepository<CongViec,Long> {
    Iterable<CongViec> findAllByIsDeletedEquals(int isDeleted);
//    @Transactional
//    @Modifying
//    @Query(value = "SELECT MAX(id) FROM cong_viecs ", nativeQuery = true)
//    Long idCongViec();

    @Query(value = "SELECT * FROM cong_viecs order by id desc limit 1; ", nativeQuery = true)
    CongViec idCongViec();
    @Query(value = "SELECT * FROM vpdt.cong_viecs where ngay_ket_thuc<= now() and tinh_trang_id=1; ", nativeQuery = true)
    List<CongViec> tinhTrangCongViec();
}
