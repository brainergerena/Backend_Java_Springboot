package com.example.primer_parcial.controllers;


import com.example.primer_parcial.models.Usuario;
import com.example.primer_parcial.services.UsuarioService;
import com.example.primer_parcial.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(maxAge = 3600)
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return usuarioService.getUserById(id);

    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.createUser(usuario);

    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(@RequestHeader(value = "Authorization") String token ) {
        try{
            if(jwtUtil.getKey(token) != null) {
                return usuarioService.allUsers();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listaPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return usuarioService.allUsersByNameAndLastName(nombre,apellidos);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listaPorNombre(@PathVariable String nombre,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return usuarioService.allUsersByName(nombre);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listaPorApellidos(@PathVariable String apellidos,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return usuarioService.allUsersByLastName(apellidos);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @Valid @RequestBody  Usuario usuario,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }
        return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id,@RequestHeader(value = "Authorization") String token ) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido");
        }

        return usuarioService.deleteUserById(id);
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
