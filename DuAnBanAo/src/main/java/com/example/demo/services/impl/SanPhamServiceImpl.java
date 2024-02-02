package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository repository;
    @Override
    public List<SanPham> findAll() {
        return repository.findAll();
    }
}
