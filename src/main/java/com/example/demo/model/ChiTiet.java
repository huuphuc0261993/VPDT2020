package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.web.servlet.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "ChiTiet")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = CongViec.class)
public class ChiTiet{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private int nvChinh = 0;

    @Column(columnDefinition="TEXT")
    private String baoCao;
    private String file;
    private LocalDate ngayBaoCao;
    private Date ngayGiaHan;
    private Long thongTinChuyenGiao = 0L;
    private int xacNhanThongTin = 0;

    @ManyToOne
    @JoinColumn(name = "nhanVien_id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "congViec_id")
    private CongViec congViec;


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

    public ChiTiet() {
    }

    public ChiTiet(Long id, int nvChinh, NhanVien nhanVien, CongViec congViec) {
        this.id = id;
        this.nvChinh = nvChinh;
        this.nhanVien = nhanVien;
        this.congViec = congViec;
    }

    public CongViec getCongViec() {
        return congViec;
    }

    public void setCongViec(CongViec congViec) {
        this.congViec = congViec;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBaoCao() {
        return baoCao;
    }

    public void setBaoCao(String baoCao) {
        this.baoCao = baoCao;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getNgayBaoCao() {
        return ngayBaoCao;
    }

    public void setNgayBaoCao(LocalDate ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    public Date getNgayGiaHan() {
        return ngayGiaHan;
    }

    public void setNgayGiaHan(Date ngayGiaHan) {
        this.ngayGiaHan = ngayGiaHan;
    }

    public Long getThongTinChuyenGiao() {
        return thongTinChuyenGiao;
    }

    public void setThongTinChuyenGiao(Long thongTinChuyenGiao) {
        this.thongTinChuyenGiao = thongTinChuyenGiao;
    }

    public boolean checked(){
        LocalDate nowDate = LocalDate.now();
        return Date.valueOf(congViec.getNgayKetThuc().toString()).getTime()<Date.valueOf(nowDate.toString()).getTime();
    }

    public int getXacNhanThongTin() {
        return xacNhanThongTin;
    }

    public void setXacNhanThongTin(int xacNhanThongTin) {
        this.xacNhanThongTin = xacNhanThongTin;
    }
}

