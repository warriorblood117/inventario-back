package com.detektor.inventarioback.dao.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.detektor.inventarioback.dao.entidades.Propietario;



@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Long>{
    
}
