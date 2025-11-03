package com.example.pizzeria.DAO;

import com.example.pizzeria.POJO.Pizza;

import java.util.ArrayList;
import java.util.List;

public class DAOPizzas {
    private List<Pizza> listaPizzas;
    private static DAOPizzas dao;

    public DAOPizzas() {
        listaPizzas = new ArrayList<>();

        listaPizzas.add(new Pizza("Margherita", "Queso", "Tomate", "Albahaca"));
        listaPizzas.add(new Pizza("Pepperoni", "Queso", "Tomate", "Pepperoni"));
        listaPizzas.add(new Pizza("Hawaiana", "Queso", "Jamón", "Piña"));
        listaPizzas.add(new Pizza("Cuatro Quesos", "Mozzarella", "Parmesano", "Roquefort"));
        listaPizzas.add(new Pizza("Vegetariana", "Pimiento", "Champiñón", "Cebolla"));
        listaPizzas.add(new Pizza("Barbacoa", "Pollo", "Cebolla", "Salsa BBQ"));
        listaPizzas.add(new Pizza("Mexicana", "Carne picada", "Maíz", "Pimiento"));
        listaPizzas.add(new Pizza("Caprichosa", "Jamón", "Champiñón", "Aceitunas"));
        listaPizzas.add(new Pizza("Marinera", "Atún", "Cebolla", "Aceitunas"));
        listaPizzas.add(new Pizza("Funghi", "Queso", "Champiñón", "Tomate"));
    }

    public static DAOPizzas getInstance() {
        if (dao == null) dao = new DAOPizzas();
        return dao;
    }

    public Pizza buscarPizza(String nombre) {
        for (Pizza p : listaPizzas) {
            if (p.getNombre().equals(p.getNombre())) return p;
        }
        return null;
    }

    public List<Pizza> obtenerPizzas() {
        return listaPizzas;
    }
}
