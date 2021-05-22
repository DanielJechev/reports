package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Data extends BaseEntity {
    @NotNull
    private String name;

    @NotNull
    private Integer totalSales;

    @NotNull
    private Integer salesPeriod;

    @NotNull
    private Double experienceMultiplier;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "data_report_definition", joinColumns = {@JoinColumn(name = "data_id")}, inverseJoinColumns = {@JoinColumn(name = "report_definition_id")})
    private List<ReportDefinition> reportDefinitionList;

    private Double score;
}
