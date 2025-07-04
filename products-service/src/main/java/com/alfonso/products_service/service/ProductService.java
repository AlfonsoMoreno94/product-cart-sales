
package com.alfonso.products_service.service;

import com.alfonso.products_service.model.Product;
import com.alfonso.products_service.repository.ProductsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    
    private final ProductsRepository produRepo;

    @Override
    public List<Product> listarProductos() {
        return produRepo.findAll();
    }

    @Override
    public Product obtenerPorId(Long id) {
        return produRepo.findById(id).orElse(null);
    }

    @Override
    public void createProduct(Product product) {
        produRepo.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        produRepo.deleteById(productId);
    }

    @Override
    public Product editProduct(Long productId, Product product) {
        
        Product oldProduct = this.obtenerPorId(productId);
        oldProduct.setNombre(product.getNombre());
        oldProduct.setMarca(product.getMarca());
        oldProduct.setPrecio(product.getPrecio());
        
        produRepo.save(oldProduct);
        return oldProduct;
    
    }
}
