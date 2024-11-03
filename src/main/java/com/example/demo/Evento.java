package com.example.demo;

public class Evento {
    private Long id;
    private String nombre;
    private String profesor;
    private String lugar;
    private String fecha;

    public Evento(Long id, String nombre, String profesor, String lugar, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getProfesor() { return profesor; }
    public String getLugar() { return lugar; }
    public String getFecha() { return fecha; }
}
