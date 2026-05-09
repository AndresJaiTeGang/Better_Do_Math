/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UsuarioDAO;
import jakarta.persistence.EntityManager;
import model.Rol;
import model.Usuario;
import security.BCryptUtil;

public class UsuarioService {

    private EntityManager em;
    private UsuarioDAO usuarioDAO;

    public UsuarioService(EntityManager em) {

        this.em = em;
        usuarioDAO = new UsuarioDAO(em);
    }

    public Usuario register(String username,
                            String password) {

        Usuario exists =
                usuarioDAO.findByUsername(username);

        if(exists != null)
            return null;

        Usuario u = new Usuario();

        u.setUsername(username);

        u.setPassword(
                BCryptUtil.hash(password));

        u.setRol(Rol.ESTUDIANTE);

        em.getTransaction().begin();

        usuarioDAO.save(u);

        em.getTransaction().commit();

        return u;
    }
}
