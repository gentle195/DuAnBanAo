package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.SanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select c from ChiTietSanPham c  where c.trangThai=0 order by c.ngayTao desc ")
    Page<ChiTietSanPham> getAll(Pageable pageable);

    @Query("select c from ChiTietSanPham c  where c.trangThai=1 order by c.ngayTao desc ")
    Page<ChiTietSanPham> getAll1(Pageable pageable);

    @Query("select c from ChiTietSanPham c  where c.sanPham.id =: check")
    List<ChiTietSanPham> findAllCTSP(UUID check);

    List<ChiTietSanPham> findChiTietSanPhamBySanPham(SanPham sanPham);

    @Transactional
    @Modifying
    @Query(value = "update ChiTietSanPham set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

}
