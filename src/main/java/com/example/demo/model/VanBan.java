package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "VanBans")
public class VanBan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "bigserial")
    private Long id;
    private String MSVB;
    private String tenvb;
    private String noiGui;
    private String noiNhan;
    private String noiDung;
    private String nguoiKy;
    private Date ngayDen;
    private Date ngayKy;
    private Date hetHan;

    @ManyToOne
    @JoinColumn(name = "nhanVien_id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "phongBan_id")
    private PhongBan phongBan;

    @ManyToOne
    @JoinColumn(name = "loaiVb_id")
    private LoaiVb loaiVb;

    @ManyToOne
    @JoinColumn(name = "nguonVb_id")
    private NguonVb nguonVb;
    @JsonIgnore
    private int isDeleted=0;
    @JsonIgnore
    private LocalDate deleted_at;
    @JsonIgnore
    private String deleted_by;
    @JsonIgnore
    private LocalDate  updated_at;
    @JsonIgnore
    private String updated_by;
    @JsonIgnore
    private LocalDate created_at;
    @JsonIgnore
    private String created_by;

    public VanBan() {
    }


    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public LoaiVb getLoaiVb() {
        return loaiVb;
    }

    public void setLoaiVb(LoaiVb loaiVb) {
        this.loaiVb = loaiVb;
    }

    public NguonVb getNguonVb() {
        return nguonVb;
    }

    public void setNguonVb(NguonVb nguonVb) {
        this.nguonVb = nguonVb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMSVB() {
        return MSVB;
    }

    public void setMSVB(String MSVB) {
        this.MSVB = MSVB;
    }

    public String getTenvb() {
        return tenvb;
    }

    public void setTenvb(String tenvb) {
        this.tenvb = tenvb;
    }

    public String getNoiGui() {
        return noiGui;
    }

    public void setNoiGui(String noiGui) {
        this.noiGui = noiGui;
    }

    public String getNoiNhan() {
        return noiNhan;
    }

    public void setNoiNhan(String noiNhan) {
        this.noiNhan = noiNhan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(String nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(Date ngayKy) {
        this.ngayKy = ngayKy;
    }

    public Date getHetHan() {
        return hetHan;
    }

    public void setHetHan(Date hetHan) {
        this.hetHan = hetHan;
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
