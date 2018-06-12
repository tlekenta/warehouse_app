package pl.edu.wat.warehouse_app.metadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.metadata.model.LogImport;

import java.sql.Timestamp;

@Repository
public interface LogImportRepository extends JpaRepository<LogImport, Long> {

    @Deprecated
    LogImport findTopByTableNameAndSuccessIsTrue(String tableName);

    @Query("select max(li.importTime) from LogImport li where tableName = :pTableName")
    Timestamp findLastTimestampForTable(@Param("pTableName") String pTableName);
}
