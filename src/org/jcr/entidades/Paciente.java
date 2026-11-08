/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author Administrador
 */
public class Paciente extends Persona{

    private HistoriaClinica historiaClinica;
    private String telefono;
    private String direccion;
    private Hospital hospital;
    private List<Cita> citas;

    public Paciente(String nombre, String apellido, String dni,
            LocalDate fechaNacimiento, TipoSangre tipoSangre,
            String telefono, String direccion) {

        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);

        this.telefono = validarTexto(telefono, "teléfono");
        this.direccion = validarTexto(direccion, "dirección");

        this.historiaClinica = new HistoriaClinica(this);
        this.citas = new ArrayList<>();
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Hospital getHospital() {
        return hospital;
    }

    //mantiene una relación bidireccional paciente - hospital
    public void setHospital(Hospital hospital) {

        if (this.hospital != hospital) {

            Hospital anterior = this.hospital;
            this.hospital = hospital;

            if (anterior != null) {
                anterior.desvincularPaciente(this);
            }

            if (hospital != null) {
                hospital.vincularPaciente(this);
            }
        }
    }

    public void addCita(Cita cita) {
        if (cita != null && !citas.contains(cita)) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
}
