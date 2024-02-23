package com.example.demo.repositories;

import com.example.demo.models.CoAo;
import com.example.demo.models.KhachHang;
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
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    @Query("select c from KhachHang c  where c.trangThai=0 order by c.ngayTao desc ")
    Page<KhachHang> getAll(Pageable pageable);

    @Query("select c from KhachHang c  where c.trangThai=1 order by c.ngayTao desc ")
    Page<KhachHang> getAll1(Pageable pageable);

    @Query("select c from KhachHang c  where  c.trangThai = 0")
    List<KhachHang> findAll0();

    @Query("select c from KhachHang c  where  c.trangThai = 0 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<KhachHang> search0(String ten);

    @Query("select c from KhachHang c  where  c.trangThai = 1 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<KhachHang> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update KhachHang set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    boolean existsKhachHangByEmail(String email);
    boolean existsKhachHangBySoDienThoai(String email);

    List<KhachHang> findAllByTrangThai(int trangThai);
    Page<KhachHang> findAllByGioiTinh(Boolean gioiTinh,Pageable pageable);
    List<KhachHang> findAllByTrangThaiAndGioiTinh(int trangThai,Boolean gioiTinh);
}
