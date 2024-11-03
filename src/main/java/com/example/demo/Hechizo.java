package com.example.demo;

public class Hechizo {
    private Long id;
    private String nombre;
    private int potencia; // entre 0 et 5
    private boolean magiaNegra;

    public Hechizo(Long id, String nombre, int potencia, boolean magiaNegra) {
        this.id = id;
        this.nombre = nombre;
        this.potencia = potencia;
        this.magiaNegra = magiaNegra;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPotencia() { return potencia; }
    public boolean isMagiaNegra() { return magiaNegra; }
}

