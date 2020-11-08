package com.example.demo.repository;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuocGiaoRepository extends PagingAndSortingRepository<CongViec,Long> {
    @Query(value = "select chi_tiet.id from chi_tiet inner join " +
            "cong_viecs on chi_tiet.cong_viec_id = cong_viecs.id where tinh_trang_id <> 3 and " +
            "nhan_vien_id = :idNhanVien and chi_tiet.is_deleted = 0", nativeQuery = true)
    List<Long> congViecCanLam(@Param("idNhanVien")Long idNhanVien);


}
