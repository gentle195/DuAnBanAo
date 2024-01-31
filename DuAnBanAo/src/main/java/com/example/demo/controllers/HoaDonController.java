
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

        List<HoaDon> hoaDonAll = hoaDonSerice.hoaDonAll();
        List<HoaDon> hoaDonHuy = hoaDonSerice.hoaDonHuy();
        List<HoaDon> hoaDonChoXacNhan = hoaDonSerice.hoaDonChoXacNhan();
        List<HoaDon> hoaDonChoGiaoHang = hoaDonSerice.hoaDonChoGiaoHang();
        List<HoaDon> hoaDonDangVanChuyen = hoaDonSerice.hoaDonDangVanChuyen();
        List<HoaDon> hoaDonDaGiaoHang = hoaDonSerice.hoaDonDaGiaoHang();
        List<HoaDon> hoaDonChoThanhToan = hoaDonSerice.hoaDonChoThanhToan();
        List<HoaDon> hoaDonDaThanhToan = hoaDonSerice.hoaDonDaThanhToan();
        List<HoaDon> hoaDonHoanThanh = hoaDonSerice.hoaDonHoanThanh();
        model.addAttribute("listHoaDonAll", hoaDonAll);
        model.addAttribute("listHoaDonHuy", hoaDonHuy);
        model.addAttribute("listHoaChoXacNhan", hoaDonChoXacNhan);
        model.addAttribute("listHoaDonChoGiaoHang", hoaDonChoGiaoHang);
        model.addAttribute("listHoaDonDangVanChuyen", hoaDonDangVanChuyen);
        model.addAttribute("listHoaDaGiaoHang", hoaDonDaGiaoHang);
        model.addAttribute("listHoaDonChoThanhToan", hoaDonChoThanhToan);
        model.addAttribute("listHoaDonDaThanhToan", hoaDonDaThanhToan);
        model.addAttribute("listHoaDonHoanThanh", hoaDonHoanThanh);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return  "home/layout";

    }

}