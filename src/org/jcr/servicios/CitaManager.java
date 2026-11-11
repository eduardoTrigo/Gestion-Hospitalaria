/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

/**
 *
 * @author Administrador
 */
public class CitaManager implements CitaService {

    private static final int HORAS_ENTRE_CITAS = 2;

    private List<Cita> citas;

    public CitaManager() {
        this.citas = new ArrayList<>();
    }

    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) throws CitaException {

        // Primero validamos todos los datos de la solicitud.
        if (paciente == null || medico == null || sala == null) {
            throw new CitaException(
                    "El paciente, médico y sala son obligatorios");
        }

        if (fechaHora == null || !fechaHora.isAfter(LocalDateTime.now())) {
            throw new CitaException(
                    "La fecha de la cita debe ser futura");
        }

        if (!(costo > 0)) {
            throw new CitaException(
                    "El costo de la cita debe ser mayor a cero");
        }

        if (medico.getEspecialidad()
                != sala.getDepartamento().getEspecialidad()) {
            throw new CitaException(
                    "La especialidad del médico no coincide "
                    + "con la del departamento de la sala");
        }

        // Comprobamos disponibilidad del médico y de la sala.
        for (Cita citaExistente : citas) {

            if (citaExistente.getMedico() == medico
                    && haySuperposicion(fechaHora,
                            citaExistente.getFechaHora())) {

                throw new CitaException(
                        "El médico tiene otra cita a menos de 2 horas");
            }

            if (citaExistente.getSala() == sala
                    && haySuperposicion(fechaHora,
                            citaExistente.getFechaHora())) {

                throw new CitaException(
                        "La sala tiene otra cita a menos de 2 horas");
            }
        }

        // Solo registramos la cita después de pasar las validaciones.
        Cita nuevaCita = new Cita(
                paciente, medico, sala, fechaHora, costo);

        citas.add(nuevaCita);
        paciente.addCita(nuevaCita);
        medico.addCita(nuevaCita);
        sala.addCita(nuevaCita);

        return nuevaCita;
    }

    // Comprueba las dos horas de separación en ambas direcciones.
    private boolean haySuperposicion(LocalDateTime nuevaFecha,
            LocalDateTime fechaExistente) {

        LocalDateTime limiteAnterior
                = fechaExistente.minusHours(HORAS_ENTRE_CITAS);

        LocalDateTime limitePosterior
                = fechaExistente.plusHours(HORAS_ENTRE_CITAS);

        return nuevaFecha.isAfter(limiteAnterior)
                && nuevaFecha.isBefore(limitePosterior);
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getPaciente() == paciente) {
                resultado.add(cita);
            }
        }

        return Collections.unmodifiableList(resultado);
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getMedico() == medico) {
                resultado.add(cita);
            }
        }

        return Collections.unmodifiableList(resultado);
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getSala() == sala) {
                resultado.add(cita);
            }
        }

        return Collections.unmodifiableList(resultado);
    }
}
