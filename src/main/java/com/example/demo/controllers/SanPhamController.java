package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.services.*;
import com.example.demo.services.SanPhamService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private CoAoService coAoService;
    @Autowired
    private KichCoService kichCoService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private HinhAnhService hinhAnhService;

    private UUID idSanPham = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("sanPham") SanPham sanPham) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("sanPham") SanPham sanPham) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll1(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("batmodalthemsanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/view-ctsp/{id}")
    public String viewCTSP(Model model, @PathVariable("id") UUID id, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        model.addAttribute("sanPham", sanPhamService.findById(id));
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(id)));
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        idSanPham = id;
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<SanPham> page = sanPhamService.getAll(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemsanpham", 0);
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = sanPhamService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "SP0" + sl;
        } else {
            mhd = "SP" + sl;
        }
        sanPham.setMa(mhd);
        sanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        sanPham.setTrangThai(0);
        sanPhamService.add(sanPham);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemsanpham", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("sanPham", sanPhamService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailsanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll1(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("sanPham", sanPhamService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailsanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("sanPham", sanPhamService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatesanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<SanPham> page = sanPhamService.getAll(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatesanpham", 0);
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
        SanPham cl = sanPhamService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(sanPham.getTrangThai());
        cl.setTen(sanPham.getTen());
        sanPhamService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatesanpham", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        SanPham cl = sanPhamService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        sanPhamService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        SanPham cl = sanPhamService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        sanPhamService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamService.getAll1(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("sanPham") SanPham sanPham, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<SanPham> page = sanPhamService.getAll(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        } else {
            List<SanPham> list = sanPhamService.search0(search);
            model.addAttribute("listSanPham", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("sanPham") SanPham sanPham, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<SanPham> page = sanPhamService.getAll1(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<SanPham> list = sanPhamService.search1(search);
            model.addAttribute("listSanPham", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }

    @GetMapping("/them-chi-tiet-san-pham")
    public String them(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                       @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                       @ModelAttribute("kichCo") KichCo kichCo,
                       @ModelAttribute("mauSac") MauSac mauSac,
                       @ModelAttribute("coAo") CoAo coAo,
                       @ModelAttribute("chatLieu") ChatLieu chatLieu
    ) {
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/chat-lieu/add")
    public String addCL(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                        @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                        @ModelAttribute("kichCo") KichCo kichCo,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("coAo") CoAo coAo,
                        @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        String mhd = "";
        Integer sl = chatLieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CL0" + sl;
        } else {
            mhd = "CL" + sl;
        }
        chatLieu.setMa(mhd);
        chatLieu.setNgayTao(Date.valueOf(LocalDate.now()));
        chatLieu.setTrangThai(0);
        chatLieuService.add(chatLieu);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/thuong-hieu/add")
    public String addTH(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                        @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                        @ModelAttribute("kichCo") KichCo kichCo,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("coAo") CoAo coAo,
                        @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        String mhd = "";
        Integer sl = thuongHieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "TH0" + sl;
        } else {
            mhd = "TH" + sl;
        }
        thuongHieu.setMa(mhd);
        thuongHieu.setNgayTao(Date.valueOf(LocalDate.now()));
        thuongHieu.setTrangThai(0);
        thuongHieuService.add(thuongHieu);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/mau-sac/add")
    public String addMS(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                        @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                        @ModelAttribute("kichCo") KichCo kichCo,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("coAo") CoAo coAo,
                        @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        String mhd = "";
        Integer sl = mauSacService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "MS0" + sl;
        } else {
            mhd = "MS" + sl;
        }
        mauSac.setMa(mhd);
        mauSac.setNgayTao(Date.valueOf(LocalDate.now()));
        mauSac.setTrangThai(0);
        mauSacService.add(mauSac);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/kich-co/add")
    public String addKC(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                        @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                        @ModelAttribute("kichCo") KichCo kichCo,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("coAo") CoAo coAo,
                        @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        String mhd = "";
        Integer sl = kichCoService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "KC0" + sl;
        } else {
            mhd = "KC" + sl;
        }
        kichCo.setMa(mhd);
        kichCo.setNgayTao(Date.valueOf(LocalDate.now()));
        kichCo.setTrangThai(0);
        kichCoService.add(kichCo);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/co-ao/add")
    public String addCA(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham,
                        @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                        @ModelAttribute("kichCo") KichCo kichCo,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("coAo") CoAo coAo,
                        @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        String mhd = "";
        Integer sl = coAoService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CA0" + sl;
        } else {
            mhd = "CA" + sl;
        }
        coAo.setMa(mhd);
        coAo.setNgayTao(Date.valueOf(LocalDate.now()));
        coAo.setTrangThai(0);
        coAoService.add(coAo);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/add")
    public String AddCTSP(Model model, @Valid @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, BindingResult bindingResult,
                          @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                          @ModelAttribute("kichCo") KichCo kichCo,
                          @ModelAttribute("mauSac") MauSac mauSac,
                          @ModelAttribute("coAo") CoAo coAo,
                          @ModelAttribute("chatLieu") ChatLieu chatLieu,
                          @RequestParam("anh1s") MultipartFile anh1,
                          @RequestParam("anh2s") MultipartFile anh2,
                          @RequestParam("anh3s") MultipartFile anh3,
                          @RequestParam("anh4s") MultipartFile anh4
    ) throws IOException, WriterException {
        if (bindingResult.hasErrors() || chiTietSanPham.getChatLieu() == null) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("errorChatLieu", "Không để trống chất liệu");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || chiTietSanPham.getMauSac() == null) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("errorMauSac", "Không để trống màu sắc");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || chiTietSanPham.getCoAo() == null) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("errorCoAo", "Không để trống cổ áo");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || chiTietSanPham.getThuongHieu() == null) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("errorThuongHieu", "Không để trống thương hiệu");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || chiTietSanPham.getKichCo() == null) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("errorKichThuoc", "Không để trống kích cỡ");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }
        if (sanPhamService.existsByChatLieuAndCoAoAndKichCoAndMauSacAndThuongHieu(chiTietSanPham.getChatLieu(),
                chiTietSanPham.getCoAo(), chiTietSanPham.getKichCo(), chiTietSanPham.getMauSac(), chiTietSanPham.getThuongHieu())) {
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("thuongHieu", new ThuongHieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("coAo", new CoAo());
            model.addAttribute("kichCo", new KichCo());
            model.addAttribute("thongBao", "Chi tiết sản phẩm đã tồn tại");
            model.addAttribute("listChatLieu", chatLieuService.findAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            model.addAttribute("listCoAo", coAoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("contentPage", "../san-pham/view-add.jsp");
            return "home/layout";
        }

        String mhd = "";
        Integer sl = sanPhamService.findAllCTSPFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CTSP0" + sl;
        } else {
            mhd = "CTSP" + sl;
        }
        String mha = "";
        Integer sla = sanPhamService.findAllCTSPFullTT().size() + 1;
        if (sla < 9) {
            mha = "HA0" + sl;
        } else {
            mha = "HA" + sl;
        }
        HinhAnh hinhAnh = new HinhAnh();
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());

        if (fileName1.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            hinhAnh.setDuongDan1(fileName1);
        }

        // Xử lý ảnh 2
        String fileName2 = StringUtils.cleanPath(anh2.getOriginalFilename());

        if (fileName2.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName2, anh2);
            hinhAnh.setDuongDan2(fileName2);
        }

        // Xử lý ảnh 3
        String fileName3 = StringUtils.cleanPath(anh3.getOriginalFilename());

        if (fileName3.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName3, anh3);
            hinhAnh.setDuongDan3(fileName3);
        }
        // Xử lý ảnh 4
        String fileName4 = StringUtils.cleanPath(anh4.getOriginalFilename());

        if (fileName4.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName4, anh4);
            hinhAnh.setDuongDan4(fileName4);
        }
        String tenAnh = mha + mhd;
        hinhAnh.setTenAnh(tenAnh);
        hinhAnh.setNgayTao(Date.valueOf(LocalDate.now()));
        hinhAnh.setTrangThai(0);
        hinhAnhService.add(hinhAnh);
        HinhAnh search = hinhAnhService.search(tenAnh);
        chiTietSanPham.setHinhAnh(search);
        chiTietSanPham.setMa(mhd);
        chiTietSanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        chiTietSanPham.setSanPham(sanPhamService.findById(idSanPham));
        chiTietSanPham.setTrangThai(0);
        String projectRootPath = System.getProperty("user.dir");
        String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
        QRCodeGenerator.generatorQRCode(chiTietSanPham, outputFolderPath);
        chiTietSanPham.setMaQR(chiTietSanPham.getMa() + ".png");
        sanPhamService.addCTSP(chiTietSanPham);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chiTiet", new ChiTietSanPham());
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("contentPage", "../san-pham/view-add.jsp");
        return "home/layout";

    }

    @GetMapping("/chi-tiet-san-pham/detail/{id}")
    public String detailCTSP(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @PathVariable("id") UUID id) {

        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chiTiet", sanPhamService.findCTSPById(id));
        model.addAttribute("tenSP", sanPhamService.findCTSPById(id).getSanPham().getTen());
        model.addAttribute("tenTH", sanPhamService.findCTSPById(id).getThuongHieu().getTen());
        model.addAttribute("tenCL", sanPhamService.findCTSPById(id).getChatLieu().getTen());
        model.addAttribute("tenMS", sanPhamService.findCTSPById(id).getMauSac().getTen());
        model.addAttribute("tenCA", sanPhamService.findCTSPById(id).getCoAo().getTen());
        model.addAttribute("tenKC", sanPhamService.findCTSPById(id).getKichCo().getTen());
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodaldetailchitiet", 0);
        model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(idSanPham)));
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        return "home/layout";
    }

    @GetMapping("/chi-tiet-san-pham/view-update/{id}")
    public String viewUpdateCTSP(Model model, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, @PathVariable("id") UUID id) {

        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chiTiet", sanPhamService.findCTSPById(id));
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("batmodalupdatechitiet", 0);
        model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(idSanPham)));
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/update/{id}")
    public String updateCTSP(Model model, @Valid @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham, BindingResult bindingResult,
                             @PathVariable("id") UUID id,
                             @RequestParam("checkanh1") String checkanh1,
                             @RequestParam("checkanh2") String checkanh2,
                             @RequestParam("checkanh3") String checkanh3,
                             @RequestParam("checkanh4") String checkanh4,
                             @RequestParam("anh1s") MultipartFile anh1,
                             @RequestParam("anh2s") MultipartFile anh2,
                             @RequestParam("anh3s") MultipartFile anh3,
                             @RequestParam("anh4s") MultipartFile anh4
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("batmodalupdatechitiet", 0);
            model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
            model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(idSanPham)));
            model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
            return "home/layout";
        }
        HinhAnh hinhAnh = hinhAnhService.searchId(chiTietSanPham.getHinhAnh().getId());
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());
        if (checkanh1.equals("cu1")) {
        } else {
            if (fileName1.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            }
            hinhAnh.setDuongDan1(fileName1);
        }
        String fileName2 = StringUtils.cleanPath(anh2.getOriginalFilename());
        if (checkanh2.equals("cu2")) {
        } else {
            if (fileName2.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName2, anh2);
            }
            hinhAnh.setDuongDan2(fileName2);
        }
        String fileName3 = StringUtils.cleanPath(anh3.getOriginalFilename());
        if (checkanh3.equals("cu3")) {
        } else {
            if (fileName3.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName3, anh3);
            }
            hinhAnh.setDuongDan3(fileName3);
        }
        String fileName4 = StringUtils.cleanPath(anh4.getOriginalFilename());
        if (checkanh4.equals("cu4")) {
        } else {
            if (fileName4.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName4, anh4);
            }
            hinhAnh.setDuongDan4(fileName4);
        }
        hinhAnh.setNgaySua(Date.valueOf(LocalDate.now()));
        hinhAnhService.update(hinhAnh.getId(), hinhAnh);
        chiTietSanPham.setHinhAnh(hinhAnh);
        chiTietSanPham.setNgaySua(Date.valueOf(LocalDate.now()));
        chiTietSanPham.setTrangThai(0);
        sanPhamService.updateCTSP(id, chiTietSanPham);
