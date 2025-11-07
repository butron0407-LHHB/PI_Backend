package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.model.Resenas;
import java.util.List;

public interface IResenasService {

//    List<Resenas> getAllResenasActive();

    Resenas getResenasById(Long id);
    Resenas createResenas(Resenas resenas);
    Resenas updateResenasById(Long id, Resenas updateResenas);
    Resenas deleteResenasById(Long id);
}