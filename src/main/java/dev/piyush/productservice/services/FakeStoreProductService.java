package dev.piyush.productservice.services;

import dev.piyush.productservice.dto.FakeStoreProductDto;
import dev.piyush.productservice.dto.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductServices{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response =
                restTemplate.postForEntity(createProductRequestUrl, product, GenericProductDto.class);
        return response.getBody();
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        // if you dont create your object then spring will create for you
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDiscription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());



        //response.getStatusCode()
        return product;
        //return null;
    }
}
