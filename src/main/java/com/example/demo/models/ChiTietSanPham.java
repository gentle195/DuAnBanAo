package com.example.demo.models;

import com.example.demo.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

    @Column(name = "Ma")
    private String ma;

    private String maQR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSanPham")
    SanPham sanPham;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMauSac")
    @NotNull(message = "* Mời chọn màu sắc")
    MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCoAo")
    @NotNull(message = "* Mời chọn cỡ áo")
    CoAo coAo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKichCo")
    @NotNull(message = "* Mời chọn kích cỡ")
    KichCo kichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChatLieu")
    @NotNull(message = "* Mời chọn chất liệu")
    ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdThuongHieu")
    @NotNull(message = "* Mời chọn thương hiệu")
    ThuongHieu thuongHieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHinhAnh")
    HinhAnh hinhAnh;

    @Column(name = "GiaBan")
    @DecimalMin(value = "79999", inclusive = false, message = "* Giá bán không hợp lệ, nhập giá nhỏ nhất là 80000")
    @DecimalMax(value = "9999999999.99", inclusive = false, message = "* Giá bán không hợp lệ")
    @NotNull(message = "* không để trống giá bán !")
    Double giaBan;


    @Column(name = "SoLuongTon")
    @Min(value = 1, message = "* Số lượng không hợp lệ, số lượng phải lớn hơn 0")
    @Max(value = 999999, message = "* Số lượng không hợp lệ")
    @NotNull(message = "* không để trống số lượng !")
    Integer soLuongTon;



    private String nguoiTao;
    private String nguoiSua;

    @Column(name = "MoTa")
//    @NotBlank(message = "* không để trống mô tả !")
    String moTaCT;

    @Column(name = "NgayTao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayTao;

    @Column(name = "NgaySua")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngaySua;


    @Column(name = "Trangthai")
    @NotNull(message = "* Mời chọn trạng thái !")
    Integer trangThai;



    public void loadFromViewModel(QLSanPham vm) {
        this.setChatLieu(vm.getChatLieu());
        this.setCoAo(vm.getCoAo());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setMoTaCT(vm.getMoTaCT());
        this.setSoLuongTon(vm.getSoLuongTon());
        this.setNgayTao(vm.getNgayTao());
        this.setThuongHieu(vm.getThuongHieu());
        this.setMauSac(vm.getMauSac());
    }
}
