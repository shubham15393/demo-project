package shubham.csvapi.dto;

import java.util.Date;

@lombok.Data
public class DataDto {
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private Date fromDate;
    private Date toDate;
    private Integer sortingPriority;
}
