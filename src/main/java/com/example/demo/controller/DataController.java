package com.example.demo.controller;

import com.example.demo.model.dto.DataDto;
import com.example.demo.model.dto.ReportDto;
import com.example.demo.model.entity.Data;
import com.example.demo.model.entity.ReportDefinition;
import com.example.demo.service.DataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    public static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_TYPE_CSV = "text/csv";

    private ModelMapper modelMapper = new ModelMapper();
    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/create")
    public ResponseEntity<DataDto> createData(@Valid @RequestBody DataDto dataDto) {
        Data savedData = dataService.createData(dataDto);
        return new ResponseEntity(modelMapper.map(savedData, DataDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<ReportDto> addReportDefinitionToData(@Valid @RequestBody ReportDto reportDto) {
        Data data = dataService.addReportDefinition(reportDto);
        return new ResponseEntity<>(modelMapper.map(data, ReportDto.class), HttpStatus.OK);
    }

    @GetMapping("/csv")
    public ResponseEntity exportReportResultsToCsv(HttpServletResponse response) throws IOException {
        List<Data> dataList = dataService.getAll();
        response.setContentType(CONTENT_TYPE_CSV);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = String.format("attachment; filename=reports_%s.csv", currentDateTime);
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] nameMapping = {"name", "score"};
        csvWriter.writeHeader(nameMapping);

        Integer takenPeriodLimit = 0;
        for (Data data : dataList) {

            for (ReportDefinition reportDefinition : data.getReportDefinitionList()) {
                takenPeriodLimit = reportDefinition.getPeriodLimit();
            }
            if (data.getSalesPeriod() <= takenPeriodLimit) {
                csvWriter.write(data, nameMapping);
            }
        }

        csvWriter.close();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
