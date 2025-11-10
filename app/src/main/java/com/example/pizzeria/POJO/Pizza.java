package com.example.pizzeria.POJO;

public class Pizza {

    private String nombre;
    private String ingr1;
    private String ingr2;
    private String ingr3;

    public Pizza(String nombre, String ingr1, String ingr2, String ingr3) {
        this.nombre = nombre;
        this.ingr1 = ingr1;
        this.ingr2 = ingr2;
        this.ingr3 = ingr3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngr1() {
        return ingr1;
    }

    public void setIngr1(String ingr1) {
        this.ingr1 = ingr1;
    }

    public String getIngr2() {
        return ingr2;
    }

    public void setIngr2(String ingr2) {
        this.ingr2 = ingr2;
    }

    public String getIngr3() {
        return ingr3;
    }

    public void setIngr3(String ingr3) {
        this.ingr3 = ingr3;
    }

    @Override
    public String toString() {
        return  "Pizza " + nombre + '\n' + ingr1 + ", " + ingr2 + ", " + ingr3;
    }
}
