/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource;

import dto.LoginDTO;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import model.Usuario;
import security.JwtUtil;
import service.AuthService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mathPU");

    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {

        EntityManager em = emf.createEntityManager();

        AuthService service =
                new AuthService(em);

        Usuario user =
                service.login(dto.username,
                              dto.password);

        if(user == null)
            return Response.status(401).build();

        String token =
                JwtUtil.generate(user.getUsername());

        return Response.ok(token).build();
    }
}