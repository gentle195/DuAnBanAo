package com.example.demo.repositories;

import com.example.demo.models.NhanVien;
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
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("select c from NhanVien c  where c.tinhTrang=0 order by c.ngayTao desc ")
    Page<NhanVien> getAll(Pageable pageable);

    @Query("select c from NhanVien c  where c.tinhTrang=1 order by c.ngayTao desc ")
    Page<NhanVien> getAll1(Pageable pageable);

    @Query("select c from NhanVien c  where  c.tinhTrang = 0")
    List<NhanVien> findAll0();

    @Query("select c from NhanVien c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<NhanVien> search0(String ten);

    @Query("select c from NhanVien c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<NhanVien> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update NhanVien set tinhTrang=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    boolean existsNhanVienBySdt(String sdt);
    boolean existsNhanVienByEmail(String sdt);
    boolean existsNhanVienByCCCD(String cccd);
    List<NhanVien> findAllByTinhTrang(int tinhTrang);
    Page<NhanVien> findAllByGioiTinh(Boolean gioiTinh,Pageable pageable);
    List<NhanVien> findAllByTinhTrangAndGioiTinh(int tinhTrang,Boolean gioiTinh);
    @Query("SELECT nv FROM NhanVien nv " +
            "INNER JOIN nv.chucVu cv " +
            "WHERE  cv.ten = :tenChucVu " +
            "AND  nv.gioiTinh = :gioiTinh")
    Page<NhanVien> searchByTenChucVuAndGioiTinh(String tenChucVu,
                                                Boolean gioiTinh,Pageable pageable);
}
