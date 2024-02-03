package com.example.demo.services.impl;

import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.services.HoaDonChiTietSerice;
import com.example.demo.services.HoaDonSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietSericeImpl implements HoaDonChiTietSerice {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;


    @Override
    public List<HoaDonChiTiet> hoaDonChiTietAll(UUID pageable) {
        return hoaDonChiTietRepository.hoaDonChiTietAll(pageable);
    }
}
