package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_User;

public interface ZrodloPos_UserRepository extends JpaRepository<ZrodloPos_User, Long> {
}
