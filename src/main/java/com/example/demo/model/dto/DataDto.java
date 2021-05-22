package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor

public class DataDto {
    @NotNull(message = "This field cannot be empty. Please, provide your full name!")
    @Size(max = 255, message = "Invalid length. Please,enter valid full name!")
    @Pattern(regexp = "^[A-Z]{1}([-']?[A-Z]?[a-z]?)[a-z]+([-']?[a-z]+)*([\\s\\-]{1}[A-Z]{1}[a-z]+([-']?[a-z]+)+)+$", message = "Invalid full name!")
    private String name;

    @NotNull(message = "Please, provide total sales!")
    @Min(value = 1)
    private Integer totalSales;

    @NotNull(message = "Please, provide sales period!")
    @Min(value = 1)
    private Integer salesPeriod;

    @NotNull(message = "Please, provide experience multiplier!")
    @Min(value = (long) 0.01, message = "Multiplier cannot be less than 0.01 !")
    private Double experienceMultiplier;

}
