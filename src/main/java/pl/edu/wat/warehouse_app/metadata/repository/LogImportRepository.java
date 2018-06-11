package pl.edu.wat.warehouse_app.metadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.metadata.model.LogImport;

@Repository
public interface LogImportRepository extends JpaRepository<LogImport, Long> {

    public LogImport findTopByTableNameAndSuccessIsTrue(String tableName);
}
