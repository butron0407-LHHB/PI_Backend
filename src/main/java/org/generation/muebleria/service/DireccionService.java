package org.generation.muebleria.service;

import org.generation.muebleria.dto.DireccionRequest;
import org.generation.muebleria.model.Direccion;
import org.generation.muebleria.model.Usuarios;
import org.generation.muebleria.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService implements IDireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    @Override
    public Optional<Direccion> getDireccionById(Long id) {
        return direccionRepository.findById(id);
    }

    @Override
    public List<Direccion> getDireccionesByUsuario(Long idUsuario) {
        List<Direccion> todasDirecciones = direccionRepository.findAll();
        return todasDirecciones.stream()
                .filter(direccion -> direccion.getUsuario().getIdUsuario().equals(idUsuario))
                .toList();
    }

    @Override
    public Optional<Direccion> getDireccionPredeterminada(Long idUsuario) {
        List<Direccion> direccionesUsuario = getDireccionesByUsuario(idUsuario);
        return direccionesUsuario.stream()
                .filter(Direccion::getEsPredeterminada)
                .findFirst();
    }

    @Override
    public Direccion createDireccion(DireccionRequest direccionRequest) {
        Usuarios usuario = usuarioService.getUsuarioById(direccionRequest.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + direccionRequest.getIdUsuario()));

        if (direccionRequest.getEsPredeterminada()) {
            resetDireccionesPredeterminadas(direccionRequest.getIdUsuario());
        }

        Direccion direccion = new Direccion();
        direccion.setTipoDireccion(direccionRequest.getTipoDireccion());
        direccion.setAlias(direccionRequest.getAlias());
        direccion.setDireccion(direccionRequest.getDireccion());
        direccion.setCiudad(direccionRequest.getCiudad());
        direccion.setEstado(direccionRequest.getEstado());
        direccion.setMunicipio(direccionRequest.getMunicipio());
        direccion.setCodigoPostal(direccionRequest.getCodigoPostal());
        direccion.setEsPredeterminada(direccionRequest.getEsPredeterminada());
        direccion.setUsuario(usuario);

        return direccionRepository.save(direccion);
    }

    @Override
    public Direccion updateDireccion(Long id, DireccionRequest direccionRequest) {
        Optional<Direccion> direccionExistente = direccionRepository.findById(id);
        if (direccionExistente.isPresent()) {
            Direccion direccion = direccionExistente.get();
            Usuarios usuario = usuarioService.getUsuarioById(direccionRequest.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (direccionRequest.getEsPredeterminada() && !direccion.getEsPredeterminada()) {
                resetDireccionesPredeterminadas(direccionRequest.getIdUsuario());
            }

            direccion.setTipoDireccion(direccionRequest.getTipoDireccion());
            direccion.setAlias(direccionRequest.getAlias());
            direccion.setDireccion(direccionRequest.getDireccion());
            direccion.setCiudad(direccionRequest.getCiudad());
            direccion.setEstado(direccionRequest.getEstado());
            direccion.setMunicipio(direccionRequest.getMunicipio());
            direccion.setCodigoPostal(direccionRequest.getCodigoPostal());
            direccion.setEsPredeterminada(direccionRequest.getEsPredeterminada());
            direccion.setUsuario(usuario);

            return direccionRepository.save(direccion);
        }
        throw new RuntimeException("Dirección no encontrada con ID: " + id);
    }

    @Override
    public Direccion setDireccionPredeterminada(Long id) {
        Optional<Direccion> direccionOpt = direccionRepository.findById(id);
        if (direccionOpt.isPresent()) {
            Direccion direccion = direccionOpt.get();
            Long idUsuario = direccion.getUsuario().getIdUsuario();

            resetDireccionesPredeterminadas(idUsuario);

            direccion.setEsPredeterminada(true);
            return direccionRepository.save(direccion);
        }
        throw new RuntimeException("Dirección no encontrada con ID: " + id);
    }

    @Override
    public boolean deleteDireccion(Long id) {
        if (direccionRepository.existsById(id)) {
            if (puedeEliminarDireccion(id)) {
                direccionRepository.deleteById(id);
                return true;
            }
            throw new RuntimeException("No se puede eliminar la dirección porque tiene pedidos asociados");
        }
        return false;
    }

    @Override
    public boolean puedeEliminarDireccion(Long id) {
        Optional<Direccion> direccion = direccionRepository.findById(id);
        return direccion.map(value -> value.getPedidos() == null || value.getPedidos().isEmpty())
                .orElse(false);
    }

    private void resetDireccionesPredeterminadas(Long idUsuario) {
        List<Direccion> direccionesUsuario = getDireccionesByUsuario(idUsuario);
        for (Direccion dir : direccionesUsuario) {
            if (dir.getEsPredeterminada()) {
                dir.setEsPredeterminada(false);
                direccionRepository.save(dir);
            }
        }
    }
}