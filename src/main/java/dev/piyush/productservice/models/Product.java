package dev.piyush.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String title;
    private String discription;
    private String image;
    private Category category;
    private double price;

}
