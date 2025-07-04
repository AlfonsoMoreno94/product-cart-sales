
package com.alfonso.chart_service.controller;

import com.alfonso.chart_service.dto.ProductDTO;
import com.alfonso.chart_service.model.Cart;
import com.alfonso.chart_service.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;
    
    @GetMapping
    public List <Cart> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return service.getCart(id);
    }
    
    @PostMapping ("/create")
    public String createCart(@RequestBody List<Long> productsIdList) {
        service.createCart(productsIdList);
        return "Carrito creado con éxito";
    }
    
    

    @PostMapping("/{cartId}/add")
    public Cart addItem(@PathVariable Long cartId, @PathVariable Long productId) {
        return service.addProduct(cartId, productId);
    }
    
    @DeleteMapping ("/{cartId}/delete")
    public String deleteCart (@PathVariable Long cartId) {
        service.deleteCart(cartId);
        return "Carrito borrado con éxito";
    }

    @DeleteMapping("/{cartId}/remove/{itemId}")
    public Cart removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        return service.removeProduct(cartId, itemId);
    }
    
    @GetMapping("/cart/{id}/products")
    public List<ProductDTO> getProductDetails(@PathVariable Long id) {
        return service.getProductDetails(id);
    }
    
    @PutMapping ("/{cartId}/edit")
    public Cart editCart (@PathVariable Long cartId, @RequestBody Cart cart) {
        return service.editCart (cartId, cart);
    }
}
