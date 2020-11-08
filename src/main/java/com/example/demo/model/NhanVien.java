package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NhanViens")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long mnv;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String avatar;
    private String roles="USER";
    private int enabled = 1;

    @OneToMany(mappedBy = "nhanVien")
    @JsonIgnore
    private List<HoSo> hoSos;

    @OneToMany(mappedBy = "nhanVien")
    @JsonIgnore
    private List<ChiTiet> chiTiets;

    @OneToMany(mappedBy = "nhanVien")
    @JsonIgnore
    private List<VanBan> vanBans;

//        @OneToMany(mappedBy = "nhanVien")
//    @JsonIgnore
//    private List<RolesDetail> rolesDetails;
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "mnv"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;


    @ManyToOne
    @JoinColumn(name = "phongBan_id")
    private PhongBan phongBan;

    @JsonIgnore
    private int isDeleted = 0;
    @JsonIgnore
    private LocalDate deleted_at;
    @JsonIgnore
    private String deleted_by;
    @JsonIgnore
    private LocalDate updated_at;
    @JsonIgnore
    private String updated_by;
    @JsonIgnore
    private LocalDate created_at;
    @JsonIgnore
    private String created_by;

    public NhanVien() {
    }

    public Long getMnv() {
        return mnv;
    }

    public void setMnv(Long mnv) {
        this.mnv = mnv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<HoSo> getHoSos() {
        return hoSos;
    }

    public void setHoSos(List<HoSo> hoSos) {
        this.hoSos = hoSos;
    }

    public List<ChiTiet> getChiTiets() {
        return chiTiets;
    }

    public void setChiTiets(List<ChiTiet> chiTiets) {
        this.chiTiets = chiTiets;
    }

    public List<VanBan> getVanBans() {
        return vanBans;
    }

    public void setVanBans(List<VanBan> vanBans) {
        this.vanBans = vanBans;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
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
