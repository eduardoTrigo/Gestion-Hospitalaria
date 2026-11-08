/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;

/**
 *
 * @author Administrador
 */
public class Departamento {
    private String nombre;
    private EspecialidadMedica especialidad;
    private Hospital hospital;
    private List<Medico> medicos;
    private List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {

        this.nombre = Persona.validarTexto(nombre, "nombre");

        if (especialidad == null) {
            throw new IllegalArgumentException(
                    "La especialidad no puede ser nula");
        }

        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {

        if (this.hospital != hospital) {

            Hospital anterior = this.hospital;
            this.hospital = hospital;

            // Se elimina la relación con el hospital anterior.
            if (anterior != null) {
                anterior.desvincularDepartamento(this);
            }

            // Se registra en el nuevo hospital.
            if (hospital != null) {
                hospital.vincularDepartamento(this);
            }
        }
    }

    public void agregarMedico(Medico medico) {

        if (medico == null) {
            throw new IllegalArgumentException(
                    "El médico no puede ser nulo");
        }

        // Solo se aceptan médicos de la misma especialidad.
        if (medico.getEspecialidad() != this.especialidad) {
            throw new IllegalArgumentException(
                    "La especialidad del médico no coincide "
                    + "con la del departamento");
        }

        medico.setDepartamento(this);
    }

    // Mantienen sincronizada la relación Departamento-Medico.
    void vincularMedico(Medico medico) {
        if (!medicos.contains(medico)) {
            medicos.add(medico);
        }
    }

    void desvincularMedico(Medico medico) {
        medicos.remove(medico);
    }

    public Sala crearSala(String numero, String tipo) {

        // Composición: el departamento crea y posee sus salas.
        Sala sala = new Sala(numero, tipo, this);
        salas.add(sala);

        return sala;
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(salas);
    }
}
