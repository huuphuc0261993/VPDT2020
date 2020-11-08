package com.example.demo.model.view;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TaoCongViecView {
    private Long id;
    private String tenCongViec;
    private String noiDung;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private List<Integer> tatCaNhanVien;
    private int nvChinh;

    private int isDeleted=0;
    private LocalDate deleted_at;
    private String deleted_by;
    private LocalDate  updated_at;
    private String updated_by;
    private LocalDate created_at;
    private String created_by;

    public TaoCongViecView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public List<Integer> getTatCaNhanVien() {
        return tatCaNhanVien;
    }

    public void setTatCaNhanVien(List<Integer> tatCaNhanVien) {
        this.tatCaNhanVien = tatCaNhanVien;
    }

    public int getNvChinh() {
        return nvChinh;
    }

    public void setNvChinh(int nvChinh) {
        this.nvChinh = nvChinh;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDate getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
