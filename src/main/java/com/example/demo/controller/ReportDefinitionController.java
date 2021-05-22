package com.example.demo.controller;

import com.example.demo.model.dto.ReportDefinitionDto;
import com.example.demo.model.entity.ReportDefinition;
import com.example.demo.service.ReportDefinitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/definition")
public class ReportDefinitionController {
    private ModelMapper modelMapper = new ModelMapper();
    private ReportDefinitionService reportDefinitionService;


    @Autowired
    public ReportDefinitionController(ReportDefinitionService reportDefinitionService) {
        this.reportDefinitionService = reportDefinitionService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReportDefinitionDto> createReportDefinition(@Valid @RequestBody ReportDefinitionDto reportDefinitionDto) {
        ReportDefinition savedReportDefinition = reportDefinitionService.createReportDefinition(reportDefinitionDto);
        return new ResponseEntity(modelMapper.map(savedReportDefinition, ReportDefinitionDto.class), HttpStatus.CREATED);
    }
}
