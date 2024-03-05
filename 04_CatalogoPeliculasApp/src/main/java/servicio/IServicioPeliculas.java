/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicio;

import franc._catalogopeliculasapp.dominio.Pelicula;

/**
 *
 * @author franc
 */
public interface IServicioPeliculas {
    // Recuerda que en las interfaces solo se declaran los métodos.
    // La definición va en las respectivas clases.
    public void listarPeliculas();
    public void agregarPelicula(Pelicula pelicula);
    public void buscarPelicula(Pelicula pelicula);
}
