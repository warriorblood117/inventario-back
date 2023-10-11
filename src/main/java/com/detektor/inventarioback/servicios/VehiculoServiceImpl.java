package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.detektor.inventarioback.dao.entidades.Vehiculo;
import com.detektor.inventarioback.dao.repositorios.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Boolean> save(Vehiculo vehiculo) {
        try {
            vehiculoRepository.save(vehiculo);
            return Optional.of(true);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> delete(Long id) {
        try {
            vehiculoRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

}
