package com.example.primer_parcial.controllers;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.models.Categoria;
import com.example.primer_parcial.repository.CategoriaRepository;
import com.example.primer_parcial.services.CategoriaService;
import com.example.primer_parcial.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/categoria")
    public ResponseEntity crearCategoria(@RequestBody Categoria categoria, @RequestHeader(value = "Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.createCategoria(categoria);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }


    }
    @GetMapping("/categorias")
    public ResponseEntity listarCategoria( @RequestHeader(value = "Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.allCategorias();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity getCategoria(@PathVariable Long id, @RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return categoriaService.getCategoriaById(id);

    }
    @GetMapping("/categoria/nombre/{nombre}")
    public ResponseEntity listaPorNombre(@PathVariable String nombre,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return categoriaService.allCategoriasByName(nombre);

    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity editarCategoria(@PathVariable Long id ,@RequestBody Categoria categoria, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return categoriaService.editCategoria(id, categoria);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido"+e.getMessage());
        }

    }

    @DeleteMapping("categoria/{id}")
    public ResponseEntity eliminarCategoria(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return categoriaService.deleteCategoria(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido."+e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}