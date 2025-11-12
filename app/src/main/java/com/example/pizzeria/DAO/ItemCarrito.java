package com.example.pizzeria.DAO;

import com.example.pizzeria.POJO.Pizza;

public class ItemCarrito {

    private Pizza pizza;
    private int cant;

    public ItemCarrito(Pizza pizza, int cant) {
        this.pizza = pizza;
        this.cant = cant;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public boolean estaPizza(Pizza pizza2) {
        return this.pizza.getNombre().equals(pizza2.getNombre());
    }

    @Override
    public String toString() {
        double precioUnitario = this.pizza.getPrecio();
        double subtotal = precioUnitario * this.cant;

        return this.cant + "x " + this.pizza.getNombre() + " - Subtotal: " + String.format("%.2f", subtotal) + "€";
    }
}
