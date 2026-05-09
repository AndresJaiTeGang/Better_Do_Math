/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource;

import dto.ResultadoDTO;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import service.ResultadoService;

@Path("/resultado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResultadoResource {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mathPU");

    @POST
    public Response save(ResultadoDTO dto,
                         @Context ContainerRequestContext ctx) {

        EntityManager em =
                emf.createEntityManager();

        String username =
                (String) ctx.getProperty("username");

        ResultadoService service =
                new ResultadoService(em);

        boolean correct =
                service.verificar(dto,
                                  username);

        return Response.ok(correct)
                .build();
    }
}
