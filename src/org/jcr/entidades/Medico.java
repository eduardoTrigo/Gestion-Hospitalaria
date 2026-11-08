/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author Administrador
 */
public class Medico extends Persona{
    private Matricula matricula;
    private EspecialidadMedica especialidad;
    private Departamento departamento;
    private List<Cita> citas;

    public Medico(String nombre, String apellido, String dni,
                  LocalDate fechaNacimiento, TipoSangre tipoSangre,
                  String numeroMatricula,
                  EspecialidadMedica especialidad) {

        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);

        if (especialidad == null) {
            throw new IllegalArgumentException(
                    "La especialidad médica no puede ser nula");
        }

        this.matricula = new Matricula(numeroMatricula);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {

        if (departamento != null
                && departamento.getEspecialidad() != this.especialidad) {

            throw new IllegalArgumentException(
                    "La especialidad del médico no coincide "
                    + "con la del departamento");
        }

        if (this.departamento != departamento) {

            Departamento anterior = this.departamento;
            this.departamento = departamento;

            if (anterior != null) {
                anterior.desvincularMedico(this);
            }

            if (departamento != null) {
                departamento.vincularMedico(this);
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
