package com.example.demo.service.impl;

import com.example.demo.model.TinhTrang;
import com.example.demo.repository.ThongBaoRepository;
import com.example.demo.repository.TinhTrangRepository;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TinhTrangServiceImpl implements TinhTrangService {
    @Autowired
    private TinhTrangRepository tinhTrangRepository;
    @Override
    public TinhTrang findById(Long id) {
        return tinhTrangRepository.findById(id).orElse(null);
    }
}
