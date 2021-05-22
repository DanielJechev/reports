package com.example.demo.service;

import com.example.demo.model.dto.ReportDefinitionDto;
import com.example.demo.model.entity.ReportDefinition;

public interface ReportDefinitionService {
    ReportDefinition createReportDefinition(ReportDefinitionDto reportDefinitionDto);
}
