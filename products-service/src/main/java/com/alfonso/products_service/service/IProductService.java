
package com.alfonso.products_service.service;

import com.alfonso.products_service.model.Product;
import java.util.List;


public interface IProductService {
    
    public List<Product> listarProductos();
    public Product obtenerPorId(Long id);

    public void createProduct(Product product);

    public void deleteProduct(Long productId);

    public Product editProduct(Long productId, Product product);
    
    
}
