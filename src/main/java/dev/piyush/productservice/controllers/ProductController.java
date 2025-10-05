package dev.piyush.productservice.controllers;

import dev.piyush.productservice.dto.GenericProductDto;
import dev.piyush.productservice.services.ProductServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    public void getAllProducts(){

    }
    //loccalhost:8080/products/{id}
    //loccalhost:8080/products/123

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productServices.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        //System.out.println();
        return productServices.createProduct(product);
    }
    @PutMapping("{id}")
    public void updateProductById(){

    }
}