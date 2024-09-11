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
    private Long customerId;

    @NotBlank(message = "First name is required")
    @Schema(description = "First name of the customer", example = "Jose")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the customer", example = "Hernandez")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email address of the customer", example = "jaha2584@gmail.com")
    private String email;

    @Schema(description = "Phone number of the customer", example = "+504 95798869")
    private String phoneNumber;
}
