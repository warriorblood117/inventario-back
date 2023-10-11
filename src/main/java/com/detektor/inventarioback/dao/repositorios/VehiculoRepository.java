package com.detektor.inventarioback.dao.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.detektor.inventarioback.dao.entidades.Vehiculo;



@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    
}
