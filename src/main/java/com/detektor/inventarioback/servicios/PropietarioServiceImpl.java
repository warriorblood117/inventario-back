package com.detektor.inventarioback.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.detektor.inventarioback.dao.entidades.Propietario;
import com.detektor.inventarioback.dao.repositorios.PropietarioRepository;

public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public List<Propietario> getAll() {
        return propietarioRepository.findAll();
    }

    @Override
    public Optional<Boolean> save(Propietario propietario) {
        try {
            propietarioRepository.save(propietario);
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

}
