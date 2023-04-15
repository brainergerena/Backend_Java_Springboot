package com.example.primer_parcial.Service;

import com.example.primer_parcial.models.Usuario;
import com.example.primer_parcial.repository.UsuarioRepository;
import com.example.primer_parcial.services.UsuarioServicelmpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UsuarioServicioText {

    @InjectMocks
    private UsuarioServicelmpl usuarioServicelmpl;

    @Mock
    UsuarioRepository usuarioRepository;

    @Test
    void seDebeEncontrarUnUsuarioPorId(){
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");





        // when
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));


         ResponseEntity<Usuario> usuarioRespuesta = usuarioServicelmpl.getUserById(anyLong());

        // then

        Assertions.assertNotNull(usuarioRespuesta);


    }
    @Test
    void whenNoEncuentraUnUsuarioPorId(){
        Usuario usuario = null;


        //given




        //when
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(usuario));

        //then
        Usuario usuarioOptional = usuarioServicelmpl.getUserById(anyLong()).getBody();
        Assertions.assertEquals(null, usuarioOptional);

    }
    @Test
    void seDebeEncontrarUnUsuarioPorNombre(){
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");





        // when
        when(usuarioRepository.findAllByNombre("brainer")).thenReturn(List.of(usuario));
        //when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));


        ResponseEntity<List<Usuario>> usuarioRespuesta = usuarioServicelmpl.allUsersByName("brainer");
        // ResponseEntity<List<Usuario>> usuarios = usuarioServicelmpl.allUsersByName(anyString());

        // then

        Assertions.assertNotNull(usuarioRespuesta);


    }
    @Test
    void whenNoEncuentraUnUsuarioPorNombre(){
        Usuario usuario = null;


        //given




        //when
        when(usuarioRepository.findAllByNombre(anyString())).thenReturn(Collections.emptyList());

        //then
        List<Usuario> usuarioOptional = usuarioServicelmpl.allUsersByName(anyString()).getBody();
        Assertions.assertEquals(null, usuarioOptional);

    }


    @Test
    void seDebeEncontrarUnUsuarioPorApellido(){
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");





        // when
        when(usuarioRepository.findAllByApellidos("gerena")).thenReturn(List.of((usuario)));
        //when(usuarioRepository.findAllByNombre("kevin")).thenReturn(List.of(usuario));
        //when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<List<Usuario>> usuarioRespuesta = usuarioServicelmpl.allUsersByLastName("gerena");
        //ResponseEntity<List<Usuario>> usuarioRespuesta = usuarioServicelmpl.allUsersByName("kevin");
        // ResponseEntity<List<Usuario>> usuarios = usuarioServicelmpl.allUsersByName(anyString());

        // then

        Assertions.assertNotNull(usuarioRespuesta);


    }

    @Test
    void whenNoEncuentraUnUsuarioPorApellido(){
        Usuario usuario = null;


        //given




        //when
        when(usuarioRepository.findAllByApellidos(anyString())).thenReturn(Collections.emptyList());

        //then
        List<Usuario> usuarioOptional = usuarioServicelmpl.allUsersByLastName(anyString()).getBody();
        Assertions.assertEquals(null, usuarioOptional);

    }


    @Test
    void seDebeEncontrarUnUsuarioPorNombreYApellido(){
        // given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");





        // when
        when(usuarioRepository.findAllByNombreAndApellidos("brainer","gerena")).thenReturn(List.of(usuario));
        //when(usuarioRepository.findAllByNombre("kevin")).thenReturn(List.of(usuario));
        //when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<List<Usuario>> usuarioRespuesta = usuarioServicelmpl.allUsersByNameAndLastName("brainer","gerena");
        //ResponseEntity<List<Usuario>> usuarioRespuesta = usuarioServicelmpl.allUsersByName("kevin");
        // ResponseEntity<List<Usuario>> usuarios = usuarioServicelmpl.allUsersByName(anyString());

        // then

        Assertions.assertNotNull(usuarioRespuesta);


    }
    @Test
    void whenNoEncuentraUnUsuarioPorNombreYapellido(){
        Usuario usuario = null;


        //given




        //when
        when(usuarioRepository.findAllByNombreAndApellidos(anyString(),anyString())).thenReturn(Collections.emptyList());

        //then
        List<Usuario> usuarioOptional = usuarioServicelmpl.allUsersByNameAndLastName(anyString(),anyString()).getBody();
        Assertions.assertEquals(null, usuarioOptional);

    }

    @Test
    void seDebeListarLosUsuarios() {
        //Given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");

        //when

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));




        ResponseEntity<List<Usuario>> usuarioP= usuarioServicelmpl.allUsers();

        //then
        Assertions.assertNotNull(usuarioP);
    }

    @Test
    void whenNoEncuentraNingunUsuario() {
        Usuario usuario = null;

        //When
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        List<Usuario> usuarioP = usuarioServicelmpl.allUsers().getBody();


        //Then
        Assertions.assertEquals(null, usuarioP);

    }

    @Test
    void seDebeActualizarUnUsuario() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNombre("brainer");
        usuario.setApellidos("gerena");
        usuario.setDocumento("102222");
        usuario.setCorreo("brainer@gmail");
        usuario.setPassword("Brainer0717");

        Usuario usuarioActualizado = new Usuario();

        usuarioActualizado.setId(1l);
        usuarioActualizado.setNombre("Juan");
        usuarioActualizado.setApellidos("Arias");
        usuarioActualizado.setDocumento("10055588");
        usuarioActualizado.setCorreo("juan@gmail");
        usuarioActualizado.setPassword("Juan0717");

        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));


        given(usuarioRepository.save(usuarioActualizado)).willReturn(usuarioActualizado);



        ResponseEntity<Usuario> userSave = usuarioServicelmpl.editUser(usuario.getId(), usuarioActualizado);

        //Then
        Assertions.assertNotNull(userSave);
    }


}
