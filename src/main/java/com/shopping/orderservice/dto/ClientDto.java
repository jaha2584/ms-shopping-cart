/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.orderservice.dto;


import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ClientDto {

    @Schema(description = "The unique identifier of the customer", example = "1")
    private Long id;

    @NotBlank(message = "First name is required")
    @Schema(description = "First name of the customer", example = "John")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the customer", example = "Doe")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email address of the customer", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Phone number of the customer", example = "+123456789")
    private String phoneNumber;
}
