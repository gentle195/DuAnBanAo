package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Ten")
    private String hoTen;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "SdtNhanVien")
    private String sdt;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Email")
    private String email;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "TaiKhoan")
    private String taiKhoan;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "CCCD")
    private String CCCD;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private int tinhTrang;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChucVu")
    private ChucVu chucVu;



}
