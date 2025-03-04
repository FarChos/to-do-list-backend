package com.example.backend.Servicio;

import com.example.backend.Modelo.Tarea;
import com.example.backend.Repositorio.TareaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareaServicio {

    private final TareaRepositorio tareaRepositorio;

    public List<Tarea> getTareas() {
        return tareaRepositorio.findAll();
    }

    public Optional<Tarea> getTareaById(Long id) {
        return tareaRepositorio.findById(id);
    }

    public Tarea crearTarea(Tarea tarea) {
        return tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepositorio.deleteById(id);
    }

    public Tarea updateTask(Long id, Tarea tareaActualizada) {
        Tarea tarea = tareaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"));

        if (tareaActualizada.getTitulo() != null && !tareaActualizada.getTitulo().isBlank()) {
            tarea.setTitulo(tareaActualizada.getTitulo());
        }

        if (tareaActualizada.getDescripcion() != null) {
            tarea.setDescripcion(tareaActualizada.getDescripcion());
        }

        tarea.setCompleted(tareaActualizada.isCompleted());

        return tareaRepositorio.save(tarea);
    }
}