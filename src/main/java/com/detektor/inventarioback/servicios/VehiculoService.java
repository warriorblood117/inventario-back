package com.detektor.inventarioback.servicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.detektor.inventarioback.dao.entidades.Vehiculo;


@Service
public interface VehiculoService extends JpaRepository<Vehiculo,Long> {
    
}
