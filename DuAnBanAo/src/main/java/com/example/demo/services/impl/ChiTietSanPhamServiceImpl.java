package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.CoAo;
import com.example.demo.models.KichCo;
import com.example.demo.models.MauSac;
import com.example.demo.models.ThuongHieu;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.services.ChiTietSanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Override
    public List<ChiTietSanPham> findAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public void add(ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void delete(ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamRepository.delete(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham, UUID id) {
        if (id != null) {
            ChiTietSanPham chiTietSanPhamUpdate = chiTietSanPhamRepository.findById(id).orElse(null);
            if (chiTietSanPhamUpdate != null) {
                BeanUtils.copyProperties(chiTietSanPham, chiTietSanPhamUpdate);
                chiTietSanPhamRepository.save(chiTietSanPhamUpdate);
            }
        }
        return null;
    }

    @Override
    public Integer getSLGioHang(UUID idKH) {
        return chiTietSanPhamRepository.getSLGioHang(idKH);
    }

    @Override
    public String getSoLuongByKichCo(UUID idMS, UUID idSP, String size) {
        return chiTietSanPhamRepository.getSoLuongByKichCo(idMS, idSP, size);
    }

    @Override
    public ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC) {
        return chiTietSanPhamRepository.findctspAddCart(idSP,idMS,idKC);
    }

    @Override
    public List<MauSac> getMauSacBySP(UUID idSP) {
        return chiTietSanPhamRepository.getMauBySanPham(idSP);
    }

    @Override
    public List<String> getKichCoSacBySP(UUID idSP) {
        return chiTietSanPhamRepository.getKichCoBySanPham(idSP);
    }

    @Override
    public List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP) {
        return chiTietSanPhamRepository.getKichCoByMauSacAndSanPham(idMS,idSP);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSPByThuongHieuList(List<UUID> idThuongHieuList, Pageable pageable) {
        return chiTietSanPhamRepository.searchCTSPByThuongHieuList(idThuongHieuList,pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable) {
        return chiTietSanPhamRepository.getCTSPBYKC(idKC,pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByMS(UUID idMS, Pageable pageable) {
        return chiTietSanPhamRepository.getCTSPBYMS(idMS,pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable) {
        return chiTietSanPhamRepository.getCTSPByGiaBan(minPrice,maxPrice,pageable);
    }

    @Override
    public List<ChiTietSanPham> getList() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public Page<ChiTietSanPham> getListSP(Pageable pageable) {
        return chiTietSanPhamRepository.getListSP(pageable);
    }

    @Override
    public void addKC(ChiTietSanPham qlSanPham) {
        chiTietSanPhamRepository.save(qlSanPham);
    }

    @Override
    public void deleteSP(UUID id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public boolean isChiTietSanPhamExists(ChiTietSanPham sp) {
        return chiTietSanPhamRepository.existsBySanPhamAndChatLieuAndThuongHieuAndMauSacAndCoAoAndKichCo(
                sp.getSanPham(), sp.getChatLieu(), sp.getThuongHieu(), sp.getMauSac(), sp.getCoAo(), sp.getKichCo()
        );
    }

    @Override
    public Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable) {
        return chiTietSanPhamRepository.searchByMau(idMau,pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable) {
        return chiTietSanPhamRepository.searchByKichCo(idKC,pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchCoAo(UUID idCoAo, Pageable pageable) {
        return chiTietSanPhamRepository.searchByCoAo(idCoAo,pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchThuongHieu(UUID idTH, Pageable pageable) {
        return chiTietSanPhamRepository.searchByThuongHieu(idTH,pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchChatLieu(UUID idCL, Pageable pageable) {
        return chiTietSanPhamRepository.searchByChatLieu(idCL,pageable);
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return chiTietSanPhamRepository.findById(id).get();
    }

    @Override
    public Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable) {
        return chiTietSanPhamRepository.searchCTSP(keyword,pageable);
    }

    @Override
    public List<ThuongHieu> search2(String keyword) {
        return chiTietSanPhamRepository.search(keyword);
    }

    @Override
    public List<KichCo> search2KC(Integer size) {
        return chiTietSanPhamRepository.search2KC(size);
    }

    @Override
    public List<KichCo> getListKC() {
        return chiTietSanPhamRepository.listKC();
    }

    @Override
    public UUID getOneToAddModal(UUID id) {
        return chiTietSanPhamRepository.getOneToAddModal(id);
    }

    @Override
    public ChiTietSanPham updateSoLuongTon(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuongTon(soLuong);
            chiTietSanPhamRepository.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }
    }

    @Override
    public ChiTietSanPham updateDelete(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuongTon(soLuong + chiTietSanPham.getSoLuongTon());
            chiTietSanPhamRepository.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }
    }

    @Override
    public Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable) {
        return chiTietSanPhamRepository.listCTSP(id,pageable);
    }

    @Override
    public List<ChiTietSanPham> listCTSPSuDung() {
        return chiTietSanPhamRepository.getListCTSPSuDung();
    }

    @Override
    public List<ThuongHieu> listLG22(Integer trangThai) {
        return chiTietSanPhamRepository.listLG22(trangThai);
    }

    @Override
    public List<CoAo> listDeGiay22(Integer trangThai) {
        return chiTietSanPhamRepository.listCoAo22(trangThai);
    }

    @Override
    public List<MauSac> listMauSac22(Integer trangThai) {
        return chiTietSanPhamRepository.listMauSac22(trangThai);
    }

    @Override
    public List<ChatLieu> listChatLieu22(Integer trangThai) {
        return chiTietSanPhamRepository.listChatLieu22(trangThai);
    }

    @Override
    public List<KichCo> listKichCo22(Integer trangThai) {
        return chiTietSanPhamRepository.listKichCo22(trangThai);
    }

    @Override
    public List<ThuongHieu> search22LG(String keyword, Integer trangThai) {
        return chiTietSanPhamRepository.search22LG(keyword,trangThai);
    }

    @Override
    public List<CoAo> search22DG(String keyword, Integer trangThai) {
        return chiTietSanPhamRepository.search22DG(keyword, trangThai);
    }

    @Override
    public List<MauSac> search22MS(String keyword, Integer trangThai) {
        return chiTietSanPhamRepository.search22MS(keyword, trangThai);
    }

    @Override
    public List<ChatLieu> search22CL(String keyword, Integer trangThai) {
        return chiTietSanPhamRepository.search22CL(keyword, trangThai);
    }

    @Override
    public List<KichCo> search22KC(Integer size, Integer trangThai) {
        return chiTietSanPhamRepository.search22KC(size, trangThai);
    }

    @Override
    public List<ChiTietSanPham> listCTSPByIDSP(UUID id) {
        return chiTietSanPhamRepository.listCTSPByIDSP(id);
    }

    @Override
    public ChiTietSanPham findFirstBySanPhamAndChatLieuAndThuongHieuAndMauSacAndDeGiayAndKichCo(ChiTietSanPham ctsp) {
        return chiTietSanPhamRepository.findFirstBySanPhamAndChatLieuAndThuongHieuAndMauSacAndCoAoAndKichCo(
                ctsp.getSanPham(), ctsp.getChatLieu(), ctsp.getThuongHieu(), ctsp.getMauSac(), ctsp.getCoAo(), ctsp.getKichCo()
        );
    }
}
