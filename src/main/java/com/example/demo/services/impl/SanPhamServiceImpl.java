package com.example.demo.services.impl;

import com.example.demo.models.*;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.SanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepo;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public Page<SanPham> getAll(Pageable pageable) {
        return sanPhamRepo.getAll(pageable);
    }

    @Override
    public Page<SanPham> getAll1(Pageable pageable) {
        return sanPhamRepo.getAll1(pageable);
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepo.findAll0();
    }

    @Override
    public List<SanPham> findAllFullTT() {
        return sanPhamRepo.findAll();
    }

    @Override
    public List<ChiTietSanPham> findAllCTSPFullTT() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepo.findById(id).orElse(null);
    }

    @Override
    public ChiTietSanPham findCTSPById(UUID id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public SanPham add(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    @Override
    public ChiTietSanPham addCTSP(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public SanPham update(UUID id, SanPham sanPham) {
        if (id != null) {
            SanPham sanPhamUpdate = sanPhamRepo.findById(id).orElse(null);
            if (sanPhamUpdate != null) {
                BeanUtils.copyProperties(sanPham, sanPhamUpdate);
                sanPhamRepo.save(sanPhamUpdate);
            }
        }
        return null;
    }

    @Override
    public ChiTietSanPham updateCTSP(UUID id, ChiTietSanPham chiTietSanPham) {
        if (id != null) {
            ChiTietSanPham chiTietUpdate = chiTietSanPhamRepository.findById(id).orElse(null);
            if (chiTietUpdate != null) {
                BeanUtils.copyProperties(chiTietSanPham, chiTietUpdate);
                chiTietSanPhamRepository.save(chiTietUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<SanPham> search0(String ten) {
        return sanPhamRepo.search0(ten);
    }

    @Override
    public List<SanPham> search1(String ten) {
        return sanPhamRepo.search1(ten);
    }

    @Override
    public List<ChiTietSanPham> findAllCTSP() {
        return chiTietSanPhamRepository.findAllCTSP();
    }

    @Override
    public List<ChiTietSanPham> findChiTietSanPhamBySanPham(SanPham sanPham) {
        return chiTietSanPhamRepository.findChiTietSanPhamBySanPham(sanPham);
    }

    @Override
    public SanPham findByMa(String ma) {
        return sanPhamRepo.findSanPhamByMa(ma);
    }

    @Override
    public SanPham findByTen(String ten) {
        return sanPhamRepo.findSanPhamByTen(ten);
    }

    @Override
    public List<ChiTietSanPham> showQR(UUID id) {
        return chiTietSanPhamRepository.showQR(id);
    }

    @Override
    public boolean existsByChatLieuAndCoAoAndKichCoAndMauSacAndThuongHieu(ChatLieu chatLieu, CoAo coAo, KichCo kichCo, MauSac mauSac, ThuongHieu thuongHieu) {
        return chiTietSanPhamRepository.existsByChatLieuAndCoAoAndKichCoAndMauSacAndThuongHieu(chatLieu, coAo, kichCo, mauSac, thuongHieu);
    }

    @Override
    public List<ChiTietSanPham> loc(UUID idSanPham, UUID idChatLieu, UUID idCoAo, UUID idKichCo, UUID idMauSac, UUID idThuongHieu) {
        return chiTietSanPhamRepository.loc(idSanPham, idChatLieu, idCoAo, idKichCo, idMauSac, idThuongHieu);
    }

    @Override
    public ChiTietSanPham scan(String ma) {
        return chiTietSanPhamRepository.scan(ma);
    }
}
