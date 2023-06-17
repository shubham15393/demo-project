package shubham.csvapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shubham.csvapi.dto.DataDto;
import shubham.csvapi.service.ICsvDataService;

import java.util.List;


@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    ICsvDataService csvDataService;
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> uploadDataFromCsv(@RequestParam("file") MultipartFile file) {

        csvDataService.uploadData(file);
        return ResponseEntity.status(HttpStatus.OK).body("file uploaded successfully");
    }
    @GetMapping
    public List<DataDto> getAllData(){
        return csvDataService.fetchAllData();
    }

    @GetMapping("/{code}")
    public DataDto getDataById(@PathVariable String code){
        return csvDataService.fetchByCode(code);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
         csvDataService.deleteAllData();
         return ResponseEntity.status(HttpStatus.OK).body("all entries deleted successfully");
    }
}
