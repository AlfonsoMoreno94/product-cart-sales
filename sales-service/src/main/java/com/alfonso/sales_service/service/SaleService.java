

package com.alfonso.sales_service.service;

import com.alfonso.sales_service.client.CartClient;
import com.alfonso.sales_service.dto.CartDTO;
import com.alfonso.sales_service.dto.ProductDTO;
import com.alfonso.sales_service.model.Sale;
import com.alfonso.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleService implements ISaleService {
    
    private final ISaleRepository saleRepo;
    private final CartClient cartClient;
    
    @Override
    public Sale createSale (Long cartId) {
        // Guardar venta
        Sale sale = Sale.builder()
                .cartId(cartId)
                .saleDate(LocalDateTime.now())
                .build();
        return saleRepo.save(sale);
    }
    
    @Override
    @CircuitBreaker (name = "chart-service", fallbackMethod = "fallbackGetCartDetails")
    public CartDTO getCartDetails (Long saleId) {
        Sale sale = saleRepo.findById(saleId).orElseThrow();
        return cartClient.getCart(sale.getCartId());
    }
    
    @Override
    public List<ProductDTO> getProductsFromSale (Long saleId) {
        Sale sale = saleRepo.findById(saleId).orElseThrow();
        return cartClient.getCartProducts(sale.getCartId());
    }

    @Override
    public List<Sale> getAll() {
        return saleRepo.findAll();
    
    }

    @Override
    public void deleteSale(Long saleId) {
       
        saleRepo.deleteById(saleId);
    
    }

    @Override
    public Sale editSale(Long saleId, Sale sale) {
       
        Sale oldSale = this.getSale(saleId);
        oldSale.setCartId(sale.getCartId());
        oldSale.setSaleDate(sale.getSaleDate());
        saleRepo.save(oldSale);
        return oldSale;
    
    }

    @Override
    public Sale getSale(Long saleId) {
        return saleRepo.findById(saleId).orElse(null);
    }

    
    public CartDTO fallbackGetCartDetails(Long saleId, Throwable throwable) {
        log.warn("Fallback activado en getCartDetails para saleId={}, causa: {}", saleId, throwable.getMessage());
        return new CartDTO(9999L, 0.0, List.of(999L,999L,999L));
    }
}
