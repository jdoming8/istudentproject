package com.example.istudentproject.model;

public class TaskModel {
    private String nombre;
    private boolean recurrente;
    private String fechaLimite;
    private int diasRecurrente;

    public TaskModel(String nombre, boolean recurrente, String fechaLimite, int diasRecurrente) {
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

    public int getDiasRecurrente() {
        return this.diasRecurrente;
    }

    public void setDiasRecurrente(int diasRecurrente) {
        this.diasRecurrente = diasRecurrente;
    }
}
