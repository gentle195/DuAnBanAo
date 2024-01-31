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
    public List<HoaDon> hoaDonChoGiaoHang() {
        return hoaDonRepository.hoaDonChoGiaoHang();
    }
}
