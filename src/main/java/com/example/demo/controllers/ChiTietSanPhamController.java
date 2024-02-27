package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
public class ChiTietSanPhamController<MauSacReponsitory> {
    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    ThuongHieuService deGiayRepo;
//    @Autowired
//    ExcelServiceImpl excelService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private KichCoService kichCoService;
    @Autowired
    private CoAoService loaiGiayRepo;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Ngưng Hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("listDeGiay")
    List<ThuongHieu> listDeGiay() {
        return deGiayRepo.findAll();
    }

    @ModelAttribute("listChatLieu")
    List<ChatLieu> listChatLieu() {
        return chatLieuService.findAll();
    }

    @ModelAttribute("listKichCo")
    List<KichCo> listKichCo() {
        return kichCoService.findAll();
    }

    @ModelAttribute("listMau")
    List<MauSac> listMauSac() {
        return mauSacService.findAll();
    }

    @ModelAttribute("listLoaiGiay")
    List<CoAo> listLoaiGiay() {
        return loaiGiayRepo.findAll();
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @Data
    public static class SearchFormSP {
        String keyword;
    }

    @Data
    public static class SearchFormSPByMau {
        UUID idMau;
    }

    @Data
    public static class SearchDeGiay {
        UUID idDe;
    }

    @Data
    public static class SearchKC {
        UUID idKC;
    }

    @Data
    public static class SearchLoaiGiay {
        UUID idLG;
    }

    @Data
    public static class SearchChatlieu {
        UUID idChatLieu;
    }

    @Data
    public static class SortFormSP {
        String key = "";
    }

    @ModelAttribute("listSP")
    List<SanPham> listSP() {
        return sanPhamRepo.findAll();
    }

    @RequestMapping("/chi-tiet-san-pham/hien-thi")

    public String hienThiSanPham(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("sanpham") QLSanPham sp, @RequestParam(defaultValue = "0") int p, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }

        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());

        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
//
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";

    }

    // search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search2-loai-giay")
    public ResponseEntity<?> search2LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(chiTietSanPhamRepo.listLoaiGiay());
        } else {
            return ResponseEntity.ok(service.search2CA("%" + keyword + "%"));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search2-kich-co")
    public ResponseEntity<List<KichCo>> search2KichCo(@RequestParam(name = "size", required = false) String size) {
        if (size == null || size == "") {
            return ResponseEntity.ok(chiTietSanPhamRepo.listKC());
        } else {
            return ResponseEntity.ok(service.search2KC("%" + size + "%"));
        }
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search2-mau-sac")
    public ResponseEntity<?> search2MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(mauSacService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchMS("%" + ten + "%"));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search2-de-giay")
    public ResponseEntity<?> search2DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(deGiayRepo.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchDG("%" + loaiDe + "%"));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search2-chat-lieu")
    public ResponseEntity<?> search2CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chatLieuService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchCL("%" + ten + "%"));
        }
    }


    // 12/11/2023
