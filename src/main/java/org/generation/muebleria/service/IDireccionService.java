package org.generation.muebleria.service;

import org.generation.muebleria.dto.DireccionRequest;
import org.generation.muebleria.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface IDireccionService {
    List<Direccion> getAllDirecciones();
    Optional<Direccion> getDireccionById(Long id);
    List<Direccion> getDireccionesByUsuario(Long idUsuario);
    Optional<Direccion> getDireccionPredeterminada(Long idUsuario);
    Direccion createDireccion(DireccionRequest direccionRequest);
    Direccion updateDireccion(Long id, DireccionRequest direccionRequest);
    Direccion setDireccionPredeterminada(Long id);
    boolean deleteDireccion(Long id);
    boolean puedeEliminarDireccion(Long id);
}