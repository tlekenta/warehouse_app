package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Client;

public interface ZrodloPos_ClientRepository extends JpaRepository<ZrodloPos_Client, Long> {
}
