
package com.alfonso.products_service.controller;

import com.alfonso.products_service.model.Product;
import com.alfonso.products_service.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {
    
    private final IProductService service;
    
    @PostMapping
    public String createProduct (@RequestBody Product product) {
        service.createProduct(product);
        return "Producto creado correctamente";
    }
    
    @DeleteMapping ("/{productId}")
    public String deleteProduct (@PathVariable Long productId) {
        service.deleteProduct(productId);
        return "Producto eliminado correctamente";
    }
    
    @PutMapping ("/{productId}/edit")
    public Product editProduct (@PathVariable Long productId, @RequestBody Product product) {
        
        return service.editProduct(productId, product);      
        
        
    }

    @GetMapping
    public List<Product> getAll() {
        return service.listarProductos();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}