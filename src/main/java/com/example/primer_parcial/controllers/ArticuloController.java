package com.example.primer_parcial.controllers;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.repository.ArticuloRepository;
import com.example.primer_parcial.services.ArticuloService;
import com.example.primer_parcial.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(maxAge = 3600)
@RestController
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/articulo")
    public ResponseEntity crearArticulo(@RequestBody Articulo articulo,@RequestHeader(value = "Authorization") String token){
        try {
            if(jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
            }
            return articuloService.createArticulo(articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

    }
    @GetMapping(value = "/articulo/{id}")
    public ResponseEntity getArticulo(@PathVariable Long id, @RequestHeader(value = "Authorization") String token ) {
        try{
            if(jwtUtil.getKey(token) == null){
                return articuloService.getArticuloById(id);
            }return ResponseEntity.badRequest().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

    }
    @GetMapping("/articulo/nombre/{nombre}")
    public ResponseEntity listaPorNombre(@PathVariable String nombre,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

        return articuloService.allArticulosByName(nombre);
    }
    @GetMapping("/articulo/codigo/{codigo}")
    public ResponseEntity listaPorCodigo(@PathVariable String codigo,@RequestHeader(value = "Authorization") String token){
        try {
            if(jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
            }
            return articuloService.getArticuloByCodigo(codigo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

    }

    @PutMapping("/articulo/{codigo}")
    public ResponseEntity editarArticulo(@PathVariable String codigo, @RequestBody Articulo articulo , @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.editArticle(codigo, articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido"+e.getMessage());
        }

    }

    @DeleteMapping("/articulo/{codigo}")
    public ResponseEntity eliminarArticulo(@PathVariable String codigo,@RequestHeader(value = "Authorization") String token){
        try {
            if(jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
            }
            return articuloService.deleteArticuloByCodigo(codigo);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
    }
    @GetMapping("/articulos")
    public ResponseEntity listarArticulo(@RequestHeader(value = "Authorization") String token){
        try {
            if(jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
            }
            return articuloService.allArticulos();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

    }


}