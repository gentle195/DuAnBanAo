package com.example.demo.models;


import com.example.demo.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QLSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

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


    @Column(name = "MoTa")
    @NotBlank(message = "* không để trống mô tả !")
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

    @Override
    public String toString() {
        return sanPham.getTen();
    }

    public void loadFromDomainModel(ChiTietSanPham domain) {

        this.setChatLieu(domain.getChatLieu());
        this.setCoAo(domain.getCoAo());
        this.setGiaBan(domain.getGiaBan());
        this.setKichCo(domain.getKichCo());
        this.setSanPham(domain.getSanPham());
        this.setTrangThai(domain.getTrangThai());
        this.setMoTaCT(domain.getMoTaCT());
        this.setNgayTao(domain.getNgayTao());
        this.setNgaySua(domain.getNgaySua());
        this.setSoLuongTon(domain.getSoLuongTon());
        this.setThuongHieu(domain.getThuongHieu());
        this.setMauSac(domain.getMauSac());
    }
}
