package com.libros.service;

import com.libros.dto.LoginRequest;
import com.libros.dto.TokenResponse;
import com.libros.entity.Usuario;
import com.libros.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class AuthService {

    private final TokenService _tokenService;
    private final UsuarioRepository _userRepository;
    private final long timeToLive;

    public AuthService(TokenService tokenService,
                       UsuarioRepository usuarioRepository,
                       @ConfigProperty(name = "smallrye.jwt.new-token.lifespan") long timeToLive)
    {
        this._tokenService = tokenService;
        this._userRepository = usuarioRepository;
        this.timeToLive = timeToLive;
    }

    public TokenResponse login(LoginRequest userData)
    {
        Usuario userFound = _userRepository.buscarPorUsername(userData.username);

        if (userFound == null)
            return null;

        boolean passwordMatching = BcryptUtil.matches(userData.password, userFound.passwordHash);

        if (!passwordMatching)
            return null;

        return new TokenResponse(_tokenService.generarToken(userFound.username, userFound.roles),
                                "Bearer",
                                 timeToLive);
    }
}
