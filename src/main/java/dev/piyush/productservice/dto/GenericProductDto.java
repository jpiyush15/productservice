package dev.piyush.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String discription;
    private String image;
    private String category;
    private double price;
}
