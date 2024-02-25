
package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;


@Controller
public class HoaDonController {

    @Autowired
    private HoaDonSerice hoaDonSerice;
    @Autowired
    private HoaDonChiTietSerice hoaDonChiTietSerice;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private KichCoService kichCoService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private CoAoService coAoService;
    @Autowired
    private PhieuGiamGiaService phieuGiamGiaService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    private HoaDon hoaDonnn = new HoaDon();
    private ChiTietSanPham ct = new ChiTietSanPham();
    private UUID idHoaDon = null;

    @GetMapping("/hoa-don/hien-thi")
    private String hoaDonHienThi(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonAll();
        model.addAttribute("listHoaDonAll", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return "home/layout";

    }

    @GetMapping("/hoa-don/huy")
    private String hoaDonHuy(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonHuy();
        model.addAttribute("listHoaDonHuy", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/cho-xac-nhan")
    private String hoaDonChoXacNhan(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonChoXacNhan();
        model.addAttribute("listHoaDonChoXacNhan", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/da-xac-nhan")
    private String hoaDonDaThanhToan(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonDaXacNhan();
        model.addAttribute("listHoaDonDaXacNhan", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/cho-giao-hang")
    private String hoaDonChoGiaoHang(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonChoGiaoHang();
        model.addAttribute("listHoaDonChoGiaoHang", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/dang-van-chuyen")
    private String hoaDonDangVanChuyen(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonDangVanChuyen();
        model.addAttribute("listHoaDonDangVanChuyen", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
        return "home/layout";
    }

//    @GetMapping ("/hoa-don/da-giao-hang")
//    private String hoaDonDaGiaoHang(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
//                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize){
//
//        List<HoaDon> page = hoaDonSerice.hoaDonDaGiaoHang();
//        model.addAttribute("listHoaDonDaGiaoHang", page.getContent());
//        model.addAttribute("page", page.getNumber());
//        model.addAttribute("total", page.getTotalPages());
//        model.addAttribute("contentPage", "../hoadon/hoa-don-da-giao-hang.jsp");
//        return  "home/layout";
//    }
//
//    @GetMapping ("/hoa-don/cho-thanh-toan")
//    private String hoaDonChoThanhToan(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
//                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize){
//
//        List<HoaDon> page = hoaDonSerice.hoaDonChoThanhToan(pageable);
//        model.addAttribute("listHoaDonChoThanhToan", page.getContent());
//        model.addAttribute("page", page.getNumber());
//        model.addAttribute("total", page.getTotalPages());
//        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-thanh-toan.jsp");
//        return  "home/layout";
//    }


    @GetMapping("/hoa-don/hoan-thanh")
    private String hoaDonHoanThanh(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        List<HoaDon> page = hoaDonSerice.hoaDonHoanThanh();
        model.addAttribute("listHoaDonHoanThanh", page);
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/detail/{id}")
    private String hoaDonDetail(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        HoaDon hoaDons = hoaDonSerice.findById(id);
        hoaDonnn = hoaDons;
        idHoaDon = id;
        List<KhachHang> khachHang = khachHangService.findAll();
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/xuat-pdf-hoan-tat/{id}")
    public ResponseEntity<byte[]> xuatPDF(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonSerice.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @PostMapping("/hoa-don/update/{id}")
    public String thanhToan(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setMa(hoaDon.getMa());
        hd.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hd.setKhachHang(hoaDon.getKhachHang());
//        hoaDon.setEmailNguoiNhan(hoaDon.getKhachHang().getEmail());
        hd.setNgaySua(Date.valueOf(LocalDate.now()));
        hd.setSdtNguoiNhan(hoaDon.getSdtNguoiNhan());
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("thongBaoThanhCong", "Cập nhật thông tin hóa đơn thành công");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @PostMapping("/hoa-don/thanh-toan/{id}")
    public ResponseEntity<byte[]> thanhToan(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
//        hoaDon.setNguoiSua();
        hoaDon.setTenNguoiNhan(hoaDon.getKhachHang().getHoTen());
        hoaDon.setEmailNguoiNhan(hoaDon.getKhachHang().getEmail());
        hoaDon.setNgaySua(Date.valueOf(LocalDate.now()));
        hoaDon.setTrangThaiHoaDon(3);
        hoaDon.setTrangThaiGiaoHang(6);
        hoaDon.setPhiShip(BigDecimal.ZERO);
//        hoaDon.setNhanVien();
        hoaDonSerice.update(id, hoaDon);
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(id));
        model.addAttribute("hoaDon", hoaDonSerice.findById(id));
//        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("thongBaoThanhCong", "Thanh toán thành công");
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");

        ResponseEntity<byte[]> responseEntity = hoaDonSerice.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @PostMapping("/hoa-don/loc")
    public String loc(Model model, @RequestParam(value = "sanPham", required = false) UUID idSanPham,
                      @RequestParam(value = "chatLieu", required = false) UUID chatLieuu,
                      @RequestParam(value = "mauSac", required = false) UUID mauSacc,
                      @RequestParam(value = "thuongHieu", required = false) UUID thuongHieuu,
                      @RequestParam(value = "kichCo", required = false) UUID kichCoo,
                      @RequestParam(value = "coAo", required = false) UUID coAoo,
                      @ModelAttribute("hoaDon") HoaDon hoaDon,
                      @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham
    ) {
        HoaDon hd = hoaDonSerice.findById(idHoaDon);
        hoaDonnn = hd;
        model.addAttribute("hoaDon", hd);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        List<ChiTietSanPham> list = sanPhamService.loc(idSanPham, chatLieuu, coAoo, kichCoo, mauSacc, thuongHieuu);
        model.addAttribute("listCTSP", list);
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodallocsanpham", 0);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/delete-hoa-don-chi-tiet/{id}")
    public String deteteHoaDonChiTiet(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                      @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @PathVariable("id") UUID id) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal giaGoc = BigDecimal.ZERO;
        HoaDonChiTiet hdct = hoaDonChiTietSerice.findHoaDonChiTiet(id);
        ChiTietSanPham ctsp = sanPhamService.findCTSPById(hdct.getIdCTSP().getId());
        ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuong());
        ctsp.setNgaySua(Date.valueOf(LocalDate.now()));
        ctsp.setTrangThai(0);
        sanPhamService.updateCTSP(ctsp.getId(), ctsp);
        hoaDonChiTietSerice.delete(id);
        List<HoaDonChiTiet> list = hoaDonChiTietSerice.hoaDonChiTietAll(hdct.getIdHoaDon().getId());
        HoaDon hd = hoaDonSerice.findById(hdct.getIdHoaDon().getId());
        if (list.isEmpty()) {
            if (hd.getPhieuGiamGia() != null) {
                PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hd.getPhieuGiamGia().getId());
                phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                phieuGiamGia.setTrangThai(0);
                phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
            }
            hd.setTongTien(BigDecimal.ZERO);
            hd.setNgaySua(Date.valueOf(LocalDate.now()));
            total = hd.getTongTien();
            hd.setPhieuGiamGia(null);
//        hd.setNguoiSua();
            hoaDonSerice.update(hd.getId(), hd);
        } else {
            for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
            ) {
                giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                if (hd.getPhieuGiamGia() != null) {
                    PhieuGiamGia pgg = hd.getPhieuGiamGia();
                    double giam = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                    double giaMoi = giaGoc.doubleValue() - giam;
                    double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                    if (giam * 2 <= pgg.getGiamToiDa().doubleValue()) {
                        hd.setTongTien(BigDecimal.valueOf(giaMoi));
                        total = BigDecimal.valueOf(giaMoi);
                    } else {
                        hd.setTongTien(BigDecimal.valueOf(giamMax));
                        total = BigDecimal.valueOf(giamMax);
                    }
                } else {
                    BigDecimal subTotal = list.stream()
                            .map(hdd -> (BigDecimal.valueOf(hdd.getDonGia().intValue() * hdd.getSoLuong())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    total = subTotal;
                    hd.setTongTien(subTotal);
                }

                hd.setNgaySua(Date.valueOf(LocalDate.now()));
//        hd.setNguoiSua();
                hoaDonSerice.update(hd.getId(), hd);
            }
        }
        model.addAttribute("tong", String.valueOf(total));
        model.addAttribute("hoaDon", hoaDonnn);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("thongBaoThanhCong", "Xóa sản phẩm thành công");
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/nhap-so-luong/{id}")
    public String nhapSoLuong(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                              @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                              @PathVariable("id") UUID id) {
        model.addAttribute("chiTiet", sanPhamService.findCTSPById(id));
        ct = sanPhamService.findCTSPById(id);
        model.addAttribute("hoaDon", hoaDonnn);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodalnhapsanpham", 0);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/them-gio-hang/{ma}")
    public String qrCode(Model model, @PathVariable("ma") String scan, @ModelAttribute("hoaDon") HoaDon hoaDon,
                         @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham chiTietSanPham1 = sanPhamService.scan(scan);
        if (chiTietSanPham1.getTrangThai() == 1) {
            model.addAttribute("chiTiet", ct);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBao", "Sản phẩm đã hết hàng");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
            return "home/layout";
        } else if (chiTietSanPham1.getTrangThai() == 2) {
            model.addAttribute("chiTiet", ct);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBao", "Sản phẩm đã ngừng kinh doanh");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
            return "home/layout";
        } else {
            model.addAttribute("chiTiet", chiTietSanPham1);
            ct = chiTietSanPham1;
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("batmodalnhapsanpham", 0);
            model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
            return "home/layout";
        }
    }

    @PostMapping("/hoa-don/them-san-pham")
    public String themSanPham(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                              @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                              @RequestParam("soLuong") String soLuongChon) {
        if (Integer.valueOf(soLuongChon) > chiTietSanPham.getSoLuongTon()) {
            model.addAttribute("chiTiet", ct);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBaoSoLuong", "Số lượng quá số lượng tồn");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("batmodalnhapsanpham", 0);
            model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
            return "home/layout";
        } else {
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal giaGoc = BigDecimal.ZERO;
            List<HoaDonChiTiet> listHDCT = hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon);
            if (listHDCT.isEmpty()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setIdCTSP(ct);
                hoaDonChiTiet.setSoLuong(Integer.valueOf(soLuongChon));
                hoaDonChiTiet.setDonGia(ct.getGiaBan());
                hoaDonChiTiet.setIdHoaDon(hoaDonnn);
                hoaDonChiTiet.setTrangThai(0);
                hoaDonChiTietSerice.add(hoaDonChiTiet);
                ChiTietSanPham chiTiet = sanPhamService.findCTSPById(ct.getId());
                chiTiet.setSoLuongTon(ct.getSoLuongTon() - Integer.valueOf(soLuongChon));
                chiTiet.setNgaySua(Date.valueOf(LocalDate.now()));
//                chiTiet.setNguoiSua();
                if (ct.getSoLuongTon() - Integer.valueOf(soLuongChon) == 0) {
                    chiTiet.setTrangThai(1);
                    sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                } else {
                    chiTiet.setTrangThai(0);
                    sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                }
                for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                ) {
                    giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                }
                if (hoaDonnn.getPhieuGiamGia() != null) {
                    PhieuGiamGia pgg = hoaDonnn.getPhieuGiamGia();
                    double giam = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                    double giaMoi = giaGoc.doubleValue() - giam;
                    double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                    if (giam * 2 <= pgg.getGiamToiDa().doubleValue()) {
                        hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
                        total = BigDecimal.valueOf(giaMoi);
                    } else {
                        hoaDonnn.setTongTien(BigDecimal.valueOf(giamMax));
                        total = BigDecimal.valueOf(giamMax);
                    }
                } else {
                    total = total.add(BigDecimal.valueOf(hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong()));
                    hoaDonnn.setTongTien(total);
                }
//                hoaDonnn.setNguoiSua();
                hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                System.out.println(total);
                model.addAttribute("tong", String.valueOf(total));
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
                return "home/layout";
            } else {
                for (HoaDonChiTiet hdct : listHDCT) {
                    System.out.println(hdct.getIdCTSP().getId());
                    System.out.println(chiTietSanPham.getId());
                    if (hdct.getIdCTSP().getId().equals(chiTietSanPham.getId())) {
                        hdct.setSoLuong(hdct.getSoLuong() + Integer.valueOf(soLuongChon));
                        hoaDonChiTietSerice.update(hdct.getId(), hdct);
                        ChiTietSanPham chiTiet = sanPhamService.findCTSPById(ct.getId());
                        chiTiet.setSoLuongTon(ct.getSoLuongTon() - Integer.valueOf(soLuongChon));
                        chiTiet.setNgaySua(Date.valueOf(LocalDate.now()));


                        if (ct.getSoLuongTon() - Integer.valueOf(soLuongChon) == 0) {
                            chiTiet.setTrangThai(1);
                            sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                        } else {
                            chiTiet.setTrangThai(0);
                            sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                        }
                        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                        ) {
                            giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                            System.out.println(giaGoc);
                            if (hoaDonnn.getPhieuGiamGia() != null) {
                                PhieuGiamGia pgg = hoaDonnn.getPhieuGiamGia();
                                double giam = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                                double giaMoi = giaGoc.doubleValue() - giam;
                                double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                                if (giam * 2 <= pgg.getGiamToiDa().doubleValue()) {
                                    hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
                                    total = BigDecimal.valueOf(giaMoi);
                                } else {
                                    hoaDonnn.setTongTien(BigDecimal.valueOf(giamMax));
                                    total = BigDecimal.valueOf(giamMax);
                                }
                            } else {
                                total = total.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                                hoaDonnn.setTongTien(total);

                            }
                            hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                            hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                        }
                        System.out.println(total);
                        model.addAttribute("tong", String.valueOf(total));
                        model.addAttribute("hoaDon", hoaDonnn);
                        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
                        model.addAttribute("listHoaDon", listHD);
                        model.addAttribute("listNhanVien", nhanVienService.findAll());
                        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                        model.addAttribute("listKhachHang", khachHangService.findAll());
                        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                        model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                        model.addAttribute("listChatLieu", chatLieuService.findAll());
                        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                        model.addAttribute("listCoAo", coAoService.findAll());
                        model.addAttribute("listMauSac", mauSacService.findAll());
                        model.addAttribute("listKichCo", kichCoService.findAll());
                        model.addAttribute("listSanPham", sanPhamService.findAll());
                        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
                        return "home/layout";
                    }

                }
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setIdCTSP(ct);
                hoaDonChiTiet.setSoLuong(Integer.valueOf(soLuongChon));
                hoaDonChiTiet.setDonGia(ct.getGiaBan());
                hoaDonChiTiet.setIdHoaDon(hoaDonnn);
                hoaDonChiTiet.setTrangThai(0);
                hoaDonChiTietSerice.add(hoaDonChiTiet);
                ChiTietSanPham chiTiet = sanPhamService.findCTSPById(ct.getId());
                chiTiet.setSoLuongTon(ct.getSoLuongTon() - Integer.valueOf(soLuongChon));
                chiTiet.setNgaySua(Date.valueOf(LocalDate.now()));
//                chiTiet.setNguoiSua();
                if (ct.getSoLuongTon() - Integer.valueOf(soLuongChon) == 0) {
                    chiTiet.setTrangThai(1);
                    sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                } else {
                    chiTiet.setTrangThai(0);
                    sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                }

                for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                ) {
                    giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                    if (hoaDonnn.getPhieuGiamGia() != null) {
                        PhieuGiamGia pgg = hoaDonnn.getPhieuGiamGia();
                        double giam = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                        double giaMoi = giaGoc.doubleValue() - giam;
                        double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                        if (giam * 2 <= pgg.getGiamToiDa().doubleValue()) {
                            hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
                            total = BigDecimal.valueOf(giaMoi);
                        } else {
                            hoaDonnn.setTongTien(BigDecimal.valueOf(giamMax));
                            total = BigDecimal.valueOf(giamMax);
                        }
                    } else {
                        total = total.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                        hoaDonnn.setTongTien(total);
                    }
                    hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                    hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                }
//                hoaDonnn.setNguoiSua();
                System.out.println(total);
                model.addAttribute("tong", String.valueOf(total));
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
                return "home/layout";
            }
        }
    }

    @GetMapping("/hoa-don/cap-nhat-so-luong/{id}")
    public String capNhatSoLuong(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                 @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @PathVariable("id") UUID id) {
        if (hoaDonnn.getPhieuGiamGia() != null) {
            PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
            phieuGiamGia.setTrangThai(0);
            phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
            phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
        }
        HoaDonChiTiet hdct = hoaDonChiTietSerice.findHoaDonChiTiet(id);
        model.addAttribute("chiTiet", hdct.getIdCTSP());
        ct = hdct.getIdCTSP();
        model.addAttribute("hoaDon", hoaDonnn);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodalnhapsanpham", 0);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-1/{id}")
    public String UpdateTT(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setTrangThaiHoaDon(1);
        hd.setTrangThaiGiaoHang(2);
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("thongBaoThanhCong", "Xác nhận hóa đơn thành công");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-3/{id}")
    public String UpdateTT3(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setTrangThaiHoaDon(3);
        hd.setTrangThaiGiaoHang(6);
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("thongBaoThanhCong", "Thanh toán hóa đơn thành công");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-0/{id}")
    public String UpdateTT0(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setTrangThaiHoaDon(0);
        hd.setTrangThaiGiaoHang(1);
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("thongBaoThanhCong", "Hóa đơn đã quay về trạng thái chờ xác nhận");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-8/{id}")
    public String UpdateTT8(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hoaDonnn = hd;
        if (hoaDonnn.getPhieuGiamGia() != null) {
            PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
            phieuGiamGia.setTrangThai(0);
            phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
            phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
        }
        LocalDate capNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = sanPhamService.findCTSPById(hdct.getIdCTSP().getId());
                ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuong());
                ctsp.setNgaySua(Date.valueOf(capNhat));
                ctsp.setTrangThai(0);
                sanPhamService.updateCTSP(ctsp.getId(), ctsp);
                hdct.setTrangThai(8);
                hoaDonChiTietSerice.update(hdct.getId(), hdct);
            }
            hd.setTrangThaiHoaDon(8);
            hd.setTrangThaiGiaoHang(5);
            hd.setNgaySua(Date.valueOf(capNhat));
//            hd.setNguoiSua();
            hoaDonSerice.update(id, hd);
        } else {
            hd.setTrangThaiHoaDon(8);
            hd.setTrangThaiGiaoHang(5);
            hd.setNgaySua(Date.valueOf(capNhat));
//            hd.setNguoiSua();
            hoaDonSerice.update(id, hd);
        }
        model.addAttribute("hoaDon", hoaDonSerice.findById(id));
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(id));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("thongBaoThanhCong", "Hóa đơn đã chuyển sang trạng thái hủy");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/chon-phieu-giam-gia/{id}")
    public String chonPhieu(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        BigDecimal giaGoc = BigDecimal.ZERO;
        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
        ) {
            giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
        }
        if (giaGoc.compareTo(phieuGiamGiaService.findById(id).getGiamToiThieu()) < 0) {
            model.addAttribute("hoaDon", hoaDonnn);
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String giaTien = nf.format(phieuGiamGiaService.findById(id).getGiamToiThieu());
            model.addAttribute("thongBao", "Tổng tiền hóa đơn chưa đủ để sử dụng phiếu giảm giá này, tổng tiền tối thiều: " + giaTien);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
            return "home/layout";
        } else {
            if (hoaDonnn.getPhieuGiamGia() == null) {
                PhieuGiamGia pgg = phieuGiamGiaService.findById(id);
                double giam = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                double giaMoi = giaGoc.doubleValue() - giam;
                double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                if (giam * 2 <= pgg.getGiamToiDa().doubleValue()) {
                    hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
                } else {
                    hoaDonnn.setTongTien(BigDecimal.valueOf(giamMax));
                }
//            hoaDonnn.setNguoiSua();
                hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                hoaDonnn.setPhieuGiamGia(pgg);
                hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                pgg.setSoLuong(pgg.getSoLuong() - 1);
                pgg.setNgaySua(Date.valueOf(LocalDate.now()));
                if (pgg.getSoLuong() == 0) {
                    pgg.setTrangThai(2);
                }
//            pgg.setNguoiSua(Date.valueOf(LocalDate.now()));
                phieuGiamGiaService.update(pgg.getId(), pgg);
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("thongBaoThanhCong", "Thêm phiếu giảm giá thành công");
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
                return "home/layout";
            } else {
                PhieuGiamGia pgg = phieuGiamGiaService.findById(id);
                PhieuGiamGia pggc = hoaDonnn.getPhieuGiamGia();
                pggc.setSoLuong(hoaDonnn.getPhieuGiamGia().getSoLuong() + 1);
                pggc.setTrangThai(0);
                pggc.setNgaySua(Date.valueOf(LocalDate.now()));
//            pggc.setNguoiSua(Date.valueOf(LocalDate.now()));
                phieuGiamGiaService.update(pggc.getId(), pggc);
                double giamMoi = giaGoc.doubleValue() * pgg.getTienGiam() / 100;
                double giaMoi = giaGoc.doubleValue() - giamMoi;
                double giamMax = giaGoc.doubleValue() - pgg.getGiamToiDa().doubleValue();
                if (giamMoi * 2 <= pgg.getGiamToiDa().doubleValue()) {
                    hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
                } else {
                    hoaDonnn.setTongTien(BigDecimal.valueOf(giamMax));
                }
//            hoaDonnn.setNguoiSua();
                hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                hoaDonnn.setPhieuGiamGia(pgg);
                hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                pgg.setSoLuong(pgg.getSoLuong() - 1);
                pgg.setNgaySua(Date.valueOf(LocalDate.now()));
//            pgg.setNguoiSua(Date.valueOf(LocalDate.now()));
                if (pgg.getSoLuong() == 0) {
                    pgg.setTrangThai(2);
                }
                phieuGiamGiaService.update(pgg.getId(), pgg);
                model.addAttribute("hoadon", hoaDonnn);
                model.addAttribute("thongBaoThanhCong", "Đổi phiếu giảm giá thành công");
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-tong")
    public String locTong(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @RequestParam(value = "nhanVien", required = false) UUID idNV,
                          @RequestParam(value = "khachHang", required = false) UUID idKH,
                          @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                          @RequestParam(value = "giaoHang", required = false) Integer giaoHang,
                          @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                          @RequestParam(value = "startDate", required = false) String startDate,
                          @RequestParam(value = "startSua", required = false) String startSua,
                          @RequestParam(value = "endSua", required = false) String endSua,
                          @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, giaoHang, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-cho-xac-nhan")
    public String locChoXacNhan(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                @RequestParam(value = "nhanVien", required = false) UUID idNV,
                                @RequestParam(value = "khachHang", required = false) UUID idKH,
                                @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                                @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                                @RequestParam(value = "startDate", required = false) String startDate,
                                @RequestParam(value = "startSua", required = false) String startSua,
                                @RequestParam(value = "endSua", required = false) String endSua,
                                @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 1, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-da-xac-nhan")
    public String locDaXacNhan(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                               @RequestParam(value = "nhanVien", required = false) UUID idNV,
                               @RequestParam(value = "khachHang", required = false) UUID idKH,
                               @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                               @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "startSua", required = false) String startSua,
                               @RequestParam(value = "endSua", required = false) String endSua,
                               @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 2, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-cho-giao-hang")
    public String locChoGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                 @RequestParam(value = "nhanVien", required = false) UUID idNV,
                                 @RequestParam(value = "khachHang", required = false) UUID idKH,
                                 @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                                 @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "startSua", required = false) String startSua,
                                 @RequestParam(value = "endSua", required = false) String endSua,
                                 @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 3, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-dang-van-chuyen")
    public String locDangVanChuyen(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                   @RequestParam(value = "nhanVien", required = false) UUID idNV,
                                   @RequestParam(value = "khachHang", required = false) UUID idKH,
                                   @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                                   @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                                   @RequestParam(value = "startDate", required = false) String startDate,
                                   @RequestParam(value = "startSua", required = false) String startSua,
                                   @RequestParam(value = "endSua", required = false) String endSua,
                                   @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 4, ttHoaDon, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-hoan-thanh")
    public String locHoanThanh(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                               @RequestParam(value = "nhanVien", required = false) UUID idNV,
                               @RequestParam(value = "khachHang", required = false) UUID idKH,
                               @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                               @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "startSua", required = false) String startSua,
                               @RequestParam(value = "endSua", required = false) String endSua,
                               @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 6, 3, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/hoa-don/loc-huy")
    public String locHuy(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                         @RequestParam(value = "nhanVien", required = false) UUID idNV,
                         @RequestParam(value = "khachHang", required = false) UUID idKH,
                         @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                         @RequestParam(value = "ttHoaDon", required = false) Integer ttHoaDon,
                         @RequestParam(value = "startDate", required = false) String startDate,
                         @RequestParam(value = "startSua", required = false) String startSua,
                         @RequestParam(value = "endSua", required = false) String endSua,
                         @RequestParam(value = "endDate", required = false) String endDate
    ) {
        if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, null, null, null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(endDate));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, null, null, Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && startSua.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";
        } else if (startSua.isEmpty() && endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), null, null);
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, null, null, Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(endDate), null, null);
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }
        } else if (startSua.isEmpty() && startDate.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";

        } else if (startDate.isEmpty() && endSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";

        } else if (endDate.isEmpty() && startSua.isEmpty()) {
            List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
            model.addAttribute("listHoaDonAll", list);
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
            return "home/layout";

        } else if (startDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(LocalDate.now()), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }

        } else if (endDate.isEmpty()) {
            LocalDate start = Date.valueOf(startSua).toLocalDate();
            LocalDate end = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(LocalDate.now()), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }

        } else if (startSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(LocalDate.now()), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }

        } else if (endSua.isEmpty()) {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            if (check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(LocalDate.now()));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }

        } else {
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            LocalDate checkStartSua = Date.valueOf(startSua).toLocalDate();
            LocalDate checkEndSua = Date.valueOf(endSua).toLocalDate();
            int check = start.compareTo(end);
            int checkSua = checkStartSua.compareTo(checkEndSua);
            if (check > 0 && checkSua < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check < 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else if (checkSua > 0 && check > 0) {
                model.addAttribute("listHoaDonAll", hoaDonSerice.hoaDonAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("thongBao", "Ngày tạo và ngày sửa bị lỗi, mời chọn đúng khoảng ngày để lọc");
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            } else {
                List<HoaDon> list = hoaDonSerice.locTong(idKH, idNV, loaiHoaDon, 5, 8, Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(startSua), Date.valueOf(endSua));
                model.addAttribute("listHoaDonAll", list);
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
                return "home/layout";
            }
        }
    }
}