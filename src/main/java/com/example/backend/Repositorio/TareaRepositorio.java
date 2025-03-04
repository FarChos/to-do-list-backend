package com.example.backend.Repositorio;

import com.example.backend.Modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface TareaRepositorio extends JpaRepository<Tarea, Long> {
    Optional<Tarea> findById(Long id);
    }
