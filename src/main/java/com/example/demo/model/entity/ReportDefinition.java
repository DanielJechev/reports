package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ReportDefinition extends BaseEntity {
    @NotNull
    private Integer topPerformersThreshold;

    @NotNull
    private Boolean useExprienceMultiplier;

    @NotNull
    private Integer periodLimit;

}
