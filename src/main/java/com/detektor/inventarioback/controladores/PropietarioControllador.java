package com.detektor.inventarioback.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.detektor.inventarioback.dao.entidades.Propietario;
import com.detektor.inventarioback.servicios.PropietarioServiceImpl;
import com.detektor.inventarioback.servicios.VehiculoServiceImpl;

@RestController
@RequestMapping("/inventario-back/")
public class PropietarioControllador {

    @Autowired
    private PropietarioServiceImpl propietarioServiceImpl;

    @GetMapping("propietario/{id}")
    public ResponseEntity<?> getPropietario(@PathVariable Long id) {
        Map<String, Object> response;
        try {
            // Busca el propietario con el ID especificado
            Propietario propietarioEncontrado = propietarioServiceImpl.findById(id).get();

            // Crea la respuesta
            response = new HashMap<>();
            response.put("propietario", propietarioEncontrado);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("save-propietario")
    public ResponseEntity<?> savePropietario(@RequestPart Propietario propietario) {
        Map<String, String> message;
        try {
            propietarioServiceImpl.save(propietario);
            message = new HashMap<>();
            message.put("message", "Propietario guardado exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            message = new HashMap<>();
            message.put("message", "No se pudo guardar el propietario");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("edit-propietario/{id}")
    public ResponseEntity<?> editPropietario(@PathVariable Long id, @RequestPart Propietario propietario) {
        Map<String, String> message;
        try {
            // Busca el propietario existente con el ID especificado
            Optional<Propietario> propietarioOptional = propietarioServiceImpl.findById(id);

            if (propietarioOptional.isPresent()) {
                Propietario propietarioEncontrado = propietarioOptional.get();

                // Actualiza los campos del propietario existente con los nuevos valores
                propietarioEncontrado.setNombre(propietario.getNombre());
                propietarioEncontrado.setApellido(propietario.getApellido());
                propietarioEncontrado.setIdentificacion(propietario.getIdentificacion());
                propietarioEncontrado.setFechaNachimiento(propietario.getFechaNachimiento());
                propietarioEncontrado.setVehiculo(propietario.getVehiculo());

                // Guarda el propietario actualizado
                propietarioServiceImpl.save(propietarioEncontrado);

                message = new HashMap<>();
                message.put("message", "Propietario editado exitosamente");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } else {
                message = new HashMap<>();
                message.put("message", "No se pudo encontrar el propietario con el ID especificado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (Exception e) {
            message = new HashMap<>();
            message.put("message", "No se pudo editar el propietario");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
