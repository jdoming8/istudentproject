package com.example.istudentproject.model;

import java.util.*;


public class ProjectModel {
    private String nombre;
    private ArrayList<TaskModel> llistaTareas;

    public ProjectModel(String nombre) {
        this.nombre = nombre;
        llistaTareas = new ArrayList<TaskModel>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<TaskModel> getLlistaTareas() {
        return this.llistaTareas;
    }

    public void addTarea(String nombre, boolean recurrente, String fechaLimite, int diasRecurrente) {
        TaskModel tarea = new TaskModel(nombre, recurrente, fechaLimite, diasRecurrente);
        llistaTareas.add(tarea);
    }

    public String getNombreTarea(TaskModel tarea) {
        return tarea.getNombre();
    }

    public void setNombreTarea(TaskModel tarea, String nombre) {
        tarea.setNombre(nombre);
    }

    public boolean getRecurrente(TaskModel tarea){
        return tarea.getRecurrente();
    }

    public void setRecurrente(TaskModel tarea, boolean recurrente) {
        tarea.setRecurrente(recurrente);
    }

    public String getFechaLimite(TaskModel tarea) {
        return tarea.getFechaLimite();
    }

    public void setFechaLimite(TaskModel tarea, String fechaLimite) {
        tarea.setFechaLimite(fechaLimite);
    }

    public int getDiasRecurrentes(TaskModel tarea) {
        return tarea.getDiasRecurrente();
    }

    public void setDiasRecurrentes(TaskModel tarea, int diasRecurrentes) {
        tarea.setDiasRecurrente(diasRecurrentes);
    }


}
