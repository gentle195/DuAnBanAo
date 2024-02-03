package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.CoAo;
import com.example.demo.models.HinhAnh;
import com.example.demo.models.KichCo;
import com.example.demo.models.MauSac;
import com.example.demo.models.SanPham;
import com.example.demo.models.ThuongHieu;

import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.services.ChatLieuService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.CoAoService;

import com.example.demo.services.HinhAnhService;
import com.example.demo.services.KichCoService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.SanPhamService;
import com.example.demo.services.ThuongHieuService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//@RequestMapping("chi-tiet-san-pham")
public class ChiTietSanPhamController {
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private CoAoService coAoService;
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private KichCoService kichCoService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;


    @GetMapping("chi-tiet-san-pham/hien-thi")
    private String hienThi(Model model) {
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamService.findAll();
        model.addAttribute("ctsp", chiTietSanPhamList);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @GetMapping("chi-tiet-san-pham/add")
    private String viewAdd(Model model) {
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-san-pham.jsp");
        return "home/layout";
    }

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Ngưng Hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("listCoAo")
    List<CoAo> listCoAo() {
        return coAoService.findAll();
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
    List<ThuongHieu> listLoaiGiay() {
        return thuongHieuService.findAll();
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
    public static class SearchCoAo {
        UUID idDe;
    }

    @Data
    public static class SearchKC {
        UUID idKC;
    }

    @Data
    public static class SearchThuongHieu {
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
        return sanPhamService.findAll();
    }

    @RequestMapping("/hien-thi1")

    public String hienThiSanPham(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("sanpham") ChiTietSanPham sp, @RequestParam(defaultValue = "0") int p, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }

        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchCoAo());

        return "chi-tiet-san-pham/a";
    }

    // search 2 loại giầy
    @GetMapping("/search2-loai-giay")
    public ResponseEntity<?> search2LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(chiTietSanPhamRepository.listThuongHieu());
        } else {
            return ResponseEntity.ok(chiTietSanPhamService.search2("%" + keyword + "%"));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search2-kich-co")
    public ResponseEntity<List<KichCo>> search2KichCo(@RequestParam(name = "keyword", required = false) Integer size) {
        List<KichCo> result;
        if (size != null) {
            // Xử lý khi 'size' có giá trị
            result = chiTietSanPhamRepository.search2KC(size);
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            result = kichCoService.findAll();
        }
        return ResponseEntity.ok(result);
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search2-mau-sac")
    public ResponseEntity<?> search2MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(mauSacService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.searchMS("%" + ten + "%"));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search2-de-giay")
    public ResponseEntity<?> search2DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(coAoService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.searchDG("%" + loaiDe + "%"));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search2-chat-lieu")
    public ResponseEntity<?> search2CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chatLieuService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.searchCL("%" + ten + "%"));
        }
    }


    // 12/11/2023
