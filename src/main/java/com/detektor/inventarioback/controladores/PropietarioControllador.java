package com.detektor.inventarioback.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/propietarios")
    public List<Propietario> getAll() {
        return propietarioServiceImpl.getAll();
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
}
