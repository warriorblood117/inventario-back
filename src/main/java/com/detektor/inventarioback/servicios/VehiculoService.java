package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;
import com.detektor.inventarioback.dao.entidades.Vehiculo;

public interface VehiculoService {
    List<Vehiculo> getAll();

    Optional<Boolean> save(Vehiculo propietario);

    Optional<Boolean> delete(Long id);

    Optional<Vehiculo> findById(Long id);
}
