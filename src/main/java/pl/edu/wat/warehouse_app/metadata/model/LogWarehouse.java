package pl.edu.wat.warehouse_app.metadata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogWarehouse {

    @Id
    @GeneratedValue
    private Long id;

    private String tableName;

    private Timestamp importTime;

    private int recordCount;

}
