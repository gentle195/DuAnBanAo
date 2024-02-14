package com.example.demo.services;

import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietSerice {

    List<HoaDonChiTiet> hoaDonChiTietAll(UUID id);

    HoaDonChiTiet add(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet update(UUID id, HoaDonChiTiet hoaDonChiTiet);
}
