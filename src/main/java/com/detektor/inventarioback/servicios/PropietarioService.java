package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;
import com.detektor.inventarioback.dao.entidades.Propietario;

public interface PropietarioService {

    List<Propietario> getAll();
    
    Optional<Boolean> save(Propietario propietario);

    Optional<Boolean> delete(Long id);

    Optional<Propietario> findById(Long id);

    Optional<Boolean> update (Long id, Propietario propietario);

}
