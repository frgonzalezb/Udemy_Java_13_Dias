/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.util.ArrayList;
import java.util.List;

import franc._catalogopeliculasapp.dominio.Pelicula;

/**
 *
 * @author franc
 */
public class ServicioPeliculasLista implements IServicioPeliculas {
    private final List<Pelicula> peliculas;
    
    public ServicioPeliculasLista() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de películas: ");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agregó la película " + pelicula + " exitosamente.");
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        // Devuelve el índice de la película encontrada en la lista
        int indice = peliculas.indexOf(pelicula);
        if (indice == -1) {
            System.out.println("No se encontró la película: " + pelicula);
        } else {
            System.out.println("Película " + pelicula + " encontrada en el índice " + indice);
        }
    }
}
