package com.example.catalogservice.jpa;

import com.example.catalogservice.dto.CatalogDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "catalogs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id", nullable = false, length = 120, unique = true)
    private String productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;
    @Column
    private LocalDate createdAt;

    public CatalogDto toCatalogDto(){
        CatalogDto catalogDto = new CatalogDto();
        catalogDto.setProductId(productId);
        catalogDto.setProductName(productName);
        catalogDto.setUnitPrice(unitPrice);
        catalogDto.setStock(stock);

        return catalogDto;
    }

    public void updateStock(Integer qty) {
        this.stock += qty;
    }
}
