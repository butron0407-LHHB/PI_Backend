package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.model.Usuarios;
import org.generation.muebleria.repository.UsuariosRepository;
import org.generation.muebleria.service.interfaces.IUsuariosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // <-- Igual que en tu imagen, para la inyección de dependencias
public class UsuarioService implements IUsuariosService {

    // --- Inyección de dependencia ---
    // (Asegúrate de haber creado tu UsuarioRepository)
    private final UsuariosRepository usuarioRepository;

    // (Opcional: Si vas a encriptar, inyecta el PasswordEncoder)
    // private final PasswordEncoder passwordEncoder;

    // --- Implementación de los métodos de la interfaz ---

    @Override
    public List<Usuarios> getAllUsuariosActivos() {
        // Llama al método que debes crear en tu UsuarioRepository
        return usuarioRepository.findByActivoTrue();
    }

    @Override
    public Optional<Usuarios> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuarios> getUsuarioByCorreo(String correo) {
        // Llama al método que debes crear en tu UsuarioRepository
        return usuarioRepository.findByCorreo(correo);
    }
/*
    @Override
    public List<Usuarios> getUsuariosByRol(Rol rol) {
        // Llama al método que debes crear en tu UsuarioRepository
        return usuarioRepository.findByRol(rol);
    }

 */

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuarios updateUsuarioById(Long id, Usuarios usuarioActualizado) {
        // 1. Buscamos el usuario existente
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuarioActualizado.getNombre());
                    usuarioExistente.setApellidos(usuarioActualizado.getApellidos());
                    usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
                    usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
                    //usuarioExistente.setRol(usuarioActualizado.getRol());
                    return usuarioRepository.save(usuarioExistente);
                })
                .orElse(null);
    }

    @Override
    public void deleteUsuarioById(Long id) {
        usuarioRepository.findById(id)
                .ifPresent(usuario -> {
                    usuario.setActivo(false);
                    usuarioRepository.save(usuario);
                });
    }
}