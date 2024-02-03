package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.CoAo;
import com.example.demo.models.KichCo;
import com.example.demo.models.MauSac;
import com.example.demo.models.SanPham;
import com.example.demo.models.ThuongHieu;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.CoAoRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.ChatLieuService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.KichCoService;
import com.example.demo.services.SanPhamService;
import com.example.demo.services.ThuongHieuService;
import com.example.demo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class SanPhamController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    CoAoRepository deGiayRepo;

    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    ThuongHieuService loaiGiayRepo;
    @Autowired
    MauSacRepository mauSacReponsitories;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    SanPhamService sanPhamService;


    @ModelAttribute("listDeGiay")
    List<CoAo> listDeGiay() {
        return deGiayRepo.findAll();
    }

    @ModelAttribute("listChatLieu")
    List<ChatLieu> listChatLieu() {
        return chatLieuRepo.findAll();
    }

    @ModelAttribute("listKichCo")
    List<KichCo> listKichCo() {
        return kichCoService.findAll();
    }

    @ModelAttribute("listMau")
    List<MauSac> listMauSac() {
        return mauSacReponsitories.findAll();
    }

    @ModelAttribute("listLoaiGiay")
    List<ThuongHieu> listLoaiGiay() {
        return loaiGiayRepo.findAll();
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Ngừng hoạt động");
        return dsTrangThai;
    }

    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }

    @GetMapping("/san-pham/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0") int p) {
        model.addAttribute("view", "../san_pham/list_san_pham.jsp");
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        Page<SanPham> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("search", new SearchForm());
        model.addAttribute("page", page);
        model.addAttribute("contentPage", "../san_pham/list_san_pham.jsp");
        return "home/layout";
    }


    @RequestMapping("/san-pham/search")
    public String search(Model model, @ModelAttribute("search") SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        model.addAttribute("view", "../san_pham/list_san_pham.jsp");
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        Page<SanPham> page = sanPhamService.findByKeyword(searchForm.keyword, pageable);
        model.addAttribute("page", page);
//        model.addAttribute("view", "../san_pham/index.jsp");
        return "admin/index";
    }

    @GetMapping("/san-pham/view-add")
    public String viewAdd(Model model, @ModelAttribute("SP") SanPham sanPham) {
        model.addAttribute("action", "/san-pham/add");
        model.addAttribute("view", "../san_pham/view_add_update.jsp");
        model.addAttribute("contentPage", "../san_pham/add-san-pham.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("SP") SanPham sanPham) {
        model.addAttribute("view", "../san_pham/view_add_update.jsp");
        SanPham product = sanPhamService.getOne(id);
        model.addAttribute("action", "/san-pham/update/" + product.getId());
        model.addAttribute("contentPage", "../san_pham/add-san-pham.jsp");
        model.addAttribute("SP", product);
        SanPham sp = sanPhamService.getOne(id);
        return "home/layout";
    }

    @PostMapping("/san-pham/add")
    public String add(Model model, @Valid @ModelAttribute("SP") SanPham sanPham, BindingResult result) {
        Boolean hasError = result.hasErrors();
        SanPham product = sanPhamService.getByMa(sanPham.getMa());
        if (product != null) {
            hasError = true;
            model.addAttribute("maspError", "Vui lòng không nhập trùng mã");
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        if (hasError) {
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        sanPhamService.addSanPham(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @PostMapping("/san-pham/update/{id}")
    public String udpate(@PathVariable("id") UUID id, Model model, @Valid @ModelAttribute("SP") SanPham sanPham,
                         BindingResult result) {
        Boolean hasError = result.hasErrors();
        if (sanPham.getMa().trim().length() < 5) {
            hasError = true;
            model.addAttribute("erorLenghMa", "Mã sản phẩm phải lớn hơn hoặc bằng 5");
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        if (hasError) {
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        SanPham sp = sanPhamService.getOne(id);
        sp.setMa(sanPham.getMa());
        sp.setTen(sanPham.getTen());
        sp.setTrangThai(sanPham.getTrangThai());
        sanPhamService.udpateSanPham(sp);

        List<ChiTietSanPham> listCTSPByIDSP = chiTietSanPhamService.listCTSPByIDSP(id);
        for (ChiTietSanPham ctsp: listCTSPByIDSP) {
            ctsp.setTrangThai(sanPham.getTrangThai());
            chiTietSanPhamService.addKC(ctsp);
        }
        return "redirect:/san-pham/hien-thi";
    }

    @RequestMapping("/chi-tiet-san-pham/view-add/{id}")
    public String viewAdd(Model model, @ModelAttribute("sanpham") ChiTietSanPham sp, @PathVariable("id") UUID id) {
        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("action2", "/san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/san-pham/chat-lieu/add/" + id);
        model.addAttribute("act", "add");
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("tensp", sanPham1.getTen());
        model.addAttribute("action", "/chi-tiet-san-pham/add/" + sanPham1.getId());
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }

    // add ctsp
    @PostMapping("/chi-tiet-san-pham/add/{id}")
    public String AddSanPham(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("sanpham") ChiTietSanPham sp
            ,
                             BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value = "kichCo", required = false) List<String> kichCoList,
                             @RequestParam(value = "mauSac", required = false) List<String> mauSacList,
                             @RequestParam(name = "soLuong", required = false) List<String> listSoLuong) throws WriterException, IOException {
        model.addAttribute("lg", new ThuongHieu());
        model.addAttribute("degiay", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "add");
        if (result.hasErrors()) {
            model.addAttribute("submitStatus", "error");
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            return "/admin/index";
        }
        SanPham sanPham1 = sanPhamService.getOne(id);
        sp.setSanPham(sanPham1);
//        sp.setNgayTao(Calendar.getInstance().getTime());
        ChiTietSanPham ctsp = new ChiTietSanPham();
        if (kichCoList != null && mauSacList != null && listSoLuong != null) {
            for (int i = 0; i < kichCoList.size(); i++) {
                if (i >= kichCoList.size()) {
                    System.out.println("Chỉ số vượt quá kích thước của kichCoList");
                    // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                    continue;
                }
                String kichCoID =kichCoList.get(i);
                for (int j = 0; j < mauSacList.size(); j++) {
                    if (j >= mauSacList.size()) {
                        System.out.println("Chỉ số vượt quá kích thước của mauSacList");
                        // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                        continue;
                    }
                    String mauSacID = mauSacList.get(j);
                    //
                    int index = i * mauSacList.size() + j;
                    if (index >= listSoLuong.size()) {
                        System.out.println("Chỉ số vượt quá kích thước của listSoLuong");
                        // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                        continue;
                    }
                    //
                    String soLuong = listSoLuong.get(index);
                    sp.setSoLuongTon(Integer.valueOf(soLuong));
                    sp.setKichCo(kichCoService.findById(UUID.fromString(kichCoID)));
                    sp.setMauSac(mauSacReponsitories.getOne(UUID.fromString(mauSacID)));
                    if (chiTietSanPhamService.isChiTietSanPhamExists(sp)) {
                        ChiTietSanPham ctspExit = chiTietSanPhamService.findFirstBySanPhamAndChatLieuAndThuongHieuAndMauSacAndDeGiayAndKichCo(sp);
//
                        ctspExit.setSoLuongTon(Integer.valueOf(soLuong) + ctspExit.getSoLuongTon());
                        sp.setGiaBan(ctspExit.getGiaBan());
                        chiTietSanPhamService.addKC(ctspExit);

                    } else {
                        chiTietSanPhamService.addKC(sp);
                    }
                }
            }
        }

        model.addAttribute("tensp", sanPham1.getTen());

        //generate code qr

        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);
        redirectAttributes.addFlashAttribute("redirectUrl", "/chi-tiet-san-pham/list-san-pham/" + id);
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + id;
    }


    // New method to handle AJAX requests
//    @PostMapping("/chi-tiet-san-pham/ajax/add/{id}")
//    @ResponseBody
//    public ResponseEntity<String> addSanPhamAjax(Model model,@PathVariable("id") UUID id, @Valid @ModelAttribute("sanpham") QLSanPham sp, BindingResult result) {
//        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body("Validation failed");
//        }
//        SanPham sanPham1 = sanPhamService.getOne(id);
//        model.addAttribute("idsp", id);
//        // Process the data and generate QR code if needed
//
//        return ResponseEntity.ok("Product added successfully");
//    }

    //add modal loai giay
    @RequestMapping("/san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") ThuongHieu loaiGiay, BindingResult result) {
        Boolean hasE = result.hasErrors();
        List<ThuongHieu> list = loaiGiayRepo.findAll();
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }

        if (loaiGiayRepo.findByMa(loaiGiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (loaiGiayRepo.findByTen(loaiGiay.getTen()) != null) {
            result.rejectValue("ten", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        loaiGiayRepo.add(loaiGiay);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());

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

    @PostMapping("/san-pham/mau-sac/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("ms") MauSac ms, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        if (result.hasErrors()) {
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            return response;
        }

        if (mauSacReponsitories.findMauSacByMa(ms.getMa()) != null) {
            result.rejectValue("ma", "duplicate2", "Lỗi! Mã không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (mauSacReponsitories.findMauSacByTen(ms.getTen()) != null) {
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        mauSacReponsitories.save(ms);
        response.put("status", "success");
        return response;
    }



    @RequestMapping("/san-pham/chat-lieu/add/{id}")
    @ResponseBody
    public Map<String, Object> store(Model model, @PathVariable("id") UUID id,
                                     @Valid @ModelAttribute("vm") ChatLieu cl,
                                     BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("degiay", new DeGiay());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
//        Boolean hasError = result.hasErrors();
        ChatLieu chatLieu = chatLieuService.findByMa(cl.getMa());

        if (result.hasErrors()) {
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            return response;
        }

        if (chatLieuRepo.findChatLieuByMa(cl.getMa()) != null) {
            result.rejectValue("ma", "duplicate1", "Lỗi! Mã không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (chatLieuRepo.findChatLieuByTen(cl.getTen()) != null) {
            result.rejectValue("ten", "duplicate1", "Lỗi! Tên chất liệu không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        chatLieuRepo.save(cl);
        response.put("status", "success");
        return response;
    }


    @PostMapping("/san-pham/de-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> add(@PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") CoAo degiay, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham = sanPhamService.getOne(id);

        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }

        if (deGiayRepo.findCoAoByMa(degiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (deGiayRepo.findCoAoByTen(degiay.getTen()) != null) {
            result.rejectValue("loaiDe", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "loaiDe");
            return response;
        }

        deGiayRepo.save(degiay);
        response.put("status", "success");
        return response;
    }

    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

    //list ctsp theo id
    @RequestMapping("/chi-tiet-san-pham/list-san-pham/{id}")

    public String hienListSanPham(@ModelAttribute("sortForm") ChiTietSanPhamController.SortFormSP sortFormSP, @ModelAttribute("sanpham") ChiTietSanPham sp, @RequestParam(defaultValue = "0") int p, @PathVariable("id") UUID id, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = chiTietSanPhamService.listCTSP(id, pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new ChiTietSanPhamController.SearchChatlieu());
        model.addAttribute("lg", new ChiTietSanPhamController.SearchThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("view", "../chi-tiet-san-pham/list-spct.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list-spct.jsp");
        model.addAttribute("searchForm", new ChiTietSanPhamController.SearchFormSP());
        model.addAttribute("searchFormByMau", new ChiTietSanPhamController.SearchFormSPByMau());
        model.addAttribute("searchKC", new ChiTietSanPhamController.SearchKC());
        model.addAttribute("searchDG", new ChiTietSanPhamController.SearchCoAo());

        return "home/layout";
    }
}
