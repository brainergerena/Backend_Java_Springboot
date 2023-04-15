package com.example.primer_parcial.Service;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.models.Categoria;
import com.example.primer_parcial.repository.CategoriaRepository;
import com.example.primer_parcial.services.ArticuloServicelmpl;
import com.example.primer_parcial.services.CategoriaServicelmpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CategoriaServicioText {

    @InjectMocks
    private CategoriaServicelmpl categoriaServicelmpl ;

    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    void seDebeEncontrarUnCategoriaPorId(){
        // given
        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");





        // when

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));



        ResponseEntity<Categoria> categoriaRespuesta = categoriaServicelmpl.getCategoriaById(anyLong());
        // then

        Assertions.assertNotNull(categoriaRespuesta);


    }

    @Test
    void whenNoEncuentraUnaCategoriaPorId(){


        Categoria categoria = null;


        //given




        //when

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(categoria));

        //then


        Categoria categoriaOptional = categoriaServicelmpl.getCategoriaById(anyLong()).getBody();
        Assertions.assertEquals(null, categoriaOptional);

    }

    @Test
    void seDebeEncontrarUnaCategoriaPorNombre(){
        // given

        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");






        // when


        when(categoriaRepository.findAllByNombre("peppe")).thenReturn(List.of(categoria));



        ResponseEntity<List<Categoria>> categoriaRespuesta = categoriaServicelmpl.allCategoriasByName("peppe");


        // then

        Assertions.assertNotNull(categoriaRespuesta);


    }


    @Test
    void whenNoEncuentraUnaCategoriaPorNombre(){


        Categoria categoria = null;


        //given




        //when


        when(categoriaRepository.findAllByNombre(anyString())).thenReturn(Collections.emptyList());

        //then


        List<Categoria> categoriaOptional = categoriaServicelmpl.allCategoriasByName(anyString()).getBody();
        Assertions.assertEquals(null, categoriaOptional);

    }

    @Test
    void seDebeListarLasCategorias() {
        //Given

        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");


        //when

        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));







        ResponseEntity<List<Categoria>> categoriaP = categoriaServicelmpl.allCategorias();

        //then
        Assertions.assertNotNull(categoriaP);
    }

    @Test
    void whenNoEncuentraNingunaCategoria() {


        Categoria categoria = null;

        //When


        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());



        List<Categoria> categoriaP = categoriaServicelmpl.allCategorias().getBody();


        //Then
        Assertions.assertEquals(null, categoriaP);

    }
}
