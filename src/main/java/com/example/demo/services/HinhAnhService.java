package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HinhAnh;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface HinhAnhService {
    List<HinhAnh> findAll();

    HinhAnh add(HinhAnh hinhAnh);

    HinhAnh update(UUID id, HinhAnh hinhAnh);

    String getImageUploadPath();

    HinhAnh search(String ten);

    HinhAnh searchId(UUID id);
}
