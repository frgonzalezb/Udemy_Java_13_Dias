/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package franc._listadopersonasapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();
        
        boolean salir = false;
        while(!salir) {
            mostrarMenu();
            
            try {
                salir = ejecutarOperacion(scanner, personas);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                continuar(scanner);
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                           *** Listado Personas App ***
                           
                           1. Agregar
                           2. Listar
                           3. Salir
                           """);
        System.out.print("Proporciona la opción: ");
    }

    private static boolean ejecutarOperacion(Scanner scanner, List<Persona> personas) {
        boolean salir = false;
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch(opcion) {
            case 1 -> {
                agregarPersona(scanner, personas);
            }
            case 2 -> {
                listarPersonas(scanner, personas);
            }
            case 3 -> {
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> {
                System.out.println("Opción inválida: " + opcion);
                continuar(scanner);
            }
        }
        blankSpace();
        
        return salir;
    }

    private static void agregarPersona(Scanner scanner, List<Persona> personas) {
        String nombre;
        String telefono;
        String email;
        Persona persona;
        
        System.out.print("Proporciona el nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Proporciona el telefono: ");
        telefono = scanner.nextLine();
        System.out.print("Proporciona el email: ");
        email = scanner.nextLine();

        persona = new Persona(nombre, telefono, email);
        personas.add(persona);
        System.out.println("Se ha agregado a " + persona.getNombre() + " exitosamente.");
        continuar(scanner);
    }

    private static void listarPersonas(Scanner scanner, List<Persona> personas) {
        // Tip: Listando usando lambda y método de referencia
        // Lambda: personas.forEach((persona) -> System.out.println(persona));
        // Mét. Ref: personas.forEach(System.out::println);
        
        if (personas.size() > 1) {
            System.out.println("El listado contiene " + personas.size() + " personas.");
            personas.forEach(System.out::println);
        } else if (personas.size() == 1) {
            System.out.println("El listado contiene 1 persona.");
            System.out.println(personas.get(0));
        } else {
            System.out.println("El listado no contiene personas.");
        }
        continuar(scanner);
    }

    private static void continuar(Scanner scanner) {
        System.out.print("Presione una tecla para continuar...");
        scanner.nextLine();
    }
    
    private static void blankSpace() {
        System.out.println("");
    }
}
