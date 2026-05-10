package service;

import jakarta.persistence.EntityManager;

import model.Rol;
import model.Usuario;

import java.util.List;

public class AdminService {

    private EntityManager em;

    public AdminService(EntityManager em) {

        this.em = em;
    }

    public List<Usuario> getUsers() {

        return em.createQuery(
                "SELECT u FROM Usuario u",
                Usuario.class)
                .getResultList();
    }

    public void delete(Long id) {

        Usuario u =
                em.find(Usuario.class, id);

        em.getTransaction().begin();

        em.remove(u);

        em.getTransaction().commit();
    }

    public void changeRole(
            Long id,
            String role) {

        Usuario u =
                em.find(Usuario.class, id);

        em.getTransaction().begin();

        u.setRol(
                Rol.valueOf(role));

        em.getTransaction().commit();
    }
}