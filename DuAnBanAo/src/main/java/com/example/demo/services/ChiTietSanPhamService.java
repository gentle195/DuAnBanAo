package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.CoAo;
import com.example.demo.models.KichCo;
import com.example.demo.models.MauSac;
import com.example.demo.models.ThuongHieu;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ChiTietSanPhamService {
    List<ChiTietSanPham> findAll();
    void add(ChiTietSanPham chiTietSanPham);
    void delete(ChiTietSanPham chiTietSanPham);
    ChiTietSanPham update(ChiTietSanPham chiTietSanPham,UUID id);
    Integer getSLGioHang(UUID idKH);

    String getSoLuongByKichCo(UUID idMS, UUID idSP, String size);

    ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC);

    List<MauSac> getMauSacBySP(UUID idSP);

    List<String> getKichCoSacBySP(UUID idSP);

    List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP);

    Page<ChiTietSanPham> searchCTSPByThuongHieuList(List<UUID> idThuongHieuList, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByMS(UUID idMS,Pageable pageable);

    Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable);

    List<ChiTietSanPham> getList();

    Page<ChiTietSanPham> getListSP(Pageable pageable);

    void addKC(ChiTietSanPham qlSanPham);

    void deleteSP(UUID id);

    boolean isChiTietSanPhamExists(ChiTietSanPham sp);

    //seacrchByMau
    Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable);

    Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> searchCoAo(UUID idCoAo, Pageable pageable);

    Page<ChiTietSanPham> searchThuongHieu(UUID idTH, Pageable pageable);

    Page<ChiTietSanPham> searchChatLieu(UUID idCL, Pageable pageable);


    ChiTietSanPham getOne(UUID id);

    Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable);

    List<ThuongHieu> search2(String keyword);

    List<KichCo> search2KC(Integer size);

    List<KichCo> getListKC();
    // v3 updatectsp modal
    UUID getOneToAddModal(UUID id);

    ChiTietSanPham updateSoLuongTon(UUID id,int soLuong);

    ChiTietSanPham updateDelete(UUID id,int soLuong);


    Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable);


    List<ChiTietSanPham> listCTSPSuDung();

    //13.11.2023
    List<ThuongHieu> listLG22(Integer trangThai);
    List<CoAo> listDeGiay22(Integer trangThai);
    List<MauSac> listMauSac22(Integer trangThai);
    List<ChatLieu> listChatLieu22(Integer trangThai);
    List<KichCo> listKichCo22(Integer trangThai);
    List<ThuongHieu> search22LG(String keyword, Integer trangThai);
    List<CoAo> search22DG(String keyword, Integer trangThai);
    List<MauSac> search22MS(String keyword, Integer trangThai);
    List<ChatLieu> search22CL(String keyword, Integer trangThai);
    List<KichCo> search22KC(Integer size, Integer trangThai);

//    List<ChiTietSanPhamCustom> listSPCungLoai (Double giaSP);


    List<ChiTietSanPham> listCTSPByIDSP(UUID id);

//    List<ChiTietSanPhamCustom> listCTSPKhuyenMai (UUID idSanPham);

    ChiTietSanPham findFirstBySanPhamAndChatLieuAndThuongHieuAndMauSacAndDeGiayAndKichCo(
            ChiTietSanPham ctsp);


}
