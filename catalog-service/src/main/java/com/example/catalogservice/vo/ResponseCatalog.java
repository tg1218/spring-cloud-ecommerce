package com.example.catalogservice.vo;


import com.example.catalogservice.dto.CatalogDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;

    public ResponseCatalog(CatalogDto catalogDto) {
        this.productId = catalogDto.getProductId();
        this.productName = catalogDto.getProductName();
        this.unitPrice = catalogDto.getUnitPrice();
        this.stock = catalogDto.getStock();
    }
}
