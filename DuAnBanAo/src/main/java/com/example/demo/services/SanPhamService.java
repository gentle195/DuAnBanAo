package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.SanPham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SanPhamService {
    List<SanPham> findAll();

}
