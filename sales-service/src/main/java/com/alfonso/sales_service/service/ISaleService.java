/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.alfonso.sales_service.service;

import com.alfonso.sales_service.dto.CartDTO;
import com.alfonso.sales_service.dto.ProductDTO;
import com.alfonso.sales_service.model.Sale;
import java.util.List;

/**
 *
 * @author alfonso
 */
public interface ISaleService {

    public Sale createSale(Long cartId);

    public CartDTO getCartDetails(Long saleId);

    public List<ProductDTO> getProductsFromSale(Long saleId);

    public List<Sale> getAll();

    public void deleteSale(Long saleId);

    public Sale editSale(Long saleId, Sale sale);

    public Sale getSale(Long saleId);

}
