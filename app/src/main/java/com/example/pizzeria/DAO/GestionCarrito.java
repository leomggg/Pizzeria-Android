package com.example.pizzeria.DAO;

import com.example.pizzeria.POJO.Pizza;

import java.util.ArrayList;
import java.util.List;

public class GestionCarrito {

    private static GestionCarrito gestionCarrito;
    private DAOPizzas dao = new DAOPizzas();
    private List<ItemCarrito> itemsCarrito = new ArrayList<>();

    public GestionCarrito() {

    }

    public static GestionCarrito getInstance() {
        if (gestionCarrito == null) gestionCarrito = new GestionCarrito();
        return gestionCarrito;
    }

    public List<ItemCarrito> getItemCarrito() {
        return itemsCarrito;
    }

    private ItemCarrito buscarItemExistente(Pizza pizza) {
        String pizzaNombre = pizza.getNombre();

        for (ItemCarrito item : itemsCarrito) {
            if (item.getPizza().getNombre() == pizzaNombre) return item;
        }

        return null;
    }

   public void anadirPizzaCarrito(String pizza, int cantidad) {
       Pizza objetoPizza = dao.buscarPizzaNombre(pizza);

       if (objetoPizza == null || cantidad <= 0) return;

       ItemCarrito itemExist = buscarItemExistente(objetoPizza);

       if (itemExist != null) itemExist.setCant((itemExist.getCant() + cantidad));
       else itemsCarrito.add(new ItemCarrito(objetoPizza, cantidad));
   }

   public void removePizzaCarrito(String pizza, int cantidad) {
       Pizza objetoPizza = dao.buscarPizzaNombre(pizza);

       if (objetoPizza == null || cantidad <= 0) return;

       ItemCarrito itemExist = buscarItemExistente(objetoPizza);

       if (itemExist != null) {
           int nuevaCant = itemExist.getCant() - cantidad;

           if (nuevaCant > 0) itemExist.setCant(nuevaCant);
           else {
               for (int i = 0; i < itemsCarrito.size(); i++) {
                   if (itemsCarrito.get(i).getPizza().getNombre() == objetoPizza.getNombre()) {
                       itemsCarrito.remove(i);
                       return;
                   }
               }
           }
       }
   }
}
