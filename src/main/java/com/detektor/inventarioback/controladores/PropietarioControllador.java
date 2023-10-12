package com.detektor.inventarioback.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.detektor.inventarioback.dao.entidades.Propietario;
import com.detektor.inventarioback.dao.entidades.Vehiculo;
import com.detektor.inventarioback.servicios.PropietarioServiceImpl;
import com.detektor.inventarioback.servicios.VehiculoServiceImpl;

@RestController
@RequestMapping("/inventario-back/")
@CrossOrigin("http://localhost:4200")
public class PropietarioControllador {

    @Autowired
    private PropietarioServiceImpl propietarioServiceImpl;

    @Autowired
    private VehiculoServiceImpl vehiculoServiceImpl;

    @GetMapping("propietarios")
    public List<Propietario> getAll() {
        return propietarioServiceImpl.getAll();
    }

    @GetMapping("propietario/{id}")
    public ResponseEntity<?> getPropietario(@PathVariable Long id) {

        try {
            // Busca el propietario con el ID especificado
            Propietario propietarioEncontrado = propietarioServiceImpl.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(propietarioEncontrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("save-propietario")
    public ResponseEntity<?> savePropietario(@RequestBody Propietario propietario) {
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
    public ResponseEntity<?> editPropietario(@PathVariable Long id, @RequestBody Propietario propietario) {
        Map<String, String> message;
        try {
            propietarioServiceImpl.update(id, propietario);
            message = new HashMap<>();
            message.put("message", "Propietario editado exitosamente");    
            return ResponseEntity.status(HttpStatus.CREATED).body(message);      
        } catch (Exception e) {
            message = new HashMap<>();
            message.put("message", "No se pudo editar el propietario");
            message.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