// search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search22-loai-giay")
    public ResponseEntity<?> search22LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(service.listLG22(0));
        } else {
            return ResponseEntity.ok(service.search22LG("%" + keyword + "%", 0));
        }
    }
    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search22-de-giay")
    public ResponseEntity<?> search22DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(service.listDeGiay22(0));
        } else {
            return ResponseEntity.ok(service.search22DG("%" + loaiDe + "%", 0));
        }
    }
    @GetMapping("/chi-tiet-san-pham/search22-kich-co")
    public ResponseEntity<List<KichCo>> search22KichCo(@RequestParam(name = "keyword", required = false) String size) {
        if (size != null || size.equals("")) {
            // Xử lý khi 'size' có giá trị
            return ResponseEntity.ok(service.listKichCo22(0));
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            return ResponseEntity.ok(chiTietSanPhamRepo.search22KC("%" + size + "%", 0));
        }
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search22-mau-sac")
    public ResponseEntity<?> search22MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listMauSac22(0));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22MS("%" + ten + "%", 0));
        }
    }



    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search22-chat-lieu")
    public ResponseEntity<?> search22CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listChatLieu22(0));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22CL("%" + ten + "%", 0));
        }
    }

    //

    @RequestMapping("/chi-tiet-san-pham/search")
    public String searchSP(@ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchFormSP.keyword != null && !searchFormSP.keyword.equals("")) {
            qlSanPhamPage = service.searchCTSP(searchFormSP.keyword, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);

        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox mau-sac
    @RequestMapping("/chi-tiet-san-pham/search-by-mausac")
    public String searchByMau(@ModelAttribute("searchFormByMau") SearchFormSPByMau searchFormSPByMau, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchFormSPByMau.idMau != null && !searchFormSPByMau.idMau.equals("")) {
            qlSanPhamPage = service.searchByMau(searchFormSPByMau.idMau, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchKC", new SearchKC());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox kich co
    @RequestMapping("/chi-tiet-san-pham/search-by-kichco")
    public String searchByKC(@ModelAttribute("searchKC") SearchKC searchKC, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchKC.idKC != null && !searchKC.idKC.equals("")) {
            qlSanPhamPage = service.searchKichCo(searchKC.idKC, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox de giay
    @RequestMapping("/chi-tiet-san-pham/search-by-degiay")
    public String searchByDeGiay(@ModelAttribute("searchDG") SearchDeGiay searchDeGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchDeGiay.idDe != null && !searchDeGiay.idDe.equals("")) {
            qlSanPhamPage = service.searchDeGiay(searchDeGiay.idDe, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // Lỗi đâu Nam chịu
    @RequestMapping("/chi-tiet-san-pham/listLoaiGiay")
    public String listLoaiGiay(@ModelAttribute("lg") SearchLoaiGiay searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = service.searchLoaiGiay(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }


    // filer with combobox chat lieu
    @RequestMapping("/chi-tiet-san-pham/search-by-chatlieu")
    public String searchByChatLieu(@ModelAttribute("searchChatLieu") SearchChatlieu searchChatlieu, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchChatlieu.idChatLieu != null && !searchChatlieu.idChatLieu.equals("")) {
            qlSanPhamPage = service.searchCL(searchChatlieu.idChatLieu, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("lg", new SearchLoaiGiay());

        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox loai giay
    @RequestMapping("/chi-tiet-san-pham/search-by-loaigiay")
    public String searchByLoaiGiay(@ModelAttribute("lg") SearchLoaiGiay searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = service.searchLoaiGiay(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    @RequestMapping("/chi-tiet-san-pham/sort")
    public String sort(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Sort sort;
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        sort = sortFormSP.key.equals("giaBan") ? Sort.by(Sort.Direction.DESC, "giaBan") : Sort.by(Sort.Direction.DESC, "giaBan");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);

        System.out.println(sortFormSP.key);
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }


    @RequestMapping("/chi-tiet-san-pham/update/{id}")
    public String updateKC(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
//            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
            return "home/layout";
        }


        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr

        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/hien-thi");
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @RequestMapping("/chi-tiet-san-pham/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);

        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());

        model.addAttribute("act", "update");
        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }
//update list-spct

    @RequestMapping("/chi-tiet-san-pham/update-sp/{id}")
    public String updateSPCT(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
            return "home/layout";
        }

        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());
        model.addAttribute("searchForm", new SearchFormSP());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr
        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/list-san-pham/" + idSP);
        model.addAttribute("view", "../chi-tiet-san-pham/list-spct.jsp");
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + idSP;
    }

    @RequestMapping("/chi-tiet-san-pham/view-update-ctsp/{id}")
    public String viewUpdateCTSP(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);
        model.addAttribute("act", "update");
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());


        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update-sp/" + sp.getId());
        model.addAttribute("sanpham", sp);
//        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }

    //    // modal
    @Autowired
    SanPhamService sanPhamService;

    @RequestMapping("/chi-tiet-san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") CoAo coAo, BindingResult result) {
        Boolean hasE = result.hasErrors();
        Map<String, Object> response = new HashMap<>();
        List<CoAo> list = loaiGiayRepo.findAll();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }

        if (loaiGiayRepo.findByMa(coAo.getMa()) != null) {
            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (loaiGiayRepo.findByTen(coAo.getTen()) != null) {
            result.rejectValue("ten", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        loaiGiayRepo.add(coAo);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/chi-tiet-san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            return response;
        }

        if (kichCoService.findByMa(kichCo.getMa()) != null) {
            result.rejectValue("ma", "duplicate3", "Lỗi! Mã không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (kichCoService.findByTen(kichCo.getTen()) != null) {
            result.rejectValue("ten", "duplicate3", "Lỗi! Size không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        kichCoService.add(kichCo);
        response.put("status", "success");
        return response;
    }

    @PostMapping("/chi-tiet-san-pham/mau-sac/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("ms") MauSac ms, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());

        if (result.hasErrors()) {
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            return response;
        }

        if (mauSacService.findByMa(ms.getMa()) != null) {
            result.rejectValue("ma", "duplicate2", "Lỗi! Mã không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (mauSacService.findByTen(ms.getTen()) != null) {
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng hoho");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        mauSacService.add(ms);
        response.put("status", "success");
        return response;
    }



    @RequestMapping("/chi-tiet-san-pham/chat-lieu/add/{id}")
    @ResponseBody
    public Map<String, Object> store(Model model, @PathVariable("id") UUID id,
                                     @Valid @ModelAttribute("vm") ChatLieu cl,
                                     BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("degiay", new DeGiay());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
        if (result.hasErrors()) {
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            return response;
        }

        if (chatLieuService.findByMa(cl.getMa()) != null) {
            result.rejectValue("ma", "duplicate1", "Lỗi! Mã không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (chatLieuService.findByTen(cl.getTen()) != null){
            result.rejectValue("ten", "duplicate1", "Lỗi! Tên chất liệu không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        chatLieuService.add(cl);
        response.put("status", "success");
        return response;
    }

    //
    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

    @PostMapping("/chi-tiet-san-pham/de-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") ThuongHieu degiay, BindingResult result) {
        UUID idSP = service.getOneToAddModal(id);
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());

        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }

        if (deGiayRepo.findByMa(degiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (deGiayRepo.findByTen(degiay.getTen()) != null) {
            result.rejectValue("ten", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        deGiayRepo.add(degiay);
        response.put("status", "success");
        return response;
    }

}
