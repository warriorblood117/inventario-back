package com.detektor.inventarioback.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.detektor.inventarioback.dao.entidades.Vehiculo;
import com.detektor.inventarioback.servicios.VehiculoServiceImpl;

@RestController
@RequestMapping("/inventario-back/")
public class VehiculoControlador {

    @Autowired
    private VehiculoServiceImpl vehiculoServiceImpl;
    
    @GetMapping("/vehiculos")
    public List<Vehiculo> getAll(){
        return vehiculoServiceImpl.getAll();
    }
}
