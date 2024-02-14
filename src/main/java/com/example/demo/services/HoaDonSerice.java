package com.example.demo.services;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HoaDonSerice {


    Page<HoaDon> hoaDonAll(Pageable pageable);

    HoaDon findById(UUID id);

    Page<HoaDon> hoaDonHuy(Pageable pageable);

    Page<HoaDon> hoaDonChoXacNhan(Pageable pageable);

    Page<HoaDon> hoaDonDaXacNhan(Pageable pageable);

    Page<HoaDon> hoaDonChoGiaoHang(Pageable pageable);

    Page<HoaDon> hoaDonDangVanChuyen(Pageable pageable);

    Page<HoaDon> hoaDonHoanThanh(Pageable pageable);

    List<HoaDon> hoaDonCho();

    HoaDon add(HoaDon hoaDon);

    HoaDon update(UUID id,HoaDon hoaDon);

    List<HoaDon> findAll();

//     Page<HoaDon> hoaDonDaGiaoHang(Pageable pageable);

//     Page<HoaDon> hoaDonDaThanhToan(Pageable pageable);

//     Page<HoaDon> hoaDonChoThanhToan(Pageable pageable);


}
