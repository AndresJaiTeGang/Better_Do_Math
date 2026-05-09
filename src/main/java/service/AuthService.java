/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UsuarioDAO;
import jakarta.persistence.EntityManager;
import model.Usuario;
import security.BCryptUtil;

public class AuthService {

    private UsuarioDAO usuarioDAO;

    public AuthService(EntityManager em) {
        usuarioDAO = new UsuarioDAO(em);
    }

    public Usuario login(String username,
                         String password) {

        Usuario u = usuarioDAO.findByUsername(username);

        if(u == null)
            return null;

        boolean valid =
                BCryptUtil.verify(password,
                                  u.getPassword());

        return valid ? u : null;
    }
}