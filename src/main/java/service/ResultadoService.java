/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UsuarioDAO;
import dto.ResultadoDTO;
import jakarta.persistence.EntityManager;
import model.Resultado;
import model.Usuario;

import java.util.List;

public class ResultadoService {

    private EntityManager em;

    public ResultadoService(EntityManager em) {
        this.em = em;
    }

    public boolean verificar(ResultadoDTO dto,
                              String username) {

        UsuarioDAO usuarioDAO =
                new UsuarioDAO(em);

        Usuario u =
                usuarioDAO.findByUsername(username);

        boolean correcto =
                (dto.numeroA + dto.numeroB)
                        == dto.respuesta;

        Resultado r = new Resultado();

        r.setNumeroA(dto.numeroA);
        r.setNumeroB(dto.numeroB);
        r.setRespuestaUsuario(dto.respuesta);
        r.setCorrecto(correcto);
        r.setUsuario(u);

        em.getTransaction().begin();

        em.persist(r);

        em.getTransaction().commit();

        return correcto;
    }

    public double score(Usuario u) {

        List<Resultado> lista =
                em.createQuery(
                        "SELECT r FROM Resultado r WHERE r.usuario = :u",
                        Resultado.class)
                        .setParameter("u", u)
                        .getResultList();

        if(lista.isEmpty())
            return 0;

        long correctos =
                lista.stream()
                        .filter(Resultado::isCorrecto)
                        .count();

        return (correctos * 100.0)
                / lista.size();
    }
}