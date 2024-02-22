
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
import java.time.LocalDate;
import java.util.List;
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
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonAll(pageable);
        model.addAttribute("listHoaDonAll", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return "home/layout";

    }

    @GetMapping("/hoa-don/huy")
    private String hoaDonHuy(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonHuy(pageable);
        model.addAttribute("listHoaDonHuy", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-huy.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/cho-xac-nhan")
    private String hoaDonChoXacNhan(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonChoXacNhan(pageable);
        model.addAttribute("listHoaDonChoXacNhan", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-xac-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/da-xac-nhan")
    private String hoaDonDaThanhToan(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonDaXacNhan(pageable);
        model.addAttribute("listHoaDonDaXacNhan", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-da-xac-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/cho-giao-hang")
    private String hoaDonChoGiaoHang(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonChoGiaoHang(pageable);
        model.addAttribute("listHoaDonChoGiaoHang", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/dang-van-chuyen")
    private String hoaDonDangVanChuyen(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonDangVanChuyen(pageable);
        model.addAttribute("listHoaDonDangVanChuyen", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-dang-van-chuyen.jsp");
        return "home/layout";
    }

//    @GetMapping ("/hoa-don/da-giao-hang")
//    private String hoaDonDaGiaoHang(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
//                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize){
//        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
//        Page<HoaDon> page = hoaDonSerice.hoaDonDaGiaoHang(pageable);
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
//        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
//        Page<HoaDon> page = hoaDonSerice.hoaDonChoThanhToan(pageable);
//        model.addAttribute("listHoaDonChoThanhToan", page.getContent());
//        model.addAttribute("page", page.getNumber());
//        model.addAttribute("total", page.getTotalPages());
//        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-thanh-toan.jsp");
//        return  "home/layout";
//    }


    @GetMapping("/hoa-don/hoan-thanh")
    private String hoaDonHoanThanh(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        Page<HoaDon> page = hoaDonSerice.hoaDonHoanThanh(pageable);
        model.addAttribute("listHoaDonHoanThanh", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-thanh.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/detail/{id}")
    private String hoaDonDetail(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        HoaDon hoaDons = hoaDonSerice.findById(id);
        hoaDonnn=hoaDons;
        idHoaDon=id;
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
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
        hd.setTenNguoiNhan(hoaDon.getKhachHang().getHoTen());
//        hoaDon.setEmailNguoiNhan(hoaDon.getKhachHang().getEmail());
        hd.setNgaySua(Date.valueOf(LocalDate.now()));
        hd.setSdtNguoiNhan(hoaDon.getSdtNguoiNhan());
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
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
        HoaDonChiTiet hdct = hoaDonChiTietSerice.findHoaDonChiTiet(id);
        ChiTietSanPham ctsp = sanPhamService.findCTSPById(hdct.getIdCTSP().getId());
        ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuong());
        ctsp.setNgaySua(Date.valueOf(LocalDate.now()));
        ctsp.setTrangThai(0);
        sanPhamService.updateCTSP(ctsp.getId(), ctsp);
        hoaDonChiTietSerice.delete(id);
        List<HoaDonChiTiet> list = hoaDonChiTietSerice.hoaDonChiTietAll(hdct.getIdHoaDon().getId());
        HoaDon hd = hoaDonSerice.findById(hdct.getIdHoaDon().getId());
        if (hoaDonnn.getPhieuGiamGia() != null) {
            PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
            phieuGiamGia.setTrangThai(0);
            phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
            phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
        }
        if (list.isEmpty()) {
            hd.setTongTien(BigDecimal.ZERO);
            hd.setNgaySua(Date.valueOf(LocalDate.now()));
            total = hd.getTongTien();
//        hd.setNguoiSua();
        } else {
            BigDecimal subTotal = list.stream()
                    .map(hdd -> (BigDecimal.valueOf(hdd.getDonGia().intValue() * hdd.getSoLuong())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            total = subTotal;
            hd.setTongTien(subTotal);
            hd.setNgaySua(Date.valueOf(LocalDate.now()));
//        hd.setNguoiSua();

        }
        hd.setPhieuGiamGia(null);
        hoaDonSerice.update(hd.getId(), hd);
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
            List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon);
            if (listHoaDonChiTiet.isEmpty()) {
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
                if (hoaDonnn.getPhieuGiamGia() != null) {
                    PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
                    phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                    phieuGiamGia.setTrangThai(0);
                    phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                    phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
                }
                total = total.add(BigDecimal.valueOf(hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong()));
                hoaDonnn.setTongTien(total);
//                hoaDonnn.setNguoiSua();
                hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                hoaDonnn.setPhieuGiamGia(null);
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
                for (HoaDonChiTiet hdct : listHoaDonChiTiet) {
                    System.out.println(hdct.getIdCTSP().getId());
                    System.out.println(chiTietSanPham.getId());
                    if (hdct.getIdCTSP().getId().equals(chiTietSanPham.getId())) {
                        hdct.setSoLuong(hdct.getSoLuong() + Integer.valueOf(soLuongChon));
                        hoaDonChiTietSerice.update(hdct.getId(), hdct);
                        ChiTietSanPham chiTiet = sanPhamService.findCTSPById(ct.getId());
                        chiTiet.setSoLuongTon(ct.getSoLuongTon() - Integer.valueOf(soLuongChon));
                        chiTiet.setNgaySua(Date.valueOf(LocalDate.now()));
                        if (hoaDonnn.getPhieuGiamGia() != null) {
                            PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
                            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                            phieuGiamGia.setTrangThai(0);
                            phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                            phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
                        }
                        if (ct.getSoLuongTon() - Integer.valueOf(soLuongChon) == 0) {
                            chiTiet.setTrangThai(1);
                            sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                        } else {
                            chiTiet.setTrangThai(0);
                            sanPhamService.updateCTSP(chiTiet.getId(), chiTiet);
                        }
                        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                        ) {
                            total = total.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                            hoaDonnn.setPhieuGiamGia(null);
                            hoaDonnn.setTongTien(total);
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
                if (hoaDonnn.getPhieuGiamGia() != null) {
                    PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
                    phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                    phieuGiamGia.setTrangThai(0);
                    phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                    phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
                }
                for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                ) {
                    total = total.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                    hoaDonnn.setTongTien(total);
                    hoaDonnn.setPhieuGiamGia(null);
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
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-3/{id}")
    public String UpdateTT3(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setTrangThaiHoaDon(3);
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/hoa-don/update-tt-0/{id}")
    public String UpdateTT0(Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon, @RequestParam("pageNum") Optional<Integer> pageNum,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HoaDon hd = hoaDonSerice.findById(id);
        hd.setTrangThaiHoaDon(0);
        hoaDonSerice.update(id, hd);
        HoaDon hoaDons = hoaDonSerice.findById(id);
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang", khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
        model.addAttribute("hoaDon", hoaDons);
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
//        model.addAttribute("thongBaoThanhCong", "Hóa đơn đã chuyển sang trạng thái hủy, có thể vào trang hóa đơn hủy để xem lại thông tin hóa đơn");
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }
}