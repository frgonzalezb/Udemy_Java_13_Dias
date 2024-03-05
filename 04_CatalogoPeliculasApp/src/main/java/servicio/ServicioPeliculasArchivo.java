/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import franc._catalogopeliculasapp.dominio.Pelicula;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author franc
 */
public class ServicioPeliculasArchivo implements IServicioPeliculas {
    private final String nombre_archivo = "peliculas.txt";
    
    public ServicioPeliculasArchivo() {
        File archivo = new File(nombre_archivo);
        
        try {
            if (archivo.exists()) {
                System.out.println("El archivo ya existe.");
            } else {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        File archivo = new File(nombre_archivo);
        String linea;
        
        try {
            System.out.println("Listado de películas: ");
            // Abrir el archivo en modo read-only
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            // Leer línea a línea
            linea = entrada.readLine();
            // Leer todas las líneas
            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                linea = entrada.readLine(); // Solicitar la siguiente línea
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ocurrió un error IO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        File archivo = new File(nombre_archivo);
        boolean anexar = false;
        
        try {
            anexar = archivo.exists();
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se agregó al archivo: " + pelicula);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ocurrió un error IO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        File archivo = new File(nombre_archivo);
        String linea;
        int indice = 1;
        boolean encontrada = false;
        String buscar = pelicula.getNombre();
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            linea = entrada.readLine();
            while (linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    encontrada = true;
                    break;
                } else {
                    linea = entrada.readLine();
                    indice++;
                }
            }
            if (encontrada) {
                System.out.println("Película " + linea + " encontrada en el índice " + indice);
            } else {
                System.out.println("No se encontró la película: " + pelicula);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ocurrió un error IO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
}