// search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search22-loai-giay")
    public ResponseEntity<?> search22LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(chiTietSanPhamService.listLG22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamService.search22LG("%" + keyword + "%", 1));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search22-kich-co")
    public ResponseEntity<List<KichCo>> search22KichCo(@RequestParam(name = "keyword", required = false) Integer size) {
        List<KichCo> result;
        if (size != null) {
            // Xử lý khi 'size' có giá trị
            result = chiTietSanPhamService.search22KC(size, 1);
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            result = chiTietSanPhamService.listKichCo22(1);
        }
        return ResponseEntity.ok(result);
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search22-mau-sac")
    public ResponseEntity<?> search22MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chiTietSanPhamService.listMauSac22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.search22MS("%" + ten + "%", 1));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search22-de-giay")
    public ResponseEntity<?> search22DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(chiTietSanPhamRepository.listCoAo22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.search22DG("%" + loaiDe + "%", 1));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search22-chat-lieu")
    public ResponseEntity<?> search22CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chiTietSanPhamService.listChatLieu22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepository.search22CL("%" + ten + "%", 1));
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
            qlSanPhamPage = chiTietSanPhamService.searchCTSP(searchFormSP.keyword, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamRepository.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
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
            qlSanPhamPage = chiTietSanPhamService.searchByMau(searchFormSPByMau.idMau, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");

        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
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
            qlSanPhamPage = chiTietSanPhamService.searchKichCo(searchKC.idKC, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");

        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("sortForm", new SortFormSP());
        return "home/layout";
    }

    // filer with combobox de giay
    @RequestMapping("/chi-tiet-san-pham/search-by-degiay")
    public String searchByCoAo(@ModelAttribute("searchDG") SearchCoAo searchCoAo, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchCoAo.idDe != null && !searchCoAo.idDe.equals("")) {
            qlSanPhamPage = chiTietSanPhamService.searchCoAo(searchCoAo.idDe, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");

        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "home/layout";
    }

    // Lỗi đâu Nam chịu
    @RequestMapping("/chi-tiet-san-pham/listLoaiGiay")
    public String listLoaiGiay(@ModelAttribute("lg") SearchThuongHieu searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = chiTietSanPhamService.searchThuongHieu(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
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
            qlSanPhamPage = chiTietSanPhamService.searchChatLieu(searchChatlieu.idChatLieu, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("lg", new SearchThuongHieu());

        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "home/layout";
    }

    // filer with combobox loai giay
    @RequestMapping("/chi-tiet-san-pham/search-by-loaigiay")
    public String searchByLoaiGiay(@ModelAttribute("lg") SearchThuongHieu searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = chiTietSanPhamService.searchThuongHieu(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new ChiTietSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "home/layout";
    }

    @RequestMapping("/chi-tiet-san-pham/sort")
    public String sort(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Sort sort;
        model.addAttribute("searchDG", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("lg", new SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        sort = sortFormSP.key.equals("giaBan") ? Sort.by(Sort.Direction.DESC, "giaBan") : Sort.by(Sort.Direction.DESC, "giaGoc");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<ChiTietSanPham> qlSanPhamPage = chiTietSanPhamService.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);

        System.out.println(sortFormSP.key);
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new ChiTietSanPham());
        return "/admin/index";
    }


    @RequestMapping("/chi-tiet-san-pham/update/{id}")
    public String updateKC(Model model, @Valid @ModelAttribute("sanpham") ChiTietSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            return "/admin/index";
        }


        UUID idSP = chiTietSanPhamService.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamService.findById(idSP);
        model.addAttribute("tensp", sp2.getTen());

        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        chiTietSanPhamService.addKC(ctsp);
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
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);

        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());

        model.addAttribute("act", "update");
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sp2 = sanPhamService.findById(idSP);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        return "/admin/index";
    }
//update list-spct

    @RequestMapping("/chi-tiet-san-pham/update-sp/{id}")
    public String updateSPCT(Model model, @Valid @ModelAttribute("sanpham") ChiTietSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            return "/admin/index";
        }

        UUID idSP = chiTietSanPhamService.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamService.findById(idSP);
        model.addAttribute("tensp", sp2.getTen());
        model.addAttribute("searchForm", new SearchFormSP());

        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        chiTietSanPhamService.addKC(ctsp);
        //generate code qr
        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/list-san-pham/" + idSP);
        model.addAttribute("view", "../chi-tiet-san-pham/list-spct.jsp");
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + idSP;
    }

    @RequestMapping("/chi-tiet-san-pham/view-update-ctsp/{id}")
    public String viewUpdateCTSP(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);
        model.addAttribute("act", "update");
        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());


        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sp2 = sanPhamService.findById(idSP);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update-sp/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        return "/admin/index";
    }

    //    // modal


    @RequestMapping("/chi-tiet-san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") ThuongHieu loaiGiay, BindingResult result) {
        Boolean hasE = result.hasErrors();
        Map<String, Object> response = new HashMap<>();
        List<ThuongHieu> list = thuongHieuService.findAll();
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamService.findById(idSP);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }

        if (thuongHieuService.findByMa(loaiGiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (thuongHieuService.findByTen(loaiGiay.getTen()) != null) {
            result.rejectValue("tentheloai", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "tentheloai");
            return response;
        }

        thuongHieuService.add(loaiGiay);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/chi-tiet-san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamService.findById(idSP);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            return response;
        }

        if (kichCoService.findByMa(kichCo.getMa()) != null) {
            result.rejectValue("maKichCo", "duplicate3", "Lỗi! Mã không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "maKichCo");
            return response;
        }
        if (kichCoService.findByTen(kichCo.getTen()) != null) {
            result.rejectValue("size", "duplicate3", "Lỗi! Size không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "size");
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
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamService.findById(idSP);
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
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng");
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
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamService.findById(idSP);
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
        if (chatLieuService.findByTen(cl.getTen()) != null) {
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
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") CoAo degiay, BindingResult result) {
        UUID idSP = chiTietSanPhamService.getOneToAddModal(id);
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham2 = sanPhamService.findById(idSP);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("vm", new ChatLieu());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
//        if (result.hasErrors()) {
//            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
//            return "redirect:/chi-tiet-san-pham/view-update/" + id;
////            return "/admin/index";
//        }
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }

        if (coAoService.findByMa(degiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (coAoService.findByTen(degiay.getTen()) != null) {
            result.rejectValue("loaiDe", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "loaiDe"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        coAoService.add(degiay);
        response.put("status", "success");
        return response;
//        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
//        return "redirect:/chi-tiet-san-pham/view-update/" + id;
//        return "/admin/index";
    }

    // hình ảnh
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @GetMapping("/chi-tiet-san-pham/hinh-anh-sp/view-add/{id}")
    public String viewAddHinhAnh(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh, @PathVariable("id") UUID id,
                                 @RequestParam UUID idSP,
                                 @RequestParam UUID idMS
    ) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        model.addAttribute("idctsp", id);

        UUID idHinhANh = hinhAnhRepository.getIdHinhAnh(id);
//        SanPham sp = hinhAnhService.getSanPhamByIDCTSP(id);
        if (idHinhANh != null) {
//            HinhAnh hinhAnh2 = hinhAnhService.getHinhAnh(id);
//            model.addAttribute("listHinhAnh", hinhAnh2);

            model.addAttribute("action4", "/hinh-anh-ctsp/update/" + ctsp.getId() + "?idSP=" + idSP + "&idMS=" + idMS);
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";


        } else {
            // Các xử lý khác nếu không tìm thấy idHinhAnh
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("action4", "/chi-tiet-san-pham/hinh-anh-sp/add/" + ctsp.getId() + "?idSP=" + idSP + "&idMS=" + idMS);
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";
        }
    }


    @PostMapping("/chi-tiet-san-pham/hinh-anh/add/{id}")
    public String save(Model model,
                       @RequestParam(name = "tenanh") MultipartFile tenanh,
                       @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                       @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                       @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                       @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                       @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                       @PathVariable UUID id,
                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp
    ) {
        HinhAnh hinhAnh = new HinhAnh();
//        hinhAnh.setCtsp(ctsp);
        try {
            String uploadPath = hinhAnhService.getImageUploadPath();
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3};
            for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile file = imageFiles[i];
                if (file != null && !file.isEmpty()) {
                    String fileName = file.getOriginalFilename().toLowerCase();
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    switch (i) {
                        case 0:
                            hinhAnh.setTenAnh(fileName);
                            break;
                        case 1:
                            hinhAnh.setDuongDan1(fileName);
                            break;
                        case 2:
                            hinhAnh.setDuongDan2(fileName);
                            break;
                        case 3:
                            hinhAnh.setDuongDan3(fileName);
                            break;
                        case 4:
                            hinhAnh.setDuongDan4(fileName);
                            break;
                        case 5:
                            hinhAnh.setDuongDan5(fileName);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";
        }
        hinhAnhRepository.save(hinhAnh);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

//    @PostMapping("/chi-tiet-san-pham/hinh-anh-sp/add/{id}")
//    public String saveHinhAnhCTSP(Model model,
//                                  @RequestParam(name = "tenanh") MultipartFile tenanh,
//                                  @RequestParam(name = "duongdan1") MultipartFile duongdan1,
//                                  @RequestParam(name = "duongdan2") MultipartFile duongdan2,
//                                  @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                                  @RequestParam UUID idSP,
//                                  @RequestParam UUID idMS,
//                                  @PathVariable UUID id,
//                                  @RequestParam(name = "ctsp") ChiTietSanPham ctsp
//    ) {
//        List<ChiTietSanPham> listCTSPBYSPAndMS = chiTietSanPhamRepository.getCTSPBYSPAndMauSac(idMS, idSP);
////        SanPham sp = hinhAnhService.getSanPhamByIDCTSP(id);
//        for (ChiTietSanPham chiTietSanPham : listCTSPBYSPAndMS) {
//            HinhAnh hinhAnh = new HinhAnh();
//            try {
//                String uploadPath = hinhAnhService.getImageUploadPath();
//                Path uploadDir = Paths.get(uploadPath);
//                if (!Files.exists(uploadDir)) {
//                    Files.createDirectories(uploadDir);
//                }
//                MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3};
//                for (int i = 0; i < imageFiles.length; i++) {
//                    MultipartFile file = imageFiles[i];
//                    if (file != null && !file.isEmpty()) {
//                        String fileName = file.getOriginalFilename().toLowerCase(); // Sử dụng tên tệp tin làm đường dẫn
//                        Path filePath = uploadDir.resolve(fileName);
//                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//                        switch (i) {
//                            case 0:
//                                hinhAnh.setTenAnh(fileName);
//                                break;
//                            case 1:
//                                hinhAnh.setDuongDan1(fileName);
//                                break;
//                            case 2:
//                                hinhAnh.setDuongDan2(fileName);
//                                break;
//                            case 3:
//                                hinhAnh.setDuongDan3(fileName);
//                                break;
//                            case 4:
//                                hinhAnh.setDuongDan4(fileName);
//                                break;
//                            case 5:
//                                hinhAnh.setDuongDan5(fileName);
//                                break;
//                            default:
//                                break;
//                        }
//
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            hinhAnh.setCtsp(chiTietSanPham);
//            hinhAnhRepository.save(hinhAnh);
//        }
//
//
//        return "redirect:/chi-tiet-san-pham/list-san-pham/" + sp.getId();
//    }


//    @PostMapping("/chi-tiet-san-pham/upload")
//    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
//        try {
//            if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
//                model.addAttribute("error", "Vui lòng chọn một tệp Excel (.xls hoặc .xlsx).");
//                return "redirect:/chi-tiet-san-pham/hien-thi";
//            }
//
//            // Tiếp tục xử lý nếu là tệp Excel hợp lệ
//            excelService.saveDataFromExcel(file);
//            model.addAttribute("success", "Dữ liệu đã được chèn thành công.");
//            return "redirect:/chi-tiet-san-pham/hien-thi";
//        } catch (IOException e) {
//            model.addAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
//            // Ghi log hoặc xử lý ngoại lệ tại đây
//            return "forward:/chi-tiet-san-pham/hien-thi";
//        }
//    }


}
