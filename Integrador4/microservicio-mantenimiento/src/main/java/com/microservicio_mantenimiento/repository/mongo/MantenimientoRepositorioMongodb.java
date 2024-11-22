package com.microservicio_mantenimiento.repository.mongo;

import com.microservicio_mantenimiento.entity.mongo.Mantenimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MantenimientoRepositorioMongodb extends MongoRepository<Mantenimiento, String> {

    @Query("{ \"id_monopatin\": ?0, \"fin\": null }")
    Optional<Mantenimiento> findByIdMonopatin(Long idMonopatin);

}
