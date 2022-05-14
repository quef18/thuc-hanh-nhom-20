package com.example.Nhom20.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
}
