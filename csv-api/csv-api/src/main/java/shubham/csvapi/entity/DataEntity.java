package shubham.csvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "source")
    String source;

    @Column(name = "code_list_code")
    String codeListCode;

    @Column(name="code")
    String code;

    @Column(name="display_value")
    String displayValue;

    @Column(name = "long_description")
    String longDescription;

    @Column(name ="from_date")
    Date fromDate;

    @Column(name = "to_date")
    Date toDate;

    @Column(name = "sorting_priority")
    Integer sortingPriority;
}
