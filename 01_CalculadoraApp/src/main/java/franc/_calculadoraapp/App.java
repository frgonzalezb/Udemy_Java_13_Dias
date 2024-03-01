/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package franc._calculadoraapp;

import java.util.Scanner;

/**
 *
 * @author franc
 */
public class App {

    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        int operacion;
        
        while (true) {
            mostrarMenu();
            
            try {
                operacion = Integer.parseInt(consola.nextLine());
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                presionarTecla(consola);
                continue;
            }
            
            if (operacion >= 1 && operacion <= 5) {
                ejecutarOperacion(operacion, consola);
            } else if (operacion == 0) {
                System.out.println("¡Hasta pronto!");
                consola.close();
                break;
            } else {
                System.out.println("Opción inválida: " + operacion);
                presionarTecla(consola);
            }
        }
    }
    
    private static void mostrarMenu() {
        String titulo;
        String menu;
        String opcionTexto;
        
        titulo = "*** Aplicación Calculadora ***";
        menu = """
               1. Suma
               2. Resta
               3. Multiplicación
               4. División

               0. Salir

               """;
        opcionTexto = "Operación a realizar: ";
            
        System.out.println(titulo);
        System.out.println(menu);
        System.out.println(opcionTexto);
    }
    
    private static void ejecutarOperacion(int operacion, Scanner scanner) {
        double operando1;
        double operando2;
        double resultado;
        
        System.out.println("Valor operando 1: ");
        operando1 = Double.parseDouble(scanner.nextLine());

        System.out.println("Valor operando 2: ");
        operando2 = Double.parseDouble(scanner.nextLine());

        switch (operacion) {
            case 1 -> { // Suma
                resultado = operando1 + operando2;
                System.out.printf("%.2f + %.2f = " + resultado, operando1, operando2);
                presionarTecla(scanner);
            }
            case 2 -> { // Resta
                resultado = operando1 - operando2;
                System.out.printf("%.2f - %.2f = " + resultado, operando1, operando2);
                presionarTecla(scanner);
            }
            case 3 -> { // Multiplicación
                resultado = operando1 * operando2;
                System.out.printf("%.2f x %.2f = " + resultado, operando1, operando2);
                presionarTecla(scanner);
            }
            case 4 -> { // División
                if (operando2 == 0) {
                    System.out.println("¡No se puede dividir por cero!");
                    presionarTecla(scanner);
                } else {
                    resultado = operando1 / operando2;
                    System.out.printf("%.2f / %.2f = " + resultado, operando1, operando2);
                    presionarTecla(scanner);
                }
            }
            default -> { // Errónea
                System.out.println("Opción inválida: " + operacion);
                presionarTecla(scanner);
            }
        }
    }
    
    private static void presionarTecla(Scanner scanner) {
        String texto;
        texto = "Presione una tecla para continuar...";
        System.out.println("\n" + texto + "\n");
        scanner.nextLine();
    }
}
