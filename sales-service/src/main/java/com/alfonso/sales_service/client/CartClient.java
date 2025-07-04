
package com.alfonso.sales_service.client;

import com.alfonso.sales_service.dto.CartDTO;
import com.alfonso.sales_service.dto.ProductDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "chart-service")
public interface CartClient {
    @GetMapping("/cart/{id}")
    CartDTO getCart(@PathVariable Long id);

    @GetMapping("/cart/{id}/products")
    List<ProductDTO> getCartProducts(@PathVariable Long id);
}
