package pl.edu.wat.warehouse_app.metadata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogTable {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    private String tableName;

    private String throwingClass;

    private Timestamp logTime;

    @Enumerated(EnumType.STRING)
    private LogType type;

}
