/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO(EntityManager em) {
        super(em, Usuario.class);
    }

    public Usuario findByUsername(String username) {

        try {

            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :u",
                    Usuario.class)
                    .setParameter("u", username)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}