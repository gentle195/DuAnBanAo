package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.repositories.PhieuGiamGiaRepository;
import com.example.demo.services.HoaDonSerice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonSericeImpl implements HoaDonSerice {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public Page<HoaDon> hoaDonAll(Pageable pageable) {
        return hoaDonRepository.hoaDonAll(pageable);
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public Page<HoaDon> hoaDonHuy(Pageable pageable) {
        return hoaDonRepository.hoaDonHuy(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonChoXacNhan(Pageable pageable) {
        return hoaDonRepository.hoaDonChoXacNhan(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonDaXacNhan(Pageable pageable) {
        return hoaDonRepository.hoaDonDaXacNhan(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonChoGiaoHang(Pageable pageable) {
        return hoaDonRepository.hoaDonChoGiaoHang(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonDangVanChuyen(Pageable pageable) {
        return hoaDonRepository.hoaDonDangVanChuyen(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonHoanThanh(Pageable pageable) {
        return hoaDonRepository.hoaDonHoanThanh(pageable);
    }

    @Override
    public List<HoaDon> hoaDonCho() {
        return hoaDonRepository.hoaDonCho();
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon update(UUID id, HoaDon hoaDon) {
        if (id != null) {
            HoaDon hoaDonUpdate = hoaDonRepository.findById(id).orElse(null);
            if (hoaDonUpdate != null) {
                BeanUtils.copyProperties(hoaDon, hoaDonUpdate);
                hoaDonRepository.save(hoaDonUpdate);
            }
        }
        return null;
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAllHoaDon();
    }


//    @Override
//    public Page<HoaDon> hoaDonDaGiaoHang(Pageable pageable) {
//        return hoaDonRepository.hoaDonDaGiaoHang(pageable);
//    }

//    @Override
//    public Page<HoaDon> hoaDonDaThanhToan(Pageable pageable) {
//        return hoaDonRepository.hoaDonChoThanhToan(pageable);
//    }

//    @Override
//    public Page<HoaDon> hoaDonChoThanhToan(Pageable pageable) {
//        return hoaDonRepository.hoaDonDaThanhToan(pageable);
//    }

}
