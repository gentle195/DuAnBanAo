package com.example.demo.services;

import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HoaDonChiTietSerice {

    Page<HoaDonChiTiet> hoaDonChiTietAll(Pageable pageable);
}
