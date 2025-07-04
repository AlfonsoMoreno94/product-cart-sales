

package com.alfonso.sales_service.dto;

import lombok.Data;


@Data
public class ProductDTO {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
}
