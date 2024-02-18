
package com.example.demo.controllers;

import com.example.demo.repositories.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

//    @Autowired
//    DataIntermediateService dataIntermediateService;

    @GetMapping("/home")
    private String showHome(Model model){
//        model.addAttribute("nhanVien", dataIntermediateService.getSharedDataNhanVien());
        return  "home/layout";
//                "chip/basic-table";
    }
//
//    @GetMapping("/homes")
//    private String showHomes(Model model){
//        model.addAttribute("ctsp",chiTietSanPhamRepository.findAll());
//        model.addAttribute("contentPage","../chi-tiet-san-pham/index.jsp");
//        return  "home/layout";
////                "chip/basic-table";
    }
//
//}