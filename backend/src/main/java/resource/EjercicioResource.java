/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource;

import dto.EjercicioDTO;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import model.Usuario;
import service.AdaptativeEngineService;
import service.ResultadoService;

import java.util.Random;

@Path("/ejercicio")
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioResource {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mathPU");

    @GET
    public Response getExercise(
            @Context ContainerRequestContext ctx) {

        EntityManager em =
                emf.createEntityManager();

        String username =
                (String) ctx.getProperty("username");

        Usuario user =
                em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.username = :u",
                        Usuario.class)
                        .setParameter("u", username)
                        .getSingleResult();

        ResultadoService rs =
                new ResultadoService(em);

        double score =
                rs.score(user);

        AdaptativeEngineService engine =
                new AdaptativeEngineService();

        int range =
                engine.getRange(score);

        Random r = new Random();

        int a = r.nextInt(range);
        int b = r.nextInt(range);

        return Response.ok(
                new EjercicioDTO(a,b))
                .build();
    }
}