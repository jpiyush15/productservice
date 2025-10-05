package dev.piyush.productservice.services;

import dev.piyush.productservice.dto.GenericProductDto;
import org.springframework.stereotype.Service;

@Service
public class SelfProductServiceImpl implements ProductServices{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
}
