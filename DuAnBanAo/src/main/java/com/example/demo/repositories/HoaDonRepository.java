package com.example.demo.repositories;

import com.example.demo.models.HoaDon;
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
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

//    @Query("select hd from HoaDon hd where (hd.tinhTrang= 0 or hd.tinhTrang= 1 or hd.tinhTrang= 2 or hd.tinhTrang= 3) and hd.tinhTrangGiaoHang =0 order by hd.ngayTao desc")
//    List<HoaDon> hoaDon();

    @Query("select hd from HoaDon hd where hd.trangThaiHoaDon = 1 order by hd.ngayTao desc")
    List<HoaDon> hoaDonChoGiaoHang();

//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =2 order by hd.ngayTao desc")
//    List<HoaDon> hoaDonDangGiaoHang();
//
//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =3 order by hd.ngayTao desc")
//    List<HoaDon> hoaDonHoanTat();
//
//    @Query("select hd from HoaDon hd where hd.tinhTrangGiaoHang =8 or hd.tinhTrang =8 or hd.tinhTrang =9 order by hd.ngayTao desc")
//    List<HoaDon> hoaDonDaHuy();
//
//    @Query("select hd from HoaDon hd left join KhachHang kh on hd.khachHang.id=kh.id where kh.id=:id")
//    List<HoaDon> hoaDonKH(UUID id);

}
