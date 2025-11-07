package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ProveedorRequest;
import org.generation.muebleria.model.Proveedores;
import org.generation.muebleria.repository.ProveedoresRepository;
import org.generation.muebleria.service.interfaces.IProveedoresService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProveedoresService implements IProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

//    @Override
//    public Proveedores crearProveedor(Proveedores proveedor) {
//        proveedor.setFechaRegistro(LocalDateTime.now());
//        proveedor.setFechaActualizacion(LocalDateTime.now());
//
//        // Si no se especifica, activar por defecto
//        if (proveedor.getAcivo() == null) {
//            proveedor.setAcivo(true);
//        }
//
//        return proveedoresRepository.save(proveedor);
//    }
//
//    @Override
//    public List<Proveedores> obtenerProveedoresActivos() {
//        return proveedoresRepository.findByAcivoTrue();
//    }
//
//    @Override
//    public List<Proveedores> obtenerTodosProveedores() {
//        return proveedoresRepository.findAll();
//    }
//
//    @Override
//    public Proveedores obtenerProveedorPorId(Long id) {
//        return proveedoresRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("No existe el proveedor con id: " + id));
//    }
//
//    @Override
//    public Proveedores actualizarProveedor(Long id, Proveedores proveedorActualizado) {
//        Proveedores proveedorExistente = proveedoresRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("No existe el proveedor con id: " + id));
//
//        // Actualizar solo los campos que vienen en el request
//        if (proveedorActualizado.getNombreEmpresa() != null) {
//            proveedorExistente.setNombreEmpresa(proveedorActualizado.getNombreEmpresa());
//        }
//        if (proveedorActualizado.getNombre() != null) {
//            proveedorExistente.setNombre(proveedorActualizado.getNombre());
//        }
//        if (proveedorActualizado.getTelefono() != null) {
//            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
//        }
//        if (proveedorActualizado.getCorreo() != null) {
//            proveedorExistente.setCorreo(proveedorActualizado.getCorreo());
//        }
//        if (proveedorActualizado.getDireccion() != null) {
//            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
//        }
//        if (proveedorActualizado.getAcivo() != null) {
//            proveedorExistente.setAcivo(proveedorActualizado.getAcivo());
//        }
//
//        // Actualizar fecha de modificación
//        proveedorExistente.setFechaActualizacion(LocalDateTime.now());
//
//        return proveedoresRepository.save(proveedorExistente);
//    }
//
//    @Override
//    public Proveedores desactivarProveedor(Long id) {
//        Proveedores proveedor = proveedoresRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("No existe el proveedor con id: " + id));
//
//        proveedor.setAcivo(false);
//        proveedor.setFechaActualizacion(LocalDateTime.now());
//
//        return proveedoresRepository.save(proveedor);
//    }

    @Override
    public List<Proveedores> getProveedoresActivos() {
        return proveedoresRepository.findByActivoTrue();
    }

    @Override
    public Optional<Proveedores> getProveedorById(Long id) {
        return proveedoresRepository.findById(id);
    }

    @Override
    public Proveedores addProveedor(ProveedorRequest proveedor) {
        if(proveedoresRepository.findByNombreEmpresa(proveedor.getNombreEmpresa()).isPresent()){
            throw new IllegalArgumentException("Ya existe un proveedor con esa empresa.");
        }

        Proveedores nuevoProveedor = new Proveedores();
        nuevoProveedor.setNombreEmpresa(proveedor.getNombreEmpresa());
        nuevoProveedor.setNombre(proveedor.getNombre());
        nuevoProveedor.setTelefono(proveedor.getTelefono());
        nuevoProveedor.setCorreo(proveedor.getCorreo());
        nuevoProveedor.setDireccion(proveedor.getDireccion());

        return proveedoresRepository.save(nuevoProveedor);
    }

    @Override
    public Proveedores updateProveedor(Long id, ProveedorRequest proveedor) {
        Proveedores proveedorDB = proveedoresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado."));

        if (proveedor.getNombreEmpresa() != null) proveedorDB.setNombreEmpresa(proveedor.getNombreEmpresa());
        if (proveedor.getNombre() != null) proveedorDB.setNombre(proveedor.getNombre());
        if (proveedor.getTelefono() != null) proveedorDB.setTelefono(proveedor.getTelefono());
        if (proveedor.getCorreo() != null) proveedorDB.setCorreo(proveedor.getCorreo());
        if (proveedor.getDireccion() != null) proveedorDB.setDireccion(proveedor.getDireccion());
        if (proveedor.getActivo() != null) proveedorDB.setActivo(proveedor.getActivo());

        proveedorDB.setFechaActualizacion(LocalDateTime.now());
        return proveedoresRepository.save(proveedorDB);
    }

    @Override
    public void desactivarProveedorById(Long id) {
        Proveedores proveedor = proveedoresRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        if (proveedor.getActivo()) {
            proveedor.setActivo(false);
            proveedoresRepository.save(proveedor);
        }
    }

    @Override
    public void activarProveedorById(Long id) {
        Proveedores proveedor = proveedoresRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        if (!proveedor.getActivo()) {
            proveedor.setActivo(true);
            proveedoresRepository.save(proveedor);
        }
    }

}