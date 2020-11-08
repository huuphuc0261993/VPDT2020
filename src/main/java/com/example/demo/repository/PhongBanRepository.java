package com.example.demo.repository;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PhongBanRepository extends PagingAndSortingRepository<PhongBan,Long> {
    Iterable<PhongBan>findAllByIsDeletedEquals(int isDeleted);


}
