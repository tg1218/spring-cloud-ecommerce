package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository repository;
    @Override

    public List<CatalogDto> getAllCatalogs() {
        List<CatalogEntity> catalogs = repository.findAll();

        return catalogs.stream().map(CatalogEntity::toCatalogDto).toList();
    }
}
