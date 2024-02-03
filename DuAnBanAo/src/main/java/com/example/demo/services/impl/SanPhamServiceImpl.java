package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository repository;
    @Override
    public List<SanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public SanPham findById(UUID id) {
        return repository.findById(id).get();
    }
    @Override
    public List<SanPham> getList() {
        return repository.findAll();
    }

    @Override
    public Page<SanPham> findAllSP(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<SanPham> findByKeyword(String key, Pageable pageable) {
        return repository.findAllByKeWord(key,pageable);
    }

    @Override
    public SanPham addSanPham(SanPham sanPham) {
        return repository.save(sanPham) ;
    }

    @Override
    public SanPham udpateSanPham(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SanPham getByMa(String ma) {
        return repository.getSanPhamByMaSP(ma);
    }

    @Override
    public List<SanPham> searchSanPham(String keyword) {
        return repository.searchSanPham(keyword);
    }
}
