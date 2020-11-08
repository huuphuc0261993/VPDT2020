package com.example.demo.service.impl;

import com.example.demo.model.ThongBao;
import com.example.demo.repository.ThongBaoRepository;
import com.example.demo.service.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class ThongBaoServiceImpl implements ThongBaoService {
    @Autowired
    private ThongBaoRepository thongBaoRepository;
    @Override
    public Iterable<ThongBao> findAllByIsDeletedEquals(int isDeleted) {
        return thongBaoRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void save(ThongBao thongBao) {
        thongBaoRepository.save(thongBao);
    }

    @Override
    public ThongBao findById(Long id) {
        return thongBaoRepository.findById(id).orElse(null);
    }

//    @Override
//    public void softDeleted(Long id) {
//        thongBaoRepository.softDeleted(id);
//    }
}
