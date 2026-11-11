/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.servicios;

import java.time.LocalDateTime;
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
public interface CitaService {

    Cita programarCita(Paciente paciente, Medico medico,
            Sala sala, LocalDateTime fechaHora,
            double costo) throws CitaException;

    List<Cita> getCitasPorPaciente(Paciente paciente);

    List<Cita> getCitasPorMedico(Medico medico);

    List<Cita> getCitasPorSala(Sala sala);
}
