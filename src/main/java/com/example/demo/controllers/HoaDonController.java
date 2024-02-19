
package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.KhachHang;
import com.example.demo.services.HoaDonChiTietSerice;
import com.example.demo.services.HoaDonSerice;
import com.example.demo.services.KhachHangService;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
public class HoaDonController {

    @Autowired
    HoaDonSerice hoaDonSerice;

    @Autowired
    HoaDonChiTietSerice hoaDonChiTietSerice;
    @Autowired
    KhachHangService khachHangService;

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
        List<KhachHang> khachHang = khachHangService.findAll();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        List<HoaDonChiTiet> page = hoaDonChiTietSerice.hoaDonChiTietAll(id);
        model.addAttribute("listKhachHang",khachHang);
        model.addAttribute("listHoaDonChiTiet", page);
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
                            @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        hoaDon.setMa(hoaDon.getMa());
        hoaDon.setTenNguoiNhan(hoaDon.getKhachHang().getHoTen());
//        hoaDon.setEmailNguoiNhan(hoaDon.getKhachHang().getEmail());
        hoaDon.setNgaySua(Date.valueOf(LocalDate.now()));
        hoaDon.setSdtNguoiNhan(hoaDon.getSdtNguoiNhan());
        hoaDonSerice.update(id, hoaDon);
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
}