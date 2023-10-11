package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.detektor.inventarioback.dao.entidades.Propietario;


@Service
public interface PropietarioService {

    List<Propietario> getAll();
    
    Optional<Boolean> save(Propietario propietario);

    Optional<Boolean> delete(Long id);

    Optional<Propietario> findById(Long id);

}
