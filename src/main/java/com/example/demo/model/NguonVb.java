package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "NguonVbs")
public class NguonVb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String tenNguonVb;

    @OneToMany(mappedBy = "nguonVb")
    @JsonIgnore
    private List<VanBan> vanBans;

    @JsonIgnore
    private int isDeleted=0;
    @JsonIgnore
    private LocalDate  deleted_at;
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

    public NguonVb() {
    }

    public NguonVb(String tenNguonVb) {
        this.tenNguonVb = tenNguonVb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNguonVb() {
        return tenNguonVb;
    }

    public void setTenNguonVb(String tenNguonVb) {
        this.tenNguonVb = tenNguonVb;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<VanBan> getVanBans() {
        return vanBans;
    }

    public void setVanBans(List<VanBan> vanBans) {
        this.vanBans = vanBans;
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
