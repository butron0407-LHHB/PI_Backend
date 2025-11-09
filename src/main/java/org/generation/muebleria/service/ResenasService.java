package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ResenasRequest;
import org.generation.muebleria.model.Pedidos;
import org.generation.muebleria.model.Productos;
import org.generation.muebleria.model.Resenas;
import org.generation.muebleria.model.Usuarios;
import org.generation.muebleria.repository.PedidosRepository;
import org.generation.muebleria.repository.ProductoRepository;
import org.generation.muebleria.repository.ResenasRepository;
import org.generation.muebleria.repository.UsuariosRepository;
import org.generation.muebleria.service.interfaces.IResenasService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResenasService implements IResenasService {

    // Inyección de dependencias
    private ResenasRepository resenasRepository;
    private ProductoRepository productoRepository;
    private UsuariosRepository usuariosRepository;
    private PedidosRepository pedidosRepository;

    @Override
    public List<Resenas> getAllResenasVisible() {
        return resenasRepository.findByResenaVisibleTrue();
    }

    @Override
    public List<Resenas> getAllResenas() {
        return resenasRepository.findAll();
    }

    @Override
    public Optional<Resenas> getResenaById(Long id) {
        return Optional.ofNullable(resenasRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La reseña con el id: " + id + " no existe")
        ));
    }

    @Override
    public List<Resenas> getResenasByProducto(Long idProducto) {
        return resenasRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public List<Resenas> getResenasByUsuario(Long idUsuario) {
        return resenasRepository.findByUsuarioIdUsuario(idUsuario);
    }

    @Override
    public List<Resenas> getResenasByPedido(Long idPedido) {
        return resenasRepository.findByPedidoIdPedido(idPedido);
    }

    @Override
    public Resenas addResena(ResenasRequest resena) {
        Productos producto = productoRepository.findById(resena.getIdProducto()).orElseThrow(
                () -> new IllegalArgumentException("El producto con ID " + resena.getIdProducto() + " no existe.")
        );
        Usuarios usuario = usuariosRepository.findById(resena.getIdUsuario()).orElseThrow(
                () -> new IllegalArgumentException("El usuario con ID " + resena.getIdUsuario() + " no existe.")
        );
        Pedidos pedido = pedidosRepository.findById(resena.getIdPedido()).orElseThrow(
                () -> new IllegalArgumentException("El pedido con ID " + resena.getIdPedido() + " no existe.")
        );

        Resenas newResena = new Resenas();
        if(resena.getCalificacion() != null) newResena.setCalificacion(resena.getCalificacion());
        if(resena.getComentario() != null) newResena.setComentario(resena.getComentario());
        if(resena.getResenaVisible() != null) newResena.setResenaVisible(resena.getResenaVisible());

        // La fecha se genera automáticamente si no viene en el request
        if(resena.getFechaResena() != null) {
            newResena.setFechaResena(resena.getFechaResena());
        } else {
            newResena.setFechaResena(LocalDateTime.now());
        }

        newResena.setProducto(producto);
        newResena.setUsuario(usuario);
        newResena.setPedido(pedido);

        return resenasRepository.save(newResena);
    }

    @Override
    public Resenas updateResenaById(Long id, ResenasRequest updateResena) {
        Optional<Resenas> optionalResena = resenasRepository.findById(id);
        if(optionalResena.isEmpty()) throw new IllegalArgumentException("La reseña no existe");

        // Obteniendo la reseña de la BD
        Resenas resenaDB = optionalResena.get();
        if(updateResena.getCalificacion() != null) resenaDB.setCalificacion(updateResena.getCalificacion());
        if(updateResena.getComentario() != null) resenaDB.setComentario(updateResena.getComentario());
        if(updateResena.getResenaVisible() != null) resenaDB.setResenaVisible(updateResena.getResenaVisible());
        if(updateResena.getFechaResena() != null) resenaDB.setFechaResena(updateResena.getFechaResena());

        if (updateResena.getIdProducto() != null) {
            Productos producto = productoRepository.findById(updateResena.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + updateResena.getIdProducto() + " no existe."));
            resenaDB.setProducto(producto);
        }

        if (updateResena.getIdUsuario() != null) {
            Usuarios usuario = usuariosRepository.findById(updateResena.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + updateResena.getIdUsuario() + " no existe."));
            resenaDB.setUsuario(usuario);
        }

        if (updateResena.getIdPedido() != null) {
            Pedidos pedido = pedidosRepository.findById(updateResena.getIdPedido())
                    .orElseThrow(() -> new IllegalArgumentException("El pedido con ID " + updateResena.getIdPedido() + " no existe."));
            resenaDB.setPedido(pedido);
        }

        return resenasRepository.save(resenaDB);
    }

    @Override
    public void ocultarResenaById(Long id) {
        Resenas resena = resenasRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La reseña con el id: " + id + " no existe")
        );

        // Ocultar la reseña
        if(resena.getResenaVisible() != null && resena.getResenaVisible()){
            resena.setResenaVisible(false);
            resenasRepository.save(resena);
        } else {
            throw new IllegalArgumentException("La reseña con ID " + id + " ya está oculta.");
        }
    }

    @Override
    public void mostrarResenaById(Long id) {
        Resenas resena = resenasRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La reseña con el id: " + id + " no existe")
        );

        // Mostrar reseña
        if(resena.getResenaVisible() == null || !resena.getResenaVisible()){
            resena.setResenaVisible(true);
            resenasRepository.save(resena);
        } else {
            throw new IllegalArgumentException("La reseña con ID " + id + " ya está visible.");
        }
    }

    @Override
    public void deleteResenaById(Long id) {
        Resenas resena = resenasRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La reseña con el id: " + id + " no existe")
        );
        resenasRepository.deleteById(id);
    }
}