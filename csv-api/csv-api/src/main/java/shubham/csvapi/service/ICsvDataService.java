package shubham.csvapi.service;

import org.springframework.web.multipart.MultipartFile;
import shubham.csvapi.dto.DataDto;

import java.util.List;

public interface ICsvDataService {
    void uploadData(MultipartFile file);

    List<DataDto> fetchAllData();

    DataDto fetchByCode(String code);

    void deleteAllData();
}
