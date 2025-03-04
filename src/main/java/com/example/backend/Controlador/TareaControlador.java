package com.example.backend.Controlador;

import com.example.backend.Modelo.Tarea;
import com.example.backend.Servicio.TareaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class TareaControlador {

    private final TareaServicio tareaServicio;

    @GetMapping
    public List<Tarea> getTareas() {
        return tareaServicio.getTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTarea(@PathVariable Long id) {
        return tareaServicio.getTareaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaServicio.crearTarea(tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (tareaServicio.getTareaById(id).isPresent()) {
            tareaServicio.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> updateTask(@PathVariable Long id, @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaServicio.updateTask(id, tarea));
    }
}