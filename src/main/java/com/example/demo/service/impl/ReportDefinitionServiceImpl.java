package com.example.demo.service.impl;

import com.example.demo.model.dto.ReportDefinitionDto;
import com.example.demo.model.entity.ReportDefinition;
import com.example.demo.repository.ReportDefinitionRepository;
import com.example.demo.service.ReportDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportDefinitionServiceImpl implements ReportDefinitionService {
    private ReportDefinitionRepository reportDefinitionRepository;

    @Autowired
    public ReportDefinitionServiceImpl(ReportDefinitionRepository reportDefinitionRepository) {
        this.reportDefinitionRepository = reportDefinitionRepository;
    }

    @Override
    public ReportDefinition createReportDefinition(ReportDefinitionDto reportDefinitionDto) {
        ReportDefinition reportDefinition = new ReportDefinition();
        reportDefinition.setTopPerformersThreshold(reportDefinitionDto.getTopPerformersThreshold());
        reportDefinition.setUseExprienceMultiplier(reportDefinitionDto.getUseExprienceMultiplier());
        reportDefinition.setPeriodLimit(reportDefinitionDto.getPeriodLimit());

        return reportDefinitionRepository.save(reportDefinition);
    }
}
