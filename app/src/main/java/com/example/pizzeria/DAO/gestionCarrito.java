package com.example.pizzeria.DAO;

import com.example.pizzeria.POJO.Pizza;

import java.util.ArrayList;
import java.util.List;

public class gestionCarrito {

    private DAOPizzas dao = new DAOPizzas();
    private List<Pizza> carrito = new ArrayList<Pizza>();

    public gestionCarrito(List<Pizza> carrito) {
        this.carrito = carrito;
    }

    private List<Pizza> getCarrito() {
        return carrito;
    }

   public void anadirPizzaCarrito(String pizza, int cantidad) {
       Pizza objetoPizza = dao.buscarPizzaNombre(pizza);
       for (int i = 1; i < cantidad; i++) {
           carrito.add(objetoPizza);
       }
       getCarrito();
   }

   public void removePizzaCarrito(String pizza, int cantidad) {
       Pizza objetoPizza = dao.buscarPizzaNombre(pizza);
       for (int i = 1; i < cantidad; i++) {
           carrito.remove(objetoPizza);
       }
       getCarrito();
   }
}
