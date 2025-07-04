
package com.alfonso.chart_service.service;

import com.alfonso.chart_service.client.ProductClient;
import com.alfonso.chart_service.dto.ProductDTO;
import com.alfonso.chart_service.model.Cart;
import com.alfonso.chart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepo;
    private final ProductClient productClient;

    public Cart getCart(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    public Cart addProduct(Long cartId, Long productId) {
        ProductDTO product = productClient.getProductById(productId);
        Cart cart = cartRepo.findById(cartId).orElse(Cart.builder().totalPrice(0.0).build());

        cart.getProductIds().add(productId);
        double total = cart.getProductIds().stream()
            .map(productClient::getProductById)
            .mapToDouble(ProductDTO::getPrecio)
            .sum();
        cart.setTotalPrice(total);

        return cartRepo.save(cart);
    }

    public Cart removeProduct(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if (cart == null) return null;

        cart.getProductIds().remove(productId);
        double total = cart.getProductIds().stream()
            .map(productClient::getProductById)
            .mapToDouble(ProductDTO::getPrecio)
            .sum();
        cart.setTotalPrice(total);

        return cartRepo.save(cart);
    }

    public List<ProductDTO> getProductDetails(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        return cart == null ? List.of() :
            cart.getProductIds().stream().map(productClient::getProductById).toList();
    }

    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    public void createCart(List<Long> productsIdList) {
        
        Cart cart = new Cart();
        cart.setProductIds(productsIdList);
        double total = productsIdList.stream()
            .map(productClient::getProductById)
            .mapToDouble(ProductDTO::getPrecio)
            .sum();
        cart.setTotalPrice(total);
        
        
        cartRepo.save(cart);
     }

    public void deleteCart(Long cartId) {
        
        cartRepo.deleteById(cartId);
    }

    public Cart editCart(Long cartId, Cart cart) {
        
        Cart oldCart = this.getCart(cartId);
        oldCart.setProductIds(cart.getProductIds());
        oldCart.setTotalPrice(cart.getTotalPrice());
        cartRepo.save(oldCart);
        
        return oldCart;
                
    
    }

}
