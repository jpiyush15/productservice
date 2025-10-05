package dev.piyush.productservice.services;

import dev.piyush.productservice.dto.GenericProductDto;

public interface ProductServices {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);
}
