package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
public class BanHangTaiQuayController {
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

    @Scheduled(cron = "0 0 */4 * * ?")
    public void autoUpdate() {
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        for (HoaDon hd : listHD
        ) {
            if (hd.getPhieuGiamGia() != null) {
                PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hd.getPhieuGiamGia().getId());
                phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                phieuGiamGia.setTrangThai(0);
                phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
            }
            LocalDate capNhat = LocalDate.now();
            List<HoaDonChiTiet> list = hoaDonChiTietSerice.hoaDonChiTietAll(hd.getId());
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
                hoaDonSerice.update(hd.getId(), hd);
            } else {
                hd.setTrangThaiHoaDon(8);
                hd.setTrangThaiGiaoHang(5);
                hd.setNgaySua(Date.valueOf(capNhat));
//            hd.setNguoiSua();
                hoaDonSerice.update(hd.getId(), hd);
            }
        }
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                          @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        hoaDonnn = null;
        List<HoaDon> list = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", list);
        model.addAttribute("HoaDonCho", hoaDonnn);
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-hoa-don")
    public String addHoaDon(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        List<HoaDon> list = hoaDonSerice.hoaDonCho();
        if (list.size() > 3) {
            hoaDonnn = null;
            model.addAttribute("thongBao", "Đã quá số lượng hóa đơn chờ");
            model.addAttribute("listHoaDon", list);
            model.addAttribute("HoaDonCho", hoaDonnn);
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            HoaDon hd = new HoaDon();
            String mhd = "";
            Integer sl = (hoaDonSerice.findAll().size() + 1);
            if (sl < 10) {
                mhd = "MHD0" + sl;
            } else {
                mhd = "MHD" + sl;
            }
            hd.setMa(mhd);
            hd.setNgayTao(Date.valueOf(LocalDate.now()));
            hd.setLoaiHoaDon(0);
            hd.setTrangThaiHoaDon(0);
            hd.setTrangThaiGiaoHang(1);
            hoaDonSerice.add(hd);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("thongBaoThanhCong", "Thêm hóa đơn chờ thành công");
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("HoaDonCho", null);
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/thong-tin-hoa-don/{id}")
    public String thongTinHoaDon(Model model,
                                 @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                                 @PathVariable("id") UUID id,
                                 @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        HoaDon hd = hoaDonSerice.findById(id);
        idHoaDon = id;
        hoaDonnn = hd;
        model.addAttribute("HoaDonCho", hd);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(id));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    //    @GetMapping("/remove/{id}")
//    public String Remove(Model model, @PathVariable("id") UUID id) {
//        HoaDon hd = hoaDonSerice.findById(id);
//
//    }
    @PostMapping("/loc")
    public String loc(Model model, @RequestParam(value = "sanPham", required = false) UUID idSanPham,
                      @RequestParam(value = "chatLieu", required = false) UUID chatLieuu,
                      @RequestParam(value = "mauSac", required = false) UUID mauSacc,
                      @RequestParam(value = "thuongHieu", required = false) UUID thuongHieuu,
                      @RequestParam(value = "kichCo", required = false) UUID kichCoo,
                      @RequestParam(value = "coAo", required = false) UUID coAoo,
                      @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                      @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham
    ) {
        HoaDon hd = hoaDonSerice.findById(idHoaDon);
        hoaDonnn = hd;
        model.addAttribute("HoaDonCho", hd);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        List<ChiTietSanPham> list = sanPhamService.loc(idSanPham, chatLieuu, coAoo, kichCoo, mauSacc, thuongHieuu);
        model.addAttribute("listCTSP", list);
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodallocsanpham", 0);
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/nhap-so-luong/{id}")
    public String nhapSoLuong(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                              @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                              @PathVariable("id") UUID id) {
        model.addAttribute("chiTiet", sanPhamService.findCTSPById(id));
        ct = sanPhamService.findCTSPById(id);
        model.addAttribute("HoaDonCho", hoaDonnn);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodalnhapsanpham", 0);
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/them-san-pham")
    public String themSanPham(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                              @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                              @RequestParam("soLuong") String soLuongChon) {
        if (Integer.valueOf(soLuongChon) > chiTietSanPham.getSoLuongTon()) {
            model.addAttribute("chiTiet", ct);
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBaoSoLuong", "Số lượng quá số lượng tồn");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("batmodalnhapsanpham", 0);
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            BigDecimal total = BigDecimal.ZERO;
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
                model.addAttribute("HoaDonCho", hoaDonnn);
                List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
                        model.addAttribute("HoaDonCho", hoaDonnn);
                        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
                        model.addAttribute("listHoaDon", listHD);
                        model.addAttribute("listNhanVien", nhanVienService.findAll());
                        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                        model.addAttribute("listKhachHang", khachHangService.findAll());
                        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                        model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                        model.addAttribute("listChatLieu", chatLieuService.findAll());
                        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                        model.addAttribute("listCoAo", coAoService.findAll());
                        model.addAttribute("listMauSac", mauSacService.findAll());
                        model.addAttribute("listKichCo", kichCoService.findAll());
                        model.addAttribute("listSanPham", sanPhamService.findAll());
                        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
                PhieuGiamGia phieuGiamGia = phieuGiamGiaService.findById(hoaDonnn.getPhieuGiamGia().getId());
                for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
                ) {
                    total = total.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
                    hoaDonnn.setTongTien(total);
                    phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);
                    phieuGiamGia.setTrangThai(0);
                    phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
                    phieuGiamGiaService.update(phieuGiamGia.getId(), phieuGiamGia);
                    hoaDonnn.setPhieuGiamGia(null);
                    hoaDonnn.setNgaySua(Date.valueOf(LocalDate.now()));
                    hoaDonSerice.update(hoaDonnn.getId(), hoaDonnn);
                }
//                hoaDonnn.setNguoiSua();
                System.out.println(total);
                model.addAttribute("tong", String.valueOf(total));
                model.addAttribute("HoaDonCho", hoaDonnn);
                List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("listNhanVien", nhanVienService.findAll());
                model.addAttribute("listPGG", phieuGiamGiaService.findAll());
                model.addAttribute("listKhachHang", khachHangService.findAll());
                model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
                model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
                model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
                model.addAttribute("listChatLieu", chatLieuService.findAll());
                model.addAttribute("listThuongHieu", thuongHieuService.findAll());
                model.addAttribute("listCoAo", coAoService.findAll());
                model.addAttribute("listMauSac", mauSacService.findAll());
                model.addAttribute("listKichCo", kichCoService.findAll());
                model.addAttribute("listSanPham", sanPhamService.findAll());
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
                return "home/layout";
            }
        }
    }

    @GetMapping("/them-gio-hang/{ma}")
    public String qrCode(Model model, @PathVariable("ma") String scan, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                         @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham chiTietSanPham1 = sanPhamService.scan(scan);
        if (chiTietSanPham1.getTrangThai() == 1) {
            model.addAttribute("chiTiet", ct);
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBao", "Sản phẩm đã hết hàng");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        } else if (chiTietSanPham1.getTrangThai() == 2) {
            model.addAttribute("chiTiet", ct);
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("thongBao", "Sản phẩm đã ngừng kinh doanh");
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            model.addAttribute("chiTiet", chiTietSanPham1);
            ct = chiTietSanPham1;
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("batmodalnhapsanpham", 0);
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/delete-hoa-don-chi-tiet/{id}")
    public String deteteHoaDonChiTiet(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
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
        model.addAttribute("HoaDonCho", hoaDonnn);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("thongBaoThanhCong", "Thêm sản phẩm thành công");
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/cap-nhat-so-luong/{id}")
    public String capNhatSoLuong(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
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
        model.addAttribute("HoaDonCho", hoaDonnn);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("batmodalnhapsanpham", 0);
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/remove/{id}")
    public String removeHoaDon(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                               @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                               @PathVariable("id") UUID id) {
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
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("HoaDonCho", null);
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(id));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("thongBaoThanhCong", "Hóa đơn đã chuyển sang trạng thái hủy, có thể vào trang hóa đơn hủy để xem lại thông tin hóa đơn");
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/search-hoa-don-chi-tiet")
    public List<HoaDonChiTiet> searchHoaDonChiTiet(Model model, @RequestParam("search-hoa-don-chi-tiet") String search,
                                                   @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                                                   @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietSerice.searchBanTaiQuay(idHoaDon, search);
        return listHoaDonChiTiets;
    }

    @GetMapping("/chon-phieu-giam-gia/{id}")
    public String chonPhieu(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                            @PathVariable("id") UUID id) {
        BigDecimal giaGoc = BigDecimal.ZERO;
        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon)
        ) {
            giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
        }
        if (hoaDonnn.getPhieuGiamGia() == null) {
            PhieuGiamGia pgg = phieuGiamGiaService.findById(id);
            double giam = hoaDonnn.getTongTien().doubleValue() * pgg.getTienGiam() / 100;
            double giaMoi = hoaDonnn.getTongTien().doubleValue() - giam;
            hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
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
            model.addAttribute("tong", String.valueOf(hoaDonnn.getTongTien()));
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("thongBaoThanhCong", "Thêm phiếu giảm giá thành công");
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
            hoaDonnn.setTongTien(BigDecimal.valueOf(giaMoi));
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
            model.addAttribute("tong", String.valueOf(hoaDonnn.getTongTien()));
            model.addAttribute("HoaDonCho", hoaDonnn);
            List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("thongBaoThanhCong", "Đổi phiếu giảm giá thành công");
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listPGG", phieuGiamGiaService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
            model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listSanPham", sanPhamService.findAll());
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/bo-phieu/{id}")
    public String boPhieu(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                          @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                          @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonSerice.findById(id);
        PhieuGiamGia pgg = phieuGiamGiaService.findById(hd.getPhieuGiamGia().getId());
//        pgg.setNguoiSua();
        pgg.setSoLuong(pgg.getSoLuong() + 1);
        pgg.setNgaySua(Date.valueOf(LocalDate.now()));
        pgg.setTrangThai(0);
        phieuGiamGiaService.update(pgg.getId(), pgg);
        BigDecimal giaGoc = BigDecimal.ZERO;
        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(hd.getId())
        ) {
            giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));
        }
        hd.setPhieuGiamGia(null);
        hd.setTongTien(giaGoc);
        hd.setNgaySua(Date.valueOf(LocalDate.now()));
//        hd.setNguoiSua();
        hoaDonSerice.update(hd.getId(), hd);
        hoaDonnn = hoaDonSerice.findById(id);
        model.addAttribute("tong", String.valueOf(hoaDonnn.getTongTien()));
        model.addAttribute("HoaDonCho", hoaDonnn);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("thongBaoThanhCong", "Bỏ phiếu giảm giá thành công");
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-khach-hang")
    public String addKhachHang(Model model, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                               @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                               @RequestParam("hoTen") String hoten, @RequestParam("gioiTinh") String gioiTinh,
                               @RequestParam("sđt") String sđt, @RequestParam("ngaySinh") String ngaySinh) {
        KhachHang kh = new KhachHang();
        kh.setHoTen(hoten);
        kh.setGioiTinh(Boolean.valueOf(gioiTinh));
        kh.setSoDienThoai(sđt);
        kh.setNgaySinh(Date.valueOf(ngaySinh));
        kh.setNgayTao(Date.valueOf(LocalDate.now()));
        kh.setTrangThai(0);
//        kh.setNguoiTao();
        khachHangService.add(kh);
        model.addAttribute("tong", String.valueOf(hoaDonnn.getTongTien()));
        model.addAttribute("HoaDonCho", hoaDonnn);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listPGG", phieuGiamGiaService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("thongBaoThanhCong", "Thêm khách hàng thành công");
        model.addAttribute("listHDCT", hoaDonChiTietSerice.hoaDonChiTietAll(idHoaDon));
        model.addAttribute("listCTSP", sanPhamService.findAllCTSP());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listSanPham", sanPhamService.findAll());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/thanh-toan/{id}")
    public ResponseEntity<byte[]> thanhToan(Model model, @PathVariable("id") UUID id, @ModelAttribute("HoaDonCho") HoaDon hoaDon,
                                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
//        hoaDon.setNguoiSua();
        hoaDon.setTenNguoiNhan(hoaDon.getKhachHang().getHoTen());
        hoaDon.setEmailNguoiNhan(hoaDon.getKhachHang().getEmail());
        hoaDon.setNgaySua(Date.valueOf(LocalDate.now()));
        hoaDon.setTrangThaiHoaDon(3);
        hoaDon.setTrangThaiGiaoHang(6);
        hoaDon.setPhiShip(BigDecimal.ZERO);
        hoaDonSerice.update(id, hoaDon);
        List<HoaDon> listHD = hoaDonSerice.hoaDonCho();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("HoaDonCho", null);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
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
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");

        ResponseEntity<byte[]> responseEntity = hoaDonSerice.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

}
