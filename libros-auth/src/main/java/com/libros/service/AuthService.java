package com.libros.service;

import com.libros.dto.TokenResponseDTO;
import com.libros.dto.UsuarioLoginDTO;
import com.libros.entity.Usuario;
import com.libros.exception.CredencialesInvalidasException;
import com.libros.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AuthService {

    private final UsuarioRepository _usuarioRepository;
    private final TokenGenService _tokenGenService;
    private final long ttl;

    public AuthService(UsuarioRepository usuarioRepository,
                       TokenGenService tokenGenService,
                       @ConfigProperty(name = "smallrye.jwt.new-token.lifespan") long ttl)
    {
        this._usuarioRepository = usuarioRepository;
        this._tokenGenService = tokenGenService;
        this.ttl = ttl;
    }

    public TokenResponseDTO login(UsuarioLoginDTO userData)
    {
        Usuario userFound = _usuarioRepository.findByUsername(userData.username);
        if (userFound == null) throw new CredencialesInvalidasException();

        boolean isThePasswordRight = BcryptUtil.matches(userData.password, userFound.passwordHashed);
        if (!isThePasswordRight) throw new CredencialesInvalidasException();

        String tokenGenerated = _tokenGenService.generarToken(userFound.username, userFound.roles);

        return new TokenResponseDTO(tokenGenerated, "Bearer", ttl);
    }
}
