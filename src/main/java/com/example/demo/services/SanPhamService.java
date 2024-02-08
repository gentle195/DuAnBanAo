package com.example.demo.services;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {
    public Page<SanPham> getAll(Pageable pageable);

    public Page<SanPham> getAll1(Pageable pageable);

    public List<SanPham> findAll();

    public List<SanPham> findAllFullTT();

    public List<ChiTietSanPham> findAllCTSPFullTT();

    public SanPham findById(UUID id);

    public ChiTietSanPham findCTSPById(UUID id);

    public SanPham add(SanPham sanPham);

    public ChiTietSanPham addCTSP(ChiTietSanPham chiTietSanPham);

    public SanPham update(UUID id, SanPham sanPham);

    public ChiTietSanPham updateCTSP(UUID id, ChiTietSanPham chiTietSanPham);

    public void updateTT();

    public List<SanPham> search0(String ten);

    public List<SanPham> search1(String ten);

    List<ChiTietSanPham> findAllCTSP(UUID check);

    List<ChiTietSanPham> findChiTietSanPhamBySanPham(SanPham sanPham);

    SanPham findByMa(String ma);

    SanPham findByTen(String ten);
}
