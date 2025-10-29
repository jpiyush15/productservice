package dev.piyush.productservice.controllers;

import dev.piyush.productservice.dto.ExceptionDto;
import dev.piyush.productservice.dto.GenericProductDto;
import dev.piyush.productservice.exception.NotFoundException;
import dev.piyush.productservice.services.ProductServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
//    field injection
    private ProductServices productServices;
// constructor injection
    public ProductController (@Qualifier("fakeStoreProductService") ProductServices productServices){
        this.productServices = productServices;
    }

    //setter injection
//    @Autowired
//    public void setProductServices(ProductServices productServices){
//        this.productServices = productServices;
//    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productServices.getAllProducts();
    }
    //loccalhost:8080/products/{id}
    //loccalhost:8080/products/123

    @GetMapping("{id}")
    public GenericProductDto getProductById (@PathVariable("id") Long id) throws NotFoundException{
        return productServices.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id){
        ResponseEntity<GenericProductDto> response =
                new ResponseEntity<>(productServices.deleteProductById(id), HttpStatus.OK);
        return response;
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        //System.out.println();
        return productServices.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable Long id,@RequestBody GenericProductDto updatedProduct){
        return productServices.updateProductById(id,updatedProduct);
    }
}