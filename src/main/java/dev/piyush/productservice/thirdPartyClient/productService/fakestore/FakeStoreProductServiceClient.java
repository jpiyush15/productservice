package dev.piyush.productservice.thirdPartyClient.productService.fakestore;

import dev.piyush.productservice.dto.GenericProductDto;
import dev.piyush.productservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreApiPathUrl;

    private String specificProductRequestUrl;
    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${fakestore.api.url}")String fakeStoreApiUrl,
            @Value("${fakestore.api.path.product}")String fakeStoreApiPathUrl){
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreApiPathUrl + "/{id}";
        this.productRequestBaseUrl = fakeStoreApiUrl + fakeStoreApiPathUrl;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        // if you dont create your object then spring will create for you
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id : "+id+" Not found");
        }
        //response.getStatusCode()
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);

        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute
                (specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProductById(Long id, GenericProductDto updatedProduct) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(updatedProduct.getTitle());
        fakeStoreProductDto.setDescription(updatedProduct.getDiscription());
        fakeStoreProductDto.setPrice(updatedProduct.getPrice());
        fakeStoreProductDto.setImage(updatedProduct.getImage());
        fakeStoreProductDto.setCategory(updatedProduct.getCategory());

        HttpEntity<FakeStoreProductDto> requestEnity = new HttpEntity<>(fakeStoreProductDto);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                specificProductRequestUrl,
                HttpMethod.PUT,
                requestEnity,
                FakeStoreProductDto.class,
                id);
        FakeStoreProductDto responseFakestoreDto = response.getBody();
        return responseFakestoreDto;
    }
}
