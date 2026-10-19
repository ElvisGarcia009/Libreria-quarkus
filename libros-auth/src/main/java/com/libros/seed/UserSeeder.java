package com.libros.seed;

import com.libros.entity.Usuario;
import com.libros.repository.UsuarioRepository;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class UserSeeder {

    private final UsuarioRepository _usuarioRepository;

    private final String userReaderPassword;
    private final String userWriterPassword;

    private static final Set<String> readPermissions =  new HashSet<>(Set.of("lector"));
    private static final Set<String> writePermissions = new HashSet<>(Set.of("lector", "escritor"));

    public UserSeeder(UsuarioRepository usuarioRepository,
                      @ConfigProperty(name = "user.seeder.password.lector") String userReaderPassword,
                      @ConfigProperty(name = "user.seeder.password.escritor") String userWriterPassword)
    {
        this._usuarioRepository = usuarioRepository;
        this.userReaderPassword = userReaderPassword;
        this.userWriterPassword = userWriterPassword;
    }

    void seedFirstUsers(@Observes StartupEvent event)
    {
        //Creamos indices en la base datos
        _usuarioRepository.mongoCollection().createIndex(Indexes.ascending("username"),
                                                         new IndexOptions().unique(true));

        checkAndCreateUser("reader", userReaderPassword, readPermissions);
        checkAndCreateUser("writer", userWriterPassword, writePermissions);
    }

    private void checkAndCreateUser(String username, String password, Set<String> roles)
    {
        Usuario userData = _usuarioRepository.findByUsername(username);

        if (userData != null)
            return;

        userData = new Usuario();
        userData.username = username;
        userData.passwordHashed = BcryptUtil.bcryptHash(password);
        userData.roles = roles;
        _usuarioRepository.persist(userData);
    }
}
