package com.example.demo.repository;

import com.example.demo.model.CongViec;
import com.example.demo.model.view.TaoCongViecView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaoCongViecRepository extends PagingAndSortingRepository<CongViec,Long> {

}
