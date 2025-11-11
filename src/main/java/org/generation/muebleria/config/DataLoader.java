package org.generation.muebleria.config;

import lombok.AllArgsConstructor;
import org.generation.muebleria.model.Categorias;
import org.generation.muebleria.model.Proveedores;
import org.generation.muebleria.model.Roles;
import org.generation.muebleria.repository.CategoriaRepository;
import org.generation.muebleria.repository.ProveedoresRepository;
import org.generation.muebleria.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime; // Importar para fechas de proveedores

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedoresRepository proveedoresRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // --- Creación de Roles ---
        if(rolRepository.findByNombreRol("ADMINISTRADOR").isEmpty()){
            Roles adminRole = new Roles(null,"ADMINISTRADOR",null);
            rolRepository.save(adminRole);
            System.out.println("Rol administrador creado");
        }

        if(rolRepository.findByNombreRol("CLIENTE").isEmpty()){
            Roles adminRole = new Roles(null,"CLIENTE",null);
            rolRepository.save(adminRole);
            System.out.println("Rol cliente creado");
        }

        // ==========================================================
        // INICIO - CATEGORÍAS DE MUEBLERÍA ESPAÑA
        // ==========================================================

        // --- Creación de Categorías predefinidas por la mueblería ---
        // Verificamos si no hay categorías
        if (categoriaRepository.count() == 0) { 
            System.out.println("Cargando categorías reales del sitio...");

            // 1. Guardar Categorías Padre (con y sin hijos)
            // Guarda el objeto retornado por save() para obtener su ID
            Categorias salas = categoriaRepository.save(new Categorias(null, "Salas", true, null, null));
            Categorias dormitorio = categoriaRepository.save(new Categorias(null, "Dormitorio", true, null, null));
            Categorias comedores = categoriaRepository.save(new Categorias(null, "Comedores", true, null, null));
            Categorias lineaBlanca = categoriaRepository.save(new Categorias(null, "Línea blanca", true, null, null));
            
            // Categorías padre sin hijos
            categoriaRepository.save(new Categorias(null, "Roperos", true, null, null));
            categoriaRepository.save(new Categorias(null, "Colchones", true, null, null));
            categoriaRepository.save(new Categorias(null, "Ofertas", true, null, null));

            // 2. Guardar Categorías Hijas (usando los objetos padre que guardamos)
            
            // Hijas de Salas
            categoriaRepository.save(new Categorias(null, "Modulares de 4 piezas", true, null, salas));
            categoriaRepository.save(new Categorias(null, "Modulares de 5 piezas", true, null, salas));
            categoriaRepository.save(new Categorias(null, "Sofacamas", true, null, salas));
            categoriaRepository.save(new Categorias(null, "Reclinables", true, null, salas));
            
            // Hijas de Dormitorio
            categoriaRepository.save(new Categorias(null, "Recámaras king size y matrimonial", true, null, dormitorio));
            categoriaRepository.save(new Categorias(null, "Cabeceras", true, null, dormitorio));
            categoriaRepository.save(new Categorias(null, "Bases", true, null, dormitorio));

            // Hijas de Comedores
            categoriaRepository.save(new Categorias(null, "4 sillas", true, null, comedores));
            categoriaRepository.save(new Categorias(null, "6 sillas", true, null, comedores));
            categoriaRepository.save(new Categorias(null, "8 sillas", true, null, comedores));

            // Hijas de Línea blanca
            categoriaRepository.save(new Categorias(null, "Lavadoras", true, null, lineaBlanca));
            categoriaRepository.save(new Categorias(null, "Estufas", true, null, lineaBlanca));
            categoriaRepository.save(new Categorias(null, "Refrigeradores", true, null, lineaBlanca));
            categoriaRepository.save(new Categorias(null, "Microondas", true, null, lineaBlanca));
            
            System.out.println("Categorías reales cargadas exitosamente.");
        }

        // --- Creación de Proveedores ---
        if (proveedoresRepository.count() == 0) { 
            System.out.println("Cargando proveedores de demo...");

            // (id, nombreEmpresa, nombre, telefono, correo, direccion, activo, fechaRegistro, fechaActualizacion, productos)
            Proveedores p1 = new Proveedores(null, "Muebles Modernos S.A.", "Juan Pérez", "5512345678", "juan@modernos.com", "Calle Falsa 123", true, null, null, null);
            proveedoresRepository.save(p1);

            Proveedores p2 = new Proveedores(null, "Decoraciones Rústicas", "Ana García", "5587654321", "ana@rustico.com", "Avenida Siempre Viva 742", true, null, null, null);
            proveedoresRepository.save(p2);

            Proveedores p3 = new Proveedores(null, "Importadora del Norte", "Carlos Sánchez", "5555555555", "carlos@import.com", "Blvd. Industrial 404", false, null, null, null);
            proveedoresRepository.save(p3);
            
            System.out.println("Proveedores de demo cargados.");
        }

        // ==========================================================
        // FIN - ACTUALIZACIÓN
        // ==========================================================
    }
