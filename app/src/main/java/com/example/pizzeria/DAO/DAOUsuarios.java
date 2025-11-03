package com.example.pizzeria.DAO;

import android.content.Context;

import com.example.pizzeria.POJO.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios {
    private List<Usuario> usuarios;
    private static DAOUsuarios dao;

    public DAOUsuarios() {
        usuarios = new ArrayList<>();

        usuarios.add(new Usuario("juan", "juanitojuan3"));
        usuarios.add(new Usuario("user1", "1234"));
    }

    public static DAOUsuarios getInstance() {
        if (dao == null) dao = new DAOUsuarios();
        return dao;
    }

    public Usuario buscarUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre)) return u;
        }
        return null;
    }

    public boolean encontrarUsuario(String nombre) {
        boolean existe = false;
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre)) existe = true;
        }
        return existe;
    }

    public boolean agregarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuario.getNombre())) return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        Usuario usuarioActual = buscarUsuario(usuario.getNombre());
        usuarioActual.setPassword(usuario.getPassword());
        return true;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}
