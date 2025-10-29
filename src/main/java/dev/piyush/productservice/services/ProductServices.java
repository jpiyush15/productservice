package dev.piyush.productservice.services;

import dev.piyush.productservice.dto.GenericProductDto;
import dev.piyush.productservice.exception.NotFoundException;

import java.util.List;

public interface ProductServices {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);

    GenericProductDto updateProductById(Long id, GenericProductDto updatedProduct);
}
