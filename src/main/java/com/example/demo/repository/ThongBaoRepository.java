package com.example.demo.repository;

import com.example.demo.model.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;

@Repository
public interface ThongBaoRepository extends PagingAndSortingRepository<ThongBao,Long> {
    Iterable<ThongBao>findAllByIsDeletedEquals(int isDeleted);
//    @Transactional
//    @Modifying
//    @Query(value = "update thong_baos set is_deleted=1 WHERE id = :id", nativeQuery = true)
//    void softDeleted(@Param("id")Long id);
}
