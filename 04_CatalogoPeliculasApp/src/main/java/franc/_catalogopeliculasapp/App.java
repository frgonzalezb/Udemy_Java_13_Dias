/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package franc._catalogopeliculasapp;

import java.util.Scanner;

import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasLista;
import servicio.ServicioPeliculasArchivo;

import franc._catalogopeliculasapp.dominio.Pelicula;

/**
 *
 * @author franc
 */
public class App {
    // Clase de tipo "presentación"

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        // Implementación del servicio
        // IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();
        
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(scanner, servicioPeliculas);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                continuar(scanner);
            }
            espacio();
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                           *** Catálogo de Películas ***
                           
                           1. Agregar película
                           2. Listar películas
                           3. Buscar película
                           4. Salir
                           """);
        System.out.print("Proporciona la opción: ");
    }

    private static boolean ejecutarOpciones(Scanner scanner, IServicioPeliculas servicioPeliculas) {
        boolean salir = false;
        String nombrePelicula;
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch(opcion) {
            case 1 -> {
                nombrePelicula = obtenerNombre(scanner);
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
                continuar(scanner);
            }
            case 2 -> {
                servicioPeliculas.listarPeliculas();
                continuar(scanner);
            }
            case 3 -> {
                nombrePelicula = obtenerNombre(scanner);
                servicioPeliculas.buscarPelicula(new Pelicula(nombrePelicula));
                continuar(scanner);
            }
            case 4 -> {
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> {
                System.out.println("Opción inválida: " + opcion);
                continuar(scanner);
            }
        }
        
        return salir;
    }
    
    private static String obtenerNombre(Scanner scanner) {
        System.out.print("Nombre de la película: ");
        return scanner.nextLine();
    }
    
    private static void continuar(Scanner scanner) {
        System.out.print("Presione una tecla para continuar...");
        scanner.nextLine();
    }
    
    private static void espacio() {
        System.out.println("");
    }
}
