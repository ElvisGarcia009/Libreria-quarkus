package com.libros.resource;

import com.libros.dto.TokenResponseDTO;
import com.libros.dto.UsuarioLoginDTO;
import com.libros.exception.CredencialesInvalidasException;
import com.libros.service.AuthService;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("api/v1/auth")
public class AuthResource {

    private final AuthService _authService;

    public AuthResource(AuthService authService)
    {
        this._authService = authService;
    }

    @POST
    @Path("/login")
    public TokenResponseDTO login(@Valid UsuarioLoginDTO userData)
    {
        TokenResponseDTO tokenGenerated = _authService.login(userData);
        return tokenGenerated;
    }
}
