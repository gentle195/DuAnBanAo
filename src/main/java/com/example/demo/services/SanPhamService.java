package com.example.demo.services;


import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {

    List<SanPham> getList();
    List<SanPham> findAll();
    Page<SanPham> findAllSP(Pageable pageable);

    Page<SanPham> findByKeyword(String key,Pageable pageable);

    SanPham addSanPham(SanPham sanPham);

    SanPham udpateSanPham(SanPham sanPham);

    SanPham getOne(UUID id);

    SanPham getByMa(String ma);

    List<SanPham> searchSanPham(String keyword);

    List<ChiTietSanPham> findAllCTSP();

    public ChiTietSanPham findCTSPById(UUID id);

    public ChiTietSanPham updateCTSP(UUID id, ChiTietSanPham chiTietSanPham);

    List<ChiTietSanPham> loc(UUID idSanPham, UUID idChatLieu, UUID idCoAo, UUID idKichCo, UUID idMauSac, UUID idThuongHieu);
    ChiTietSanPham scan(String ma);
    List<ChiTietSanPham> showQR(UUID id);
}
