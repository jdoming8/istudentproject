package com.example.istudentproject.model;

import java.io.Serializable;


public class TaskModel implements Serializable {
    private String nombre;
    private boolean recurrente;
    private String fechaLimite;
    private String diasRecurrente;

    public TaskModel() {

    }
    public TaskModel(String nombre, boolean recurrente, String fechaLimite, String diasRecurrente) {
        this.nombre = nombre;
        this.recurrente = recurrente;
        this.fechaLimite = fechaLimite;
        this.diasRecurrente = diasRecurrente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public boolean getRecurrente() {
        return this.recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public String getFechaLimite() {
        return this.fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getDiasRecurrente() {
        return this.diasRecurrente;
    }

    public void setDiasRecurrente(String diasRecurrente) {
        this.diasRecurrente = diasRecurrente;
    }

    public String getTaskModel() {
        String tasca = "Nombre: " + this.getNombre() + " Fecha: " + this.getFechaLimite();
        if (recurrente) {
            tasca += " Cada " + this.getDiasRecurrente() + " dias";
        }
        return tasca;
    }
}
