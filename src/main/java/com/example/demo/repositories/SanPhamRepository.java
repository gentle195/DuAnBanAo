package com.example.demo.repositories;

import com.example.demo.models.SanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("select c from SanPham c  where c.trangThai=0 order by c.ngayTao desc ")
    Page<SanPham> getAll(Pageable pageable);

    @Query("select c from SanPham c  where c.trangThai=1 order by c.ngayTao desc ")
    Page<SanPham> getAll1(Pageable pageable);

    @Query("select c from SanPham c  where  c.trangThai = 0")
    List<SanPham> findAll0();

    @Query("select c from SanPham c  where  c.trangThai = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<SanPham> search0(String ten);

    @Query("select c from SanPham c  where  c.trangThai = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<SanPham> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update SanPham set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    SanPham findSanPhamByMa(String ma);
    SanPham findSanPhamByTen(String ten);
}
