/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.productservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about the product")
public class ProductDto {

    @ApiModelProperty(notes = "The unique ID of the product")
    private int id;

    @ApiModelProperty(notes = "The name of the product")
    private String title;

    @ApiModelProperty(notes = "The price of the product")
    private double price;

    @ApiModelProperty(notes = "The description of the product")
    private String description;

    @ApiModelProperty(notes = "The category of the product")
    private String category;

    @ApiModelProperty(notes = "The URL of the product image")
    private String image;

    @ApiModelProperty(notes = "The rating details of the product")
    private RatingDto rating;

    @Data
    @ApiModel(description = "Details about the product rating")
    public static class RatingDto {

        @ApiModelProperty(notes = "The average rating of the product")
        private double rate;

        @ApiModelProperty(notes = "The number of users who rated the product")
        private int count;
    }
}
