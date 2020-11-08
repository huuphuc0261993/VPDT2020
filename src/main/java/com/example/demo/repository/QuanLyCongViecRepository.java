package com.example.demo.repository;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuanLyCongViecRepository extends PagingAndSortingRepository<ChiTiet,Long> {

    @Query(value = "SELECT chi_tiet.* FROM chi_tiet inner join cong_viecs on chi_tiet.cong_viec_id = cong_viecs.id \n" +
            "where (tinh_trang_id = 5 or tinh_trang_id = 4 or tinh_trang_id = 6) and nv_chinh = 1 and xac_nhan_thong_tin = 0;", nativeQuery = true)
    Iterable<ChiTiet> listQuanLy();

    @Query(value = "select * from chi_tiet where is_deleted=0 and nv_chinh = 1 and xac_nhan_thong_tin = 0;", nativeQuery = true)
    List<ChiTiet> chiTietList();

}
