package com.example.demo.repositories;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;

import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

    @Query("select hd from HoaDon hd order by hd.ngayTao desc")
    Page<HoaDon> hoaDonAll(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 1 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonChoXacNhan(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 2 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonDaXacNhan(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 3 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonChoGiaoHang(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 4 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonDangVanChuyen(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 5 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonHuy(Pageable pageable);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 6 order by hd.ngayTao desc")
    Page<HoaDon> hoaDonHoanThanh(Pageable pageable);

    //    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 5 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonDaGiaoHang(Pageable pageable);

//    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 6 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonDaThanhToan(Pageable pageable);

//    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 7 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonChoThanhToan(Pageable pageable);


//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =2 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonDangGiaoHang(Pageable pageable);
//
//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =3 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonHoanTat(Pageable pageable);
//
//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =8 or hd.tinhTrang =8 or hd.tinhTrang =9 order by hd.ngayTao desc")
//    Page<HoaDon> hoaDonDaHuy(Pageable pageable);
//
//    @Query("select hd from HoaDon hd left join KhachHang kh on hd.khachHang.id=kh.id where kh.id=:id")
//    Page<HoaDon> hoaDonKH(UUID id);

}
