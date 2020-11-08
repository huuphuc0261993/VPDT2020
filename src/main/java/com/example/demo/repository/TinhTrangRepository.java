package com.example.demo.repository;

import com.example.demo.model.TinhTrang;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhTrangRepository extends PagingAndSortingRepository<TinhTrang,Long> {
}
