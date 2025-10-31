package dev.piyush.productservice.services;

import dev.piyush.productservice.thirdPartyClient.productService.fakestore.FakeStoreProductDto;
import dev.piyush.productservice.dto.GenericProductDto;
import dev.piyush.productservice.exception.NotFoundException;
import dev.piyush.productservice.thirdPartyClient.productService.fakestore.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductServices{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }
    private GenericProductDto convertFakeStoreDtoToGenricProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDiscription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return  product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return convertFakeStoreDtoToGenricProductDto(fakeStoreProductServiceClient.createProduct(product));
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenricProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genricOfGetAllProduct = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()) {
            genricOfGetAllProduct.add(convertFakeStoreDtoToGenricProductDto(fakeStoreProductDto));
        }
        return genricOfGetAllProduct;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {

        return convertFakeStoreDtoToGenricProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto updatedProduct) {

        return convertFakeStoreDtoToGenricProductDto(fakeStoreProductServiceClient.updateProductById(id,updatedProduct));
    }
}
