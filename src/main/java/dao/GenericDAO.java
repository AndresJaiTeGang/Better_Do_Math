/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericDAO<T> {

    protected EntityManager em;
    private Class<T> clazz;

    public GenericDAO(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public T find(Long id) {
        return em.find(clazz, id);
    }

    public List<T> findAll() {
        return em.createQuery(
                "FROM " + clazz.getSimpleName(),
                clazz
        ).getResultList();
    }
}