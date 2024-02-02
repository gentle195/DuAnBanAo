package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HinhAnh;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.services.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    private HinhAnhRepository repository;
    @Override
    public List<HinhAnh> findAll() {
        return repository.findAll();
    }
}
