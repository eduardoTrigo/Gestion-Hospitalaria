/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Hospital {

    private String nombre;
    private String direccion;
    private String telefono;
    private List<Departamento> departamentos;
    private List<Paciente> pacientes;

    public Hospital(String nombre, String direccion, String telefono) {

        this.nombre = Persona.validarTexto(nombre, "nombre");
        this.direccion = Persona.validarTexto(direccion, "dirección");
        this.telefono = Persona.validarTexto(telefono, "teléfono");

        this.departamentos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void agregarDepartamento(Departamento departamento) {
        if (departamento == null) {
            throw new IllegalArgumentException(
                    "El departamento no puede ser nulo");
        }

        // El departamento actualiza ambas puntas de la relación.
        departamento.setHospital(this);
    }

    public void agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException(
                    "El paciente no puede ser nulo");
        }

        paciente.setHospital(this);
    }

    // Métodos internos utilizados para sincronizar las listas.
    void vincularDepartamento(Departamento departamento) {
        if (!departamentos.contains(departamento)) {
            departamentos.add(departamento);
        }
    }

    void desvincularDepartamento(Departamento departamento) {
        departamentos.remove(departamento);
    }

    void vincularPaciente(Paciente paciente) {
        if (!pacientes.contains(paciente)) {
            pacientes.add(paciente);
        }
    }

    void desvincularPaciente(Paciente paciente) {
        pacientes.remove(paciente);
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }
}
