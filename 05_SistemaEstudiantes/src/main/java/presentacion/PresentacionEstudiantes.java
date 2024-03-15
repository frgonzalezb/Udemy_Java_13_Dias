/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.util.Scanner;

import datos.EstudianteDAO;
import dominio.Estudiante;
import java.util.List;

/**
 *
 * @author franc
 */
public class PresentacionEstudiantes {
    
    public static void ejecutarPrograma() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        // Implementación del servicio
        // IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        // IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();
        
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(scanner);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                continuar(scanner);
            }
            saltarEspacio();
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("""
                           *** Sistema de Estudiantes ***
                           
                           1. Listar estudiantes
                           2. Buscar estudiante
                           3. Agregar estudiante
                           4. Modificar estudiante
                           5. Eliminar estudiante
                           6. Salir
                           """);
        System.out.print("Proporciona la opción: ");
    }
    
    private static boolean ejecutarOpciones(Scanner scanner) {
        boolean salir = false;
        int idEstudiante;
        String nombre;
        String apellido;
        String telefono;
        String email;
        Estudiante estudiante;
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        List<Estudiante> estudiantes;
        boolean resultado;
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch(opcion) {
            case 1 -> {
                mostrarSubtitulo("Listar estudiantes");
                estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
                continuar(scanner);
            }
            case 2 -> {
                mostrarSubtitulo("Buscar estudiante");
                idEstudiante = obtenerId(scanner);
                estudiante = new Estudiante(idEstudiante);
                resultado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (resultado) {
                    System.out.println("Estudiante encontrado: " + estudiante);
                } else {
                    System.out.println("No se encontró estudiante con ese id.");
                }
                continuar(scanner);
            }
            case 3 -> {
                mostrarSubtitulo("Agregar estudiante");
                nombre = obtenerDato(scanner, "nombre");
                apellido = obtenerDato(scanner, "apellido");
                telefono = obtenerDato(scanner, "telefono");
                email = obtenerDato(scanner, "email");
                estudiante = new Estudiante(nombre, apellido, telefono, email);
                resultado = estudianteDAO.agregarEstudiante(estudiante);
                if (resultado) {
                    System.out.println("Estudiante agregado: " + estudiante);
                } else {
                    System.out.println("No se pudo agregar al estudiante.");
                }
                continuar(scanner);
            }
            case 4 -> {
                mostrarSubtitulo("Modificar estudiante");
                idEstudiante = obtenerId(scanner);
                estudiante = new Estudiante(idEstudiante);
                resultado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (resultado) {
                    nombre = obtenerDato(scanner, "nombre");
                    apellido = obtenerDato(scanner, "apellido");
                    telefono = obtenerDato(scanner, "telefono");
                    email = obtenerDato(scanner, "email");
                    estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                    resultado = estudianteDAO.modificarEstudiante(estudiante);
                    if (resultado) {
                        System.out.println("Estudiante modificado: " + estudiante);
                    } else {
                        System.out.println("No se pudo modificar al estudiante.");
                    }
                } else {
                    System.out.println("No se encontró estudiante con ese id.");
                }
                continuar(scanner);
            }
            case 5 -> {
                mostrarSubtitulo("Eliminar estudiante");
                idEstudiante = obtenerId(scanner);
                estudiante = new Estudiante(idEstudiante);
                resultado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (resultado) {
                    boolean confirmado = confirmarEliminacion(scanner);
                    if (confirmado) {
                        resultado = estudianteDAO.eliminarEstudiante(estudiante);
                        if (resultado) {
                            System.out.println("Estudiante eliminado.");
                        } else {
                            System.out.println("No se pudo eliminar al estudiante.");
                        }
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                } else {
                    System.out.println("No se encontró estudiante con ese id.");
                }
                continuar(scanner);
            }
            case 6 -> {
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
    
    private static int obtenerId(Scanner scanner) {
        System.out.println("Ingrese el id del estudiante: ");
        return Integer.parseInt(scanner.nextLine());
    }
    
    private static String obtenerDato(Scanner scanner, String dato) {
        System.out.println("Ingrese el " + dato + ": ");
        return scanner.nextLine();
    }
    
    private static boolean confirmarEliminacion(Scanner scanner) {
        System.out.println("Esta acción es irreversible, ¿desea proceder? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.toLowerCase().equals("s")) {
            return true;
        }
        return false;
    }
    
    private static void mostrarSubtitulo(String texto) {
        System.out.println("## " + texto + ":");
    }
    
    private static void continuar(Scanner scanner) {
        System.out.print("Presione una tecla para continuar...");
        scanner.nextLine();
    }
    
    private static void saltarEspacio() {
        System.out.println("");
    }
}
