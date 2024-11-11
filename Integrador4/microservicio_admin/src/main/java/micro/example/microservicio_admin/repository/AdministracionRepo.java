package micro.example.microservicio_admin.repository;

import micro.example.microservicio_admin.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministracionRepo extends JpaRepository<Administrador, Long> {
}
