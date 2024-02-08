package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String ma;
    private String maQR;

    @DecimalMin(value = "1", message = "Số lượng tồn lớn hơn 0")
    @DecimalMax(value = "1000", message = "Số lượng tồn nhỏ hơn hoặc bằng 1000")
    @NotNull(message = "Không để trống dữ liệu")
    private int soLuongTon;

//    private BigDecimal giaNhap;

    @DecimalMin(value = "0.01", message = "Giá lớn hơn 0")
    @DecimalMax(value = "100000000", message = "Giá nhỏ hơn hoặc bằng 100 triệu")
    @NotNull(message = "Không để trống dữ liệu")
    private BigDecimal giaBan;

    private String moTa;
    private Date ngayTao;
    private Date ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private int trangThai;
    //0: con hang : 1: hêt hang 2: ngung ban

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSanPham")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idThuongHieu")
    private ThuongHieu thuongHieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idKichCo")
    private KichCo kichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idChatLieu")
    private ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCoAo")
    private CoAo coAo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHinhAnh")
    private HinhAnh hinhAnh;

    public void loadFromViewModel(ChiTietSanPham vm) {
        this.setChatLieu(vm.getChatLieu());
        this.setCoAo(vm.getCoAo());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setSoLuongTon(vm.getSoLuongTon());
        this.setNgayTao(vm.getNgayTao());
        this.setThuongHieu(vm.getThuongHieu());
        this.setMauSac(vm.getMauSac());
    }


}
