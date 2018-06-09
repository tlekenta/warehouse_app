package pl.edu.wat.warehouse_app.zrodlo_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_system.model.ZrodloSystem_Sklep;

public interface ZrodloPos_SklepRepository extends JpaRepository<ZrodloSystem_Sklep, Long> {
}
