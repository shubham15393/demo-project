package shubham.csvapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shubham.csvapi.dto.DataDto;
import shubham.csvapi.entity.DataEntity;
import shubham.csvapi.repository.DataRepository;
import shubham.csvapi.service.ICsvDataService;
import shubham.csvapi.util.Helper;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CsvDataServiceImpl implements ICsvDataService {


    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private ObjectMapper mapper ;
    @Autowired
    DataRepository dataRepository;

    @Override
    public void uploadData(MultipartFile file) {
        BufferedReader br = Helper.getBufferedReaderFromMultiPartFile(file);
        List<DataEntity> dataEntities;
        try {
            br.readLine(); //reading and removing header line from buffer
            dataEntities = br.lines().map(this::getEntityFromLines).toList();
            dataRepository.saveAll(dataEntities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DataDto> fetchAllData() {
        List<DataEntity> entities = (List<DataEntity>)dataRepository.findAll();
        return entities.stream().map(entity-> mapper.convertValue(entity,DataDto.class)).toList();
    }

    @Override
    public DataDto fetchByCode(String code) {
        DataEntity dataEntity = dataRepository.findByCode(code);
        return mapper.convertValue(dataEntity,DataDto.class);
    }

    @Override
    public void deleteAllData() {
        dataRepository.deleteAll();
    }

    private DataEntity getEntityFromLines(String line) {
        String[] arr = line.split("\",");
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i].replaceAll("\"","");
        if (arr.length != 8)
            return null;

        Date fromDate=null, toDate=null;
        try {
            fromDate = dateFormat.parse(arr[5]);
            toDate = dateFormat.parse(arr[6]);
        } catch (ParseException e) {
            System.out.println("data parse error");
        }

        return DataEntity.builder()
                .source(arr[0])
                .codeListCode(arr[1])
                .code(arr[2])
                .displayValue(arr[3])
                .longDescription(arr[4])
                .fromDate(fromDate)
                .toDate(toDate)
                .sortingPriority(arr[7].length()>0?Integer.valueOf(arr[7]):null)
                .build();
    }
}
