package com.example.demo.service.impl;

import com.example.demo.model.ChiTiet;
import com.example.demo.repository.DuocGiaoRepository;
import com.example.demo.service.DuocGiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuocGiaoServiceImpl implements DuocGiaoService {
    @Autowired
    private DuocGiaoRepository duocGiaoRepository;
    @Override
    public List<Long> congViecCanLam(Long nhan_vien_id) {
        return duocGiaoRepository.congViecCanLam(nhan_vien_id);
    }

}
