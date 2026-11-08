/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDateTime;
import org.jcr.enums.EstadoCita;

/**
 *
 * @author Administrador
 */
public class Cita {
    private Paciente paciente;
    private Medico medico;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double costo;
    private EstadoCita estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala,
                LocalDateTime fechaHora, double costo) {

        // Se comprueban las referencias obligatorias.
        if (paciente == null || medico == null
                || sala == null || fechaHora == null) {

            throw new IllegalArgumentException(
                    "Paciente, médico, sala y fecha son obligatorios");
        }

        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;

        // Toda cita nueva comienza programada y sin observaciones.
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones = "";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setEstado(EstadoCita estado) {

        if (estado == null) {
            throw new IllegalArgumentException(
                    "El estado de la cita no puede ser nulo");
        }

        this.estado = estado;
    }

    public void setObservaciones(String observaciones) {

        // Las observaciones nulas se guardan como texto vacío.
        this.observaciones = observaciones == null
                ? "" : observaciones;
    }
}
