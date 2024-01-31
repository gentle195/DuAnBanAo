
package com.example.demo.controllers;

import com.example.demo.models.HoaDon;
import com.example.demo.services.HoaDonSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HoaDonController {

    @Autowired
    HoaDonSerice hoaDonSerice;

    @GetMapping ("/hoa-don/hien-thi")
    private String hoaDonHienThi(Model model){

        List<HoaDon> hoaDons = hoaDonSerice.hoaDonChoGiaoHang();
        model.addAttribute("listHoaDon", hoaDons);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return  "home/layout";

    }

}