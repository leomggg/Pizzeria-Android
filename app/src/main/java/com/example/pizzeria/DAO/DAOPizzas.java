package com.example.pizzeria.DAO;

import com.example.pizzeria.POJO.Pizza;

import java.util.ArrayList;
import java.util.List;

public class DAOPizzas {
    private List<Pizza> listaPizzas;
    private static DAOPizzas dao;

    public DAOPizzas() {
        listaPizzas = new ArrayList<>();

        listaPizzas.add(new Pizza("Margherita", "Queso", "Tomate", "Albahaca", 7.0));
        listaPizzas.add(new Pizza("Pepperoni", "Queso", "Tomate", "Pepperoni", 7.0));
        listaPizzas.add(new Pizza("Hawaiana", "Queso", "Jamón", "Piña", 7.0));
        listaPizzas.add(new Pizza("Cuatro Quesos", "Mozzarella", "Parmesano", "Roquefort", 7.0));
        listaPizzas.add(new Pizza("Vegetariana", "Pimiento", "Champiñón", "Cebolla", 7.0));
        listaPizzas.add(new Pizza("Barbacoa", "Pollo", "Cebolla", "Salsa BBQ", 7.0));
        listaPizzas.add(new Pizza("Mexicana", "Carne picada", "Maíz", "Pimiento", 7.0));
        listaPizzas.add(new Pizza("Caprichosa", "Jamón", "Champiñón", "Aceitunas", 7.0));
        listaPizzas.add(new Pizza("Marinera", "Atún", "Cebolla", "Aceitunas", 7.0));
        listaPizzas.add(new Pizza("Funghi", "Queso", "Champiñón", "Tomate", 7.0));
    }

    public static DAOPizzas getInstance() {
        if (dao == null) dao = new DAOPizzas();
        return dao;
    }

    public Pizza buscarPizzaNombre(String nombre) {
        for (Pizza p : listaPizzas) {
            if (p.getNombre().equals(nombre)) return p;
        }
        return null;
    }

    public List<Pizza> obtenerPizzas() {
        return listaPizzas;
    }

    public String getListaPizzas() {
        return listaPizzas.toString();
    }
}
