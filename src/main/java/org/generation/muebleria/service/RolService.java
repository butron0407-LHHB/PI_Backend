package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.model.Roles;
import org.generation.muebleria.repository.RolRepository;
import org.generation.muebleria.service.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Roles> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Roles getRolById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Roles getRolByNombre(String nombre) {
        return rolRepository.findByNombreRol(nombre).orElse(null);
    }

    @Override
    public Roles createRol(Roles rol) {
        // Validar que no exista un rol con el mismo nombre
        Optional<Roles> rolExistente = rolRepository.findByNombreRol(rol.getNombreRol());
        if (rolExistente.isPresent()) {
            throw new RuntimeException("Ya existe un rol con el nombre: " + rol.getNombreRol());
        }
        return rolRepository.save(rol);
    }

    @Override
    public Roles updateRolById(Long id, Roles rolActualizado) {
        // 1. Buscamos el rol
        Optional<Roles> rolExistente = rolRepository.findById(id);

        // 2. Si no existe, devolvemos null
        if (rolExistente.isEmpty()) {
            return null;
        }

        // 3. Validar que el nuevo nombre no exista en otros roles
        if (rolActualizado.getNombreRol() != null) {
            Optional<Roles> rolConMismoNombre = rolRepository.findByNombreRol(rolActualizado.getNombreRol());
            if (rolConMismoNombre.isPresent() && !rolConMismoNombre.get().getIdRol().equals(id)) {
                throw new RuntimeException("Ya existe otro rol con el nombre: " + rolActualizado.getNombreRol());
            }
        }

        // 4. Actualizamos los campos
        Roles rolDb = rolExistente.get();
        if (rolActualizado.getNombreRol() != null) {
            rolDb.setNombreRol(rolActualizado.getNombreRol());
        }
        if (rolActualizado.getDescripcion() != null) {
            rolDb.setDescripcion(rolActualizado.getDescripcion());
        }

        return rolRepository.save(rolDb);
    }

    @Override
    public void deleteRolById(Long id) {
        // Verificar que el rol existe antes de eliminar
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el rol con ID: " + id);
        }

        // Podrías agregar validación para no eliminar roles que estén en uso
        rolRepository.deleteById(id);
    }
}