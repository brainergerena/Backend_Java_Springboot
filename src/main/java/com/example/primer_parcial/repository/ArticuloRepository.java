package com.example.primer_parcial.repository;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
    Optional<Articulo> findByCodigo(String codigo);

    List<Articulo> findAllByNombre(String nombre);

}
