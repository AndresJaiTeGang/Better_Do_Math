/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        String authHeader =
                requestContext.getHeaderString(
                        HttpHeaders.AUTHORIZATION);

        if(authHeader == null ||
           !authHeader.startsWith("Bearer ")) {

            requestContext.abortWith(
                    jakarta.ws.rs.core.Response
                            .status(401)
                            .build());

            return;
        }

        String token =
                authHeader.replace("Bearer ", "");

        try {

            Claims claims =
                    Jwts.parser()
                            .verifyWith(JwtUtil.KEY)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();

            String username =
                    claims.getSubject();

            requestContext.setProperty(
                    "username",
                    username);

        } catch (Exception e) {

            requestContext.abortWith(
                    jakarta.ws.rs.core.Response
                            .status(401)
                            .build());
        }
    }
}