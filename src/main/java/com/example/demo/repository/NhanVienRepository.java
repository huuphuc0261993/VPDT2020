package com.example.demo.repository;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NhanVienRepository extends PagingAndSortingRepository<NhanVien,Long> {
    Iterable<NhanVien>findAllByIsDeletedEquals(int isDeleted);
    NhanVien findByUsername(String username);
    @Transactional
    @Modifying
    @Query(value = "select nhan_viens.* from phong_bans inner join nhan_viens on phong_bans.mpb = nhan_viens.phong_ban_id  WHERE mpb = :mpb and nhan_viens.is_deleted = 0", nativeQuery = true)
    List<NhanVien> listNhanVien(@Param("mpb")Long mpb);

    @Query(value = "select nhan_viens.* from phong_bans inner join nhan_viens on phong_bans.mpb = nhan_viens.phong_ban_id  WHERE mpb = :mpb and nhan_viens.is_deleted = 0 and mnv = :mnv", nativeQuery = true)
    List<NhanVien> listNhanVienUser(@Param("mpb")Long mpb, @Param("mnv")Long mnv);


}
