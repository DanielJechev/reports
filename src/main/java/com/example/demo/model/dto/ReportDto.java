package com.example.demo.model.dto;

import com.example.demo.model.entity.ReportDefinition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReportDto {

    private Integer id;

    private List<ReportDefinition> reportDefinitionList;

}
