package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HinhAnh;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HinhAnhService {
    List<HinhAnh> findAll();
    String getImageUploadPath();

}
