package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detektor.inventarioback.dao.entidades.Propietario;
import com.detektor.inventarioback.dao.entidades.Vehiculo;
import com.detektor.inventarioback.dao.repositorios.PropietarioRepository;
import com.detektor.inventarioback.dao.repositorios.VehiculoRepository;

@Service
public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Propietario> getAll() {
        return propietarioRepository.findAll();
    }

    @Override
    public Optional<Boolean> save(Propietario propietario) {
        try {
            // Guarda el propietario y sus vehículos
            propietarioRepository.save(propietario);
            List<Vehiculo> vehiculos = propietario.getVehiculo();

            if (vehiculos != null) {
                for (Vehiculo vehiculo : vehiculos) {
                    vehiculo.setPropietario(propietario);
                }

                vehiculoRepository.saveAll(vehiculos); // Guarda todos los vehículos
            }

            return Optional.of(true);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> delete(Long id) {

        try {
            propietarioRepository.deleteById(id);
            return Optional.of(true);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Propietario> findById(Long id) {
        return propietarioRepository.findById(id);
    }

    @Override
    public Optional<Boolean> update(Long id, Propietario propietario) {
        try {
            // Busca el propietario existente con el ID especificado
            Optional<Propietario> propietarioOptional = propietarioRepository.findById(id);

            if (propietarioOptional.isPresent()) {
                Propietario propietarioEncontrado = propietarioOptional.get();
                propietarioEncontrado.setNombre(propietario.getNombre());
                propietarioEncontrado.setApellido(propietario.getApellido());
                propietarioEncontrado.setIdentificacion(propietario.getIdentificacion());
                propietarioEncontrado.setFechaNacimiento(propietario.getFechaNacimiento());

                // Actualiza el propietario existente
                propietarioRepository.save(propietarioEncontrado);

                List<Vehiculo> vehiculos = propietario.getVehiculo();
                if (vehiculos != null) {
                    for (Vehiculo vehiculo : vehiculos) {
                        System.out.println(vehiculo);
                        vehiculo.setPropietario(propietarioEncontrado);
                    }

                    vehiculoRepository.saveAll(vehiculos); // Guarda todos los vehículos
                }

                return Optional.of(true);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
