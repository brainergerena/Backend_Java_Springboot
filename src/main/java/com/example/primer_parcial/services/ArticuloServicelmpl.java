package com.example.primer_parcial.services;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.models.Usuario;
import com.example.primer_parcial.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServicelmpl implements ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public ResponseEntity<Articulo> createArticulo(Articulo articulo) {
        try {
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @Override
    public ResponseEntity<Articulo> getArticuloByCodigo(String codigo) {
        Optional<Articulo> articulos = articuloRepository.findByCodigo(codigo);
        if (!articulos.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> editArticle(String codigo, Articulo articulo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()) {
            try {
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setStock(articulo.getStock());
                articuloBD.get().setPrecio_venta(articulo.getPrecio_venta());
                articuloBD.get().setPrecio_compra(articulo.getPrecio_compra());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articulo, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Articulo> deleteArticuloByCodigo(String codigo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if(articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return  ResponseEntity.noContent().build();

        }


        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Articulo>> allArticulos() {
        List<Articulo> articulos= articuloRepository.findAll();
        if(articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> getArticuloById(Long id) {
        Optional<Articulo> articulo= articuloRepository.findById(id);
        if (articulo.isPresent()){
            return new ResponseEntity(articulo, HttpStatus.OK);
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Articulo>> allArticulosByName(String nombre) {

        List<Articulo> articulos= articuloRepository.findAllByNombre(nombre);
        if(articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos,HttpStatus.OK);
    }
}
