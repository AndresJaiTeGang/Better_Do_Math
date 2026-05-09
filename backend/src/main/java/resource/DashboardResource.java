package resource;

import dto.DashboardDTO;

import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import model.Resultado;
import model.Usuario;

import java.util.List;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mathPU");

    @GET
    public Response getStats(
            @Context ContainerRequestContext ctx) {

        EntityManager em =
                emf.createEntityManager();

        String username =
                (String) ctx.getProperty("username");

        Usuario u =
                em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.username = :u",
                        Usuario.class)
                        .setParameter("u", username)
                        .getSingleResult();

        List<Resultado> lista =
                em.createQuery(
                        "SELECT r FROM Resultado r WHERE r.usuario = :u",
                        Resultado.class)
                        .setParameter("u", u)
                        .getResultList();

        long correctos =
                lista.stream()
                        .filter(Resultado::isCorrecto)
                        .count();

        long incorrectos =
                lista.size() - correctos;

        double porcentaje =
                lista.isEmpty()
                ? 0
                : (correctos * 100.0)
                  / lista.size();

        return Response.ok(
                new DashboardDTO(
                        correctos,
                        incorrectos,
                        porcentaje))
                .build();
    }
}