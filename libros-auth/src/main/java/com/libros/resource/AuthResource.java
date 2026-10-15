package com.libros.resource;

import com.libros.dto.LoginRequest;
import com.libros.dto.TokenResponse;
import com.libros.service.AuthService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("api/v1/auth")
public class AuthResource {

    private final AuthService _authService;

    public AuthResource(AuthService authService) { this._authService = authService; }


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TokenResponse login(@Valid LoginRequest userData)
    {
        TokenResponse response = _authService.login(userData);

        if (response == null)
            throw new NotAuthorizedException("Credenciales inválidas", "Bearer");

        return response;
    }
}
