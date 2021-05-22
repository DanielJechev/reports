package com.example.demo.service;

import com.example.demo.model.dto.DataDto;
import com.example.demo.model.dto.ReportDto;
import com.example.demo.model.entity.Data;

import java.util.List;

public interface DataService {
    Data createData(DataDto dataDto);

    Data addReportDefinition(ReportDto reportDto);

    List<Data> getAll();
}

