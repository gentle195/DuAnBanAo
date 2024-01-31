package com.example.demo.services;

import com.example.demo.models.HoaDon;

import java.util.List;

public interface HoaDonSerice {


    public List<HoaDon> hoaDonAll();

    public List<HoaDon> hoaDonHuy();

    public List<HoaDon> hoaDonChoXacNhan();

    public List<HoaDon> hoaDonChoGiaoHang();

    public List<HoaDon> hoaDonDangVanChuyen();

    public List<HoaDon> hoaDonDaGiaoHang();

    public List<HoaDon> hoaDonDaThanhToan();

    public List<HoaDon> hoaDonChoThanhToan();

    public List<HoaDon> hoaDonHoanThanh();

}
