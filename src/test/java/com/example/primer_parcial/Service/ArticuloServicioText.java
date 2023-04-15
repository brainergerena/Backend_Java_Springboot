package com.example.primer_parcial.Service;

import com.example.primer_parcial.models.Articulo;
import com.example.primer_parcial.models.Categoria;
import com.example.primer_parcial.models.Usuario;
import com.example.primer_parcial.repository.ArticuloRepository;
import com.example.primer_parcial.repository.CategoriaRepository;
import com.example.primer_parcial.repository.UsuarioRepository;
import com.example.primer_parcial.services.ArticuloServicelmpl;
import com.example.primer_parcial.services.UsuarioServicelmpl;
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
public class ArticuloServicioText {

    @InjectMocks
    private ArticuloServicelmpl articuloServicelmpl;

    @Mock
    ArticuloRepository articuloRepository;
    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    void seDebeEncontrarUnArticuloPorId(){
        // given
        Articulo articulo= new Articulo();
        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");
        articulo.setId(1l);
        articulo.setCodigo("001");
        articulo.setNombre("Coca-cola");
        articulo.setDescripcion("Prueba");
        articulo.setFecha_registro(new Date(2002-06-15));
        articulo.setStock(10);
        articulo.setCategoria(categoria);
        articulo.setPrecio_compra(new Float(4.000));
        articulo.setPrecio_venta(new Float(5.000));





        // when
        when(articuloRepository.findById(anyLong())).thenReturn(Optional.of(articulo));


        ResponseEntity<Articulo> articuloRespuesta = articuloServicelmpl.getArticuloById(anyLong());
        // then

        Assertions.assertNotNull(articuloRespuesta);


    }
    @Test
    void whenNoEncuentraUnArticuloPorId(){

        Articulo articulo = null;


        //given




        //when

        when(articuloRepository.findById(anyLong())).thenReturn(Optional.ofNullable(articulo));

        //then

        Articulo articuloOptional = articuloServicelmpl.getArticuloById(anyLong()).getBody();
        Assertions.assertEquals(null, articuloOptional);

    }

     @Test
    void seDebeEncontrarUnArticuloPorCodigo(){
        // given
        Articulo articulo= new Articulo();
        Categoria categoria= new Categoria();
        categoria.setId(2l);
         categoria.setNombre("Bebidas");
         categoria.setDescripcion("Azucaradas");
         articulo.setId(1l);
         articulo.setCodigo("001");
         articulo.setNombre("Coca-cola");
         articulo.setDescripcion("Prueba");
         articulo.setFecha_registro(new Date(2002-06-15));
         articulo.setStock(10);
         articulo.setCategoria(categoria);
         articulo.setPrecio_compra(new Float(4.000));
         articulo.setPrecio_venta(new Float(5.000));



        // when
        when(articuloRepository.findByCodigo(anyString())).thenReturn(Optional.of(articulo));


        ResponseEntity<Articulo> articuloRespuesta = articuloServicelmpl.getArticuloByCodigo(anyString());

        // then
        Assertions.assertNotNull(articuloRespuesta);


    }

    @Test
    void whenNoEncuentraUnArticuloPorCodigo(){
         Articulo articulo = null;


        //given




        //when

        when(articuloRepository.findByCodigo(anyString())).thenReturn(Optional.ofNullable(articulo));

        //then
        //Usuario usuarioOptional = usuarioServicelmpl.getUserById(anyLong()).getBody();
        Articulo articuloOptional = articuloServicelmpl.getArticuloByCodigo(anyString()).getBody();

        Assertions.assertEquals(null, articuloOptional);


    }

    @Test
    void seDebeEncontrarUnArticuloPorNombre(){
        // given
        Articulo articulo= new Articulo();
        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");
        articulo.setId(1l);
        articulo.setCodigo("001");
        articulo.setNombre("Coca-cola");
        articulo.setDescripcion("Prueba");
        articulo.setFecha_registro(new Date(2002-06-15));
        articulo.setStock(10);
        articulo.setCategoria(categoria);
        articulo.setPrecio_compra(new Float(4.000));
        articulo.setPrecio_venta(new Float(5.000));





        // when

        when(articuloRepository.findAllByNombre("kevin")).thenReturn(List.of(articulo));



        ResponseEntity<List<Articulo>> articuloRespuesta = articuloServicelmpl.allArticulosByName("kevin");


        // then

        Assertions.assertNotNull(articuloRespuesta);


    }

    @Test
    void whenNoEncuentraUnArticuloPorNombre(){

        Articulo articulo = null;


        //given




        //when

        when(articuloRepository.findAllByNombre(anyString())).thenReturn(Collections.emptyList());

        //then

        List<Articulo> articuloOptional = articuloServicelmpl.allArticulosByName(anyString()).getBody();
        Assertions.assertEquals(null, articuloOptional);

    }

    @Test
    void seDebeListarLosArticulos() {
        //Given
        Articulo articulo= new Articulo();
        Categoria categoria= new Categoria();
        categoria.setId(2l);
        categoria.setNombre("Bebidas");
        categoria.setDescripcion("Azucaradas");
        articulo.setId(1l);
        articulo.setCodigo("001");
        articulo.setNombre("Coca-cola");
        articulo.setDescripcion("Prueba");
        articulo.setFecha_registro(new Date(2002-06-15));
        articulo.setStock(10);
        articulo.setCategoria(categoria);
        articulo.setPrecio_compra(new Float(4.000));
        articulo.setPrecio_venta(new Float(5.000));

        //when
        when(articuloRepository.findAll()).thenReturn(List.of(articulo));






        ResponseEntity<List<Articulo>> articuloP = articuloServicelmpl.allArticulos();

        //then
        Assertions.assertNotNull(articuloP);
    }

    @Test
    void whenNoEncuentraNingunArticulo() {

        Articulo articulo = null;

        //When

        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());


        List<Articulo> articuloP = articuloServicelmpl.allArticulos().getBody();


        //Then
        Assertions.assertEquals(null, articuloP);

    }

}
