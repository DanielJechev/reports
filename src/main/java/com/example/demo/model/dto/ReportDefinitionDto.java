package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ReportDefinitionDto {

    @NotNull(message = "Please, provide top performers threshold!")
    @Min(value = 1)
    private Integer topPerformersThreshold;

    @NotNull(message = "Please,choose valid type between true or false!")
    private Boolean useExprienceMultiplier;

    @NotNull(message = "Please, provide period limit!")
    @Min(value = 1)
    private Integer periodLimit;

}