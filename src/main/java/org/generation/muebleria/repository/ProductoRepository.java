package org.generation.muebleria.repository;

import org.generation.muebleria.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Productos,Long> {

    // ========== MÉTODOS CON IMÁGENES ==========

    // Listar todos los productos activos CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.activo = true")
    List<Productos> findAllActiveWithImages();

    // Listar todos los productos (activos e inactivos) CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p")
    List<Productos> findAllWithImages();

    // ✅ CORREGIDO: Buscar producto por ID CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.idProducto = :id")
    Optional<Productos> findByIdWithImages(@Param("id") Long id);

    // ========== TUS MÉTODOS EXISTENTES ACTUALIZADOS ==========

    //listar solo productos activos con su categoria, proveedores E IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.activo = true")
    List<Productos> findByActivoTrueWithCategoriasAndProveedores();

    // 1. FILTRADO PARA USUARIO NORMAL (Activos por Categoría) CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.activo = true AND p.categoria.idCategoria = :categoriaId")
    List<Productos> findActiveByCategoriaId(@Param("categoriaId") Long categoriaId);

    // 2. FILTRADO PARA USUARIO NORMAL (Activos por Proveedor) CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.activo = true AND p.proveedor.idProveedor = :proveedorId")
    List<Productos> findActiveByProveedorId(@Param("proveedorId") Long proveedorId);

    // 3. FILTRADO PARA ADMINISTRADOR (Activos por Categoria Y/O Proveedor) CON IMÁGENES
    @EntityGraph(attributePaths = {"imagenesProducto", "categoria", "proveedor"})
    @Query("SELECT p FROM Productos p WHERE p.activo = true " +
            "AND (:categoriaId IS NULL OR p.categoria.idCategoria = :categoriaId) " +
            "AND (:proveedorId IS NULL OR p.proveedor.idProveedor = :proveedorId)")
    List<Productos> filterActiveByCategoriaAndProveedor(@Param("categoriaId") Long categoriaId, @Param("proveedorId") Long proveedorId);
}