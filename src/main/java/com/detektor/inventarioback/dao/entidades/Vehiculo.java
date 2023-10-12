package com.detektor.inventarioback.dao.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String vin;

    private String marca;

    private String linea;

    private String cilidrada;

    private String color;

    private String chasis;

    @Column(name = "tipo_de_vehiculo")
    private String tipoDeVehiculo;

    private String modelo;

    

    @Override
    public String toString() {
        return "ID: " + id + "Placa: " + placa + "Vin: " + vin;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    private Propietario propietario;

  
}
