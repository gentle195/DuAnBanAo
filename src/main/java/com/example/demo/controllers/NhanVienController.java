package com.example.demo.controllers;


import com.example.demo.models.ChucVu;
import com.example.demo.models.NhanVien;
import com.example.demo.services.ChucVuService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("contentPage", "../nhan-vien/view-add.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, @ModelAttribute("chucVu") ChucVu chucVu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/view-add.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = nhanVienService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "NV0" + sl;
        } else {
            mhd = "NV" + sl;
        }
        nhanVien.setMa(mhd);
        nhanVien.setNgayTao(Date.valueOf(LocalDate.now()));
        nhanVien.setTinhTrang(0);
        nhanVienService.add(nhanVien);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemnhanvien", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-chuc-vu")
    public String addChucVu(Model model,@ModelAttribute("chucVu") ChucVu chucVu, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        String mhd = "";
        Integer sl = chucVuService.findAll().size() + 1;
        if (sl < 9) {
            mhd = "CV0" + sl;
        } else {
            mhd = "CV" + sl;
        }
        chucVu.setMa(mhd);
        chucVu.setNgayTao(Date.valueOf(LocalDate.now()));
        chucVu.setTinhTrang(0);
        chucVuService.add(chucVu);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../nhan-vien/view-add.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailnhanvien", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailnhanvien", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/view-update.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("contentPage", "../nhan-vien/view-update.jsp");
            return "home/layout";
        }
        NhanVien cl = nhanVienService.findById(id);
        nhanVien.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVien.setTinhTrang(cl.getTinhTrang());
        nhanVien.setMa(cl.getMa());
        nhanVien.setNgayTao(cl.getNgayTao());
        nhanVienService.update(id, nhanVien);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatenhanvien", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        NhanVien cl = nhanVienService.findById(id);
        cl.setTinhTrang(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        NhanVien cl = nhanVienService.findById(id);
        cl.setTinhTrang(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        } else {
            List<NhanVien> list = nhanVienService.search0(search);
            model.addAttribute("listNhanVien", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<NhanVien> page = nhanVienService.getAll1(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<NhanVien> list = nhanVienService.search1(search);
            model.addAttribute("listNhanVien", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
