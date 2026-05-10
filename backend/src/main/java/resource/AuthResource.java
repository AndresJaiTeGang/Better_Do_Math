package resource;

import dto.AuthResponseDTO;
import dto.LoginDTO;
import dto.RegisterDTO;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import model.Usuario;
import security.JwtUtil;
import service.AuthService;
import service.UsuarioService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("mathPU");

    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {

        EntityManager em = emf.createEntityManager();

        AuthService service
                = new AuthService(em);

        Usuario user
                = service.login(dto.username,
                        dto.password);

        if (user == null) {
            return Response.status(401).build();
        }

        String token
                = JwtUtil.generate(user.getUsername());

        return Response.ok(token).build();
    }

    @POST
    @Path("/register")
    public Response register(RegisterDTO dto) {

        EntityManager em
                = emf.createEntityManager();

        UsuarioService service
                = new UsuarioService(em);

        Usuario u
                = service.register(
                        dto.username,
                        dto.password);

        if (u == null) {
            return Response.status(409)
                    .entity("Usuario existente")
                    .build();
        }

        return Response.ok(
                new AuthResponseDTO(
                        token,
                        user.getRol().name())
        ).build();
    }
}
