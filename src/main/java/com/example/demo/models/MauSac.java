package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;
@Entity
@Table(name = "MauSac")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @NotBlank(message = "Không để trống thông tin")
    private String ten;
    @CreationTimestamp
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;
}
