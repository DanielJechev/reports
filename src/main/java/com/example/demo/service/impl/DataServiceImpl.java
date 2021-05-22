package com.example.demo.service.impl;

import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.ReportDefinitionNotFoundException;
import com.example.demo.model.dto.DataDto;
import com.example.demo.model.dto.ReportDto;
import com.example.demo.model.entity.Data;
import com.example.demo.model.entity.ReportDefinition;
import com.example.demo.repository.DataRepository;
import com.example.demo.repository.ReportDefinitionRepository;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {
    private DataRepository dataRepository;
    private ReportDefinitionRepository reportDefinitionRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository, ReportDefinitionRepository reportDefinitionRepository) {
        this.dataRepository = dataRepository;
        this.reportDefinitionRepository = reportDefinitionRepository;
    }

    @Override
    public Data createData(DataDto dataDto) {
        Data data = new Data();
        data.setName(dataDto.getName());
        data.setTotalSales(dataDto.getTotalSales());
        data.setSalesPeriod(dataDto.getSalesPeriod());
        data.setExperienceMultiplier(dataDto.getExperienceMultiplier());
        return dataRepository.save(data);
    }

    @Override
    public Data addReportDefinition(ReportDto reportDto) {
        Data data = getDataById(reportDto.getId());
        List<ReportDefinition> reportDefinitionList = addDefinitionOfReport(reportDto.getReportDefinitionList());
        data.setReportDefinitionList(reportDefinitionList);

        Double score = calculateScore(reportDto.getReportDefinitionList(), data);
        data.setScore(score);

        return dataRepository.save(data);
    }

    private Double calculateScore(List<ReportDefinition> reportDefinitionList, Data data) {
        Optional<ReportDefinition> currentReportDefinition;
        double score = 0;
        for (int i = 0; i < reportDefinitionList.size(); i++) {
            currentReportDefinition = reportDefinitionRepository.findById(reportDefinitionList.get(i).getId());

            if (currentReportDefinition.isEmpty()) {
                throw new ReportDefinitionNotFoundException("This report definition does not exist!");
            }
            if (Objects.equals(true, reportDefinitionList.get(i).getUseExprienceMultiplier())) {
                score = data.getTotalSales() / (data.getSalesPeriod() * data.getExperienceMultiplier());
            }

            if (Objects.equals(false, reportDefinitionList.get(i).getUseExprienceMultiplier())) {
                score = data.getTotalSales() / (data.getSalesPeriod() * 1.0);
            }
        }
        return score;
    }

    @Override
    public List<Data> getAll() {
        return dataRepository.findAll();
    }

    private List<ReportDefinition> addDefinitionOfReport(List<ReportDefinition> reportDefinitionList) {
        Optional<ReportDefinition> currentReportDefinition;
        for (int i = 0; i < reportDefinitionList.size(); i++) {
            currentReportDefinition = reportDefinitionRepository.findById(reportDefinitionList.get(i).getId());

            if (currentReportDefinition.isEmpty()) {
                throw new ReportDefinitionNotFoundException("This report definition does not exist!");
            }
            reportDefinitionList.set(i, currentReportDefinition.get());
        }

        return reportDefinitionList;
    }

    private Data getDataById(Integer id) {
        Data data = new Data();
        data.setId(id);
        Example<Data> example = Example.of(data);
        return dataRepository.findOne(example).orElseThrow(() -> new DataNotFoundException(MessageFormat.format("Data with id:{0} not found!", id)));
    }
}
