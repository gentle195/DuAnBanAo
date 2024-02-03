package com.example.demo.repositories;

import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {

    @Query("select hd from HoaDonChiTiet hd left join HoaDon h on hd.idHoaDon.id=h.id where h.id =:id")
    List<HoaDonChiTiet> hoaDonChiTietAll(UUID id);
}
