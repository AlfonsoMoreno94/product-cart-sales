
package com.alfonso.sales_service.controller;

import com.alfonso.sales_service.dto.CartDTO;
import com.alfonso.sales_service.dto.ProductDTO;
import com.alfonso.sales_service.model.Sale;
import com.alfonso.sales_service.service.ISaleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/sales")
@RequiredArgsConstructor
public class SaleController {
    
    private final ISaleService service;
    
    @PostMapping ("/{cartId}")
    public Sale registerSale (@PathVariable Long cartId) {
        return service.createSale(cartId);
    }
    
    @GetMapping ("/{saleId}")
    public Sale getSale (@PathVariable Long saleId) {
        return service.getSale(saleId);
    }
    
    @GetMapping ("/{saleId}/cart")
    public CartDTO getCartInfo(@PathVariable Long saleId) {
        return service.getCartDetails(saleId);
    }
    
    @GetMapping ("/{saleId}/products")
    public List<ProductDTO> getProducts (@PathVariable Long saleId) {
        return service.getProductsFromSale(saleId);
    }
    
    @GetMapping ("/sales")
    public List <Sale> getAll() {
        return service.getAll();
    }
    
    @DeleteMapping ("/{saleId}/delete")
    public String deleteSale(@PathVariable Long saleId) {
        service.deleteSale(saleId);
        return "Venta borrada con éxito";
    }
    
    @PutMapping ("/{saleId}/edit")
    public Sale editSale (@PathVariable Long saleId, @RequestBody Sale sale) {
        return service.editSale (saleId, sale);
    }
    

}
