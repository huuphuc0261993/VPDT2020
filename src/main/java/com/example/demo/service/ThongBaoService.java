package com.example.demo.service;

import com.example.demo.model.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public interface ThongBaoService {
    Iterable<ThongBao>findAllByIsDeletedEquals(int isDeleted);
    void save(ThongBao thongBao);
    ThongBao findById(Long id);
//    void softDeleted(@Param("id")Long id);
}
