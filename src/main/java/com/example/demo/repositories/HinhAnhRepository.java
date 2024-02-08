package com.example.demo.repositories;

import com.example.demo.models.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
    @Query(value = "select h.Id from ChiTietSanPham ct join HinhAnh h on h.IdCTSP=ct.Id where ct.Id= ?1", nativeQuery = true)
    UUID getIdHinhAnh(UUID keyword);

}
