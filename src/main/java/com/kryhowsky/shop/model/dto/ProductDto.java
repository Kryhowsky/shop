package com.kryhowsky.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 10)
    private String description;

    @Min(value = 0)
    private Double price;

    @Min(value = 0)
    private Integer quantity;

    private Integer revisionNumber;

}
