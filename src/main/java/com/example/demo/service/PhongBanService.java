package com.example.demo.service;

import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface PhongBanService {
    Iterable<PhongBan>findAllByIsDeletedEquals(int isDeleted);
    void save(PhongBan phongBan);
    PhongBan findById(Long id);

}
