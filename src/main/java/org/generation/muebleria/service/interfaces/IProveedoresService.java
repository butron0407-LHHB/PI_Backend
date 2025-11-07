package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.ProveedorRequest;
import org.generation.muebleria.model.Proveedores;
import java.util.List;
import java.util.Optional;

public interface IProveedoresService {

    // CREATE
//    Proveedores crearProveedor(Proveedores proveedor);

    // READ
    List<Proveedores> getProveedoresActivos();
//    List<Proveedores> obtenerTodosProveedores();
    Optional<Proveedores> getProveedorById(Long id);

    // UPDATE
    Proveedores addProveedor(ProveedorRequest proveedor);
    Proveedores updateProveedor(Long id, ProveedorRequest proveedor);

    // DELETE (soft delete)
//    Proveedores desactivarProveedor(Long id);
    void desactivarProveedorById(Long id);
    void activarProveedorById(Long id);
}