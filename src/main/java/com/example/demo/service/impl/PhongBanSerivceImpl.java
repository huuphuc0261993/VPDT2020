package com.example.demo.service.impl;

import com.example.demo.model.PhongBan;
import com.example.demo.repository.PhongBanRepository;
import com.example.demo.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhongBanSerivceImpl implements PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;
    @Override
    public Iterable<PhongBan> findAllByIsDeletedEquals(int isDeleted) {
        return phongBanRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void save(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }

    @Override
    public PhongBan findById(Long id) {
        return phongBanRepository.findById(id).orElse(null);
    }


}
