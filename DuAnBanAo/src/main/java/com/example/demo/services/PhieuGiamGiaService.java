package com.example.demo.services;

import com.example.demo.models.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaService {
    public Page<PhieuGiamGia> getAll(Pageable pageable);

    public Page<PhieuGiamGia> getAll1(Pageable pageable);

    public List<PhieuGiamGia> findAll();

    public PhieuGiamGia findById(UUID id);

    public PhieuGiamGia add(PhieuGiamGia phieuGiamGia);

    public PhieuGiamGia update(UUID id,PhieuGiamGia phieuGiamGia);

    public void updateTT();

    public List<PhieuGiamGia> search0(String ten);

    public List<PhieuGiamGia> search1(String ten);
}
