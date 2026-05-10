package resource;

import dto.UsuarioDTO;

import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import model.Usuario;
import service.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mathPU");

    @GET
    @Path("/users")
    public Response users() {

        EntityManager em =
                emf.createEntityManager();

        AdminService service =
                new AdminService(em);

        List<UsuarioDTO> lista =
                service.getUsers()
                        .stream()
                        .map(u -> new UsuarioDTO(
                                u.getId(),
                                u.getUsername(),
                                u.getRol().name()
                        ))
                        .collect(Collectors.toList());

        return Response.ok(lista)
                .build();
    }

    @DELETE
    @Path("/users/{id}")
    public Response delete(
            @PathParam("id") Long id) {

        EntityManager em =
                emf.createEntityManager();

        AdminService service =
                new AdminService(em);

        service.delete(id);

        return Response.ok()
                .build();
    }

    @PUT
    @Path("/users/{id}/role/{role}")
    public Response role(
            @PathParam("id") Long id,
            @PathParam("role") String role) {

        EntityManager em =
                emf.createEntityManager();

        AdminService service =
                new AdminService(em);

        service.changeRole(id, role);

        return Response.ok()
                .build();
    }
}