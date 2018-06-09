package pl.edu.wat.warehouse_app.zrodlo_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_system.model.ZrodloSystem_Klient;

public interface ZrodloPos_KlientRepository extends JpaRepository<ZrodloSystem_Klient, Long> {
}
