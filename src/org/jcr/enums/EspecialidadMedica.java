/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.enums;

/**
 *
 * @author Administrador
 */
public enum EspecialidadMedica {
    CARDIOLOGIA("Cardiología"),
    NEUROLOGIA("Neurología"),
    PEDIATRIA("Pediatría"),
    TRAUMATOLOGIA("Traumatología"),
    GINECOLOGIA("Ginecología"),
    UROLOGIA("Urología"),
    OFTALMOLOGIA("Oftalmología"),
    DERMATOLOGIA("Dermatología"),
    PSIQUIATRIA("Psiquiatría"),
    MEDICINA_GENERAL("Medicina General"),
    CIRUGIA_GENERAL("Cirugía General"),
    ANESTESIOLOGIA("Anestesiología");

    private final String descripcion;

    EspecialidadMedica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
