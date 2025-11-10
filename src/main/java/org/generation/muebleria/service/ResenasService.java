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

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResenasService implements IResenasService {

    private final ResenasRepository resenasRepository;
    private final ProductoRepository productoRepository;
    private final UsuariosRepository usuariosRepository;
    private final PedidosRepository pedidosRepository;

    @Override
    public List<Resenas> getAllResenasVisible() {
        return resenasRepository.findByResenaVisible(true);
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
    public Optional<Resenas> getResenaById(Long id) {
        return resenasRepository.findById(id);
    }

    @Override
    public Resenas addResena(ResenasRequest resenaRequest) {
        // Buscar el producto
        Productos producto = productoRepository.findById(resenaRequest.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + resenaRequest.getIdProducto()));

        // Buscar el usuario
        Usuarios usuario = usuariosRepository.findById(resenaRequest.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + resenaRequest.getIdUsuario()));

        // Buscar el pedido
        Pedidos pedido = pedidosRepository.findById(resenaRequest.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + resenaRequest.getIdPedido()));

        // Crear la reseña
        Resenas resena = new Resenas();
        resena.setCalificacion(resenaRequest.getCalificacion());
        resena.setComentario(resenaRequest.getComentario());
        resena.setResenaVisible(resenaRequest.getResenaVisible() != null ? resenaRequest.getResenaVisible() : true);
        resena.setProducto(producto);
        resena.setUsuario(usuario);
        resena.setPedido(pedido);

        return resenasRepository.save(resena);
    }
}