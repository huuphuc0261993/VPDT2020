package com.example.demo.service;

import com.example.demo.model.ChiTiet;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DuocGiaoService {
    List<Long> congViecCanLam(@Param("nhan_vien_id")Long nhan_vien_id);

}
