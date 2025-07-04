
package com.alfonso.chart_service.client;

import com.alfonso.chart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/productos/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}
