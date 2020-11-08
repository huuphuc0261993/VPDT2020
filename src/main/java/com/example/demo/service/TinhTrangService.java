package com.example.demo.service;

import com.example.demo.model.ThongBao;
import com.example.demo.model.TinhTrang;
import org.springframework.stereotype.Service;

@Service
public interface TinhTrangService {
    TinhTrang findById(Long id);
}
