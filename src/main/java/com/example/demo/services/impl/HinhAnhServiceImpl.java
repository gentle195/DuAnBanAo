
package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HinhAnh;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.services.HinhAnhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    private HinhAnhRepository repository;

    @Override
    public List<HinhAnh> findAll() {
        return repository.findAll();
    }

    @Override
    public HinhAnh add(HinhAnh hinhAnh) {
        return repository.save(hinhAnh);
    }

    @Override
    public HinhAnh update(UUID id, HinhAnh hinhAnh) {
        if (id != null) {
            HinhAnh ha = repository.findById(id).orElse(null);
            if (ha != null) {
                BeanUtils.copyProperties(hinhAnh, ha);
                repository.save(ha);
            }
        }
        return null;
    }

    @Override
    public String getImageUploadPath() {
        return imageUploadPath;
    }

    @Override
    public HinhAnh search(String ten) {
        return repository.search(ten);
    }

    @Override
    public HinhAnh searchId(UUID id) {
        return repository.searchId(id);
    }


    @Value("/src/main/uploads")
    private String imageUploadPath;
}

