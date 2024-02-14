package com.example.demo.services;

import com.example.demo.models.*;
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

    List<ChiTietSanPham> findAllCTSP();

    List<ChiTietSanPham> findChiTietSanPhamBySanPham(SanPham sanPham);

    SanPham findByMa(String ma);

    SanPham findByTen(String ten);

    List<ChiTietSanPham> showQR(UUID id);

    boolean existsByChatLieuAndCoAoAndKichCoAndMauSacAndThuongHieu(ChatLieu chatLieu, CoAo coAo, KichCo kichCo, MauSac mauSac, ThuongHieu thuongHieu);

    List<ChiTietSanPham> loc(UUID idSanPham, UUID idChatLieu, UUID idCoAo, UUID idKichCo, UUID idMauSac, UUID idThuongHieu);

    ChiTietSanPham scan(String ma);
}
