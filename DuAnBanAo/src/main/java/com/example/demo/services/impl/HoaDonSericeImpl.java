package com.example.demo.services.impl;

import com.example.demo.models.HoaDon;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.services.HoaDonSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonSericeImpl implements HoaDonSerice {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> hoaDonAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> hoaDonHuy() {
        return hoaDonRepository.hoaDonHuy();
    }

    @Override
    public List<HoaDon> hoaDonChoXacNhan() {
        return hoaDonRepository.hoaDonChoXacNhan();
    }

    @Override
    public List<HoaDon> hoaDonChoGiaoHang() {
        return hoaDonRepository.hoaDonChoGiaoHang();
    }

    @Override
    public List<HoaDon> hoaDonDangVanChuyen() {
        return hoaDonRepository.hoaDonDangVanChuyen();
    }

    @Override
    public List<HoaDon> hoaDonDaGiaoHang() {
        return hoaDonRepository.hoaDonDaGiaoHang();
    }

    @Override
    public List<HoaDon> hoaDonDaThanhToan() {
        return hoaDonRepository.hoaDonChoThanhToan();
    }

    @Override
    public List<HoaDon> hoaDonChoThanhToan() {
        return hoaDonRepository.hoaDonDaThanhToan();
    }

    @Override
    public List<HoaDon> hoaDonHoanThanh() {
        return hoaDonRepository.hoaDonHoanThanh();
    }
}
