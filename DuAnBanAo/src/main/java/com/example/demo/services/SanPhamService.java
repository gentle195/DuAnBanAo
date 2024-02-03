package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SanPhamService {
    List<SanPham> findAll();
    SanPham findById(UUID id);
    List<SanPham> getList();
    Page<SanPham> findAllSP(Pageable pageable);

    Page<SanPham> findByKeyword(String key,Pageable pageable);

    SanPham addSanPham(SanPham sanPham);

    SanPham udpateSanPham(SanPham sanPham);

    SanPham getOne(UUID id);

    SanPham getByMa(String ma);

    List<SanPham> searchSanPham(String keyword);

}