//        chiTietSanPham.setNgaySua();
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("chiTiet", sanPhamService.findCTSPById(id));
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(idSanPham)));
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        model.addAttribute("thongBaoThanhCong", "Cập nhật thông tin thành công");
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/chi-tiet-san-pham/show-qr/{id}")
    public List<ChiTietSanPham> showQR(Model model, @PathVariable("id") UUID id) {
        List<ChiTietSanPham> list = sanPhamService.showQR(id);
        System.out.println(1);
        return list;
    }

    @GetMapping("/chi-tiet-san-pham/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham cl = sanPhamService.findCTSPById(id);
        cl.setTrangThai(2);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        sanPhamService.updateCTSP(id, cl);
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listCTSP", sanPhamService.findChiTietSanPhamBySanPham(sanPhamService.findById(idSanPham)));
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        model.addAttribute("thongBaoThanhCong", "Cập nhật thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/loc")
    public String loc(Model model, @RequestParam(value = "chatLieu", required = false) UUID chatLieuu,
                      @RequestParam(value = "mauSac", required = false) UUID mauSacc,
                      @RequestParam(value = "thuongHieu", required = false) UUID thuongHieuu,
                      @RequestParam(value = "kichCo", required = false) UUID kichCoo,
                      @RequestParam(value = "coAo", required = false) UUID coAoo,
                      @ModelAttribute("chiTiet") ChiTietSanPham chiTietSanPham
    ) {
        List<ChiTietSanPham> list = sanPhamService.loc(idSanPham, chatLieuu, coAoo, kichCoo, mauSacc, thuongHieuu);
        model.addAttribute("listCTSP",list);
        model.addAttribute("thongBaoThanhCong","Lọc dữ liệu thành công");
        model.addAttribute("sanPham", sanPhamService.findById(idSanPham));
        model.addAttribute("listChatLieu", chatLieuService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listCoAo", coAoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        model.addAttribute("thongBaoThanhCong", "Cập nhật thông tin thành công");
        return "home/layout";
    }
}
