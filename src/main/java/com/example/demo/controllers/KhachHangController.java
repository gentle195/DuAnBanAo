package com.example.demo.controllers;


import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.MailerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService KhachHangService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private MailerService mailerService;
    private KhachHang kh = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("khachHang") KhachHang khachHang, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/loc")
    public String hienThiLoc(Model model,
                             @RequestParam("gioiTinh1") String gioiTinh, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("khachHang") KhachHang khachHang, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        Page<KhachHang> page = KhachHangService.locGT(Boolean.valueOf(gioiTinh), pageable);

        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";


    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("khachHang") KhachHang khachHang) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll1(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../khachhang/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("KhachHang", new KhachHang());
        model.addAttribute("batmodalthemcoao", 0);
        model.addAttribute("contentPage", "../khachhang/khach-hang-add.jsp");
        return "home/layout";
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("khachHang") KhachHang KhachHang, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<KhachHang> page = KhachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
            return "home/layout";
        }
        if (KhachHangService.existKhachHangByEmail(KhachHang.getEmail())) {
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<KhachHang> page = KhachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungemail", "Email trùng!");
            model.addAttribute("contentPage", "../khachhang/khach-hang-add.jsp");
            return "home/layout";

        }
        if (KhachHangService.existKhachHangBySDT(KhachHang.getSoDienThoai())) {
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<KhachHang> page = KhachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungsdt", "Số điện thoại trùng!");
            model.addAttribute("contentPage", "../khachhang/khach-hang-add.jsp");
            return "home/layout";

        }
        String mk=generateRandomPassword(8);
        String mhd = "";
        Integer sl = KhachHangService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "KH0" + sl;
        } else {
            mhd = "KH" + sl;
        }
        KhachHang.setMa(mhd);
        KhachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        KhachHang.setTrangThai(0);
        KhachHang.setTaiKhoan(KhachHang.getEmail());
        KhachHang.setMatKhau(mk);
        KhachHangService.add(KhachHang);
        mailerService.queue(KhachHang.getEmail(),"Chúc mừng bạn đã đăng kí thành công!" ,"Tài khoản : " +KhachHang.getTaiKhoan() +"\n Mật khẩu: " +mk);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("khachHang", KhachHangService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailkhachhang", 0);
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll1(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("KhachHang", KhachHangService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailcoao", 0);
        model.addAttribute("contentPage", "../khachhang/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("khachHang", KhachHangService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../khachhang/khach-hang-update.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("khachHang") KhachHang KhachHang, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("contentPage", "../khachhang/khach-hang-update.jsp");
            return "home/layout";
        }

        KhachHang cl = KhachHangService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(KhachHang.getTrangThai());
        cl.setHoTen(KhachHang.getHoTen());
        cl.setGioiTinh(KhachHang.getGioiTinh());
        cl.setEmail(KhachHang.getEmail());
        cl.setNgaySinh(KhachHang.getNgaySinh());
        cl.setTaiKhoan(KhachHang.getTaiKhoan());
        cl.setMatKhau(KhachHang.getMatKhau());
        cl.setSoDienThoai(KhachHang.getSoDienThoai());
        KhachHangService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatecoao", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        KhachHang cl = KhachHangService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        KhachHangService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        KhachHang cl = KhachHangService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        KhachHangService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll1(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../khachhang/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("khachHang") KhachHang KhachHang, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<KhachHang> page = KhachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
            return "home/layout";
        } else {
            List<KhachHang> list = KhachHangService.search0(search);
            model.addAttribute("listKhachHang", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("khachHang") KhachHang KhachHang, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<KhachHang> page = KhachHangService.getAll1(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../khachhang/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<KhachHang> list = KhachHangService.search1(search);
            model.addAttribute("listKhachHang", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../khachhang/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }

    @GetMapping("/danh-sach-dia-chi/{id}")
    public String danhSachDiaChi(Model model, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @PathVariable("id") UUID id, @ModelAttribute("addDiaChi") DiaChi diaChi) {
        List<DiaChi> list = diaChiService.danhSachDiaChi(id);
        model.addAttribute("listDiaChi", list);
        model.addAttribute("batmodaldanhsachdiachi", 0);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add-dia-chi/{id}")
    public String viewAddDiaChi(Model model, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
            , @ModelAttribute("addDiaChi") DiaChi diaChi, @PathVariable("id") UUID id) {
        kh = KhachHangService.findById(id);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemdiachi", 0);
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-dia-chi")
    public String addDiaChi(Model model, @ModelAttribute("khachHang") KhachHang KhachHang
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @Valid @ModelAttribute("addDiaChi") DiaChi diaChi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            model.addAttribute("batmodalthemdiachi", 0);
            Page<KhachHang> page = KhachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = diaChiService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "DC" + sl;
        } else {
            mhd = "DC" + sl;
        }
        diaChi.setMa(mhd);
        diaChi.setKhachHang(kh);
        diaChi.setNgayTao(Date.valueOf(LocalDate.now()));
        diaChi.setTrangThai(0);
        diaChiService.add(diaChi);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = KhachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemdiachi", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm địa chỉ mới thành công");
        model.addAttribute("contentPage", "../khachhang/hien-thi.jsp");
        return "home/layout";
    }
}
