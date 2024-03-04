/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package franc._ventacomputadorasapp;

import franc._ventacomputadorasapp.modelo.Computadora;
import franc._ventacomputadorasapp.modelo.Monitor;
import franc._ventacomputadorasapp.modelo.Teclado;
import franc._ventacomputadorasapp.modelo.Raton;
import franc._ventacomputadorasapp.servicio.Orden;

/**
 *
 * @author franc
 */
public class App {

    public static void main(String[] args) {
        // Crear objetos
        Raton ratonLogitech = new Raton("Bluetooth", "Logitech");
        Teclado tecladoRedDragon = new Teclado("USB", "RedDragon");
        Monitor monitorHP = new Monitor("HP", 27);
        
        // Crear un objeto de tipo Computadora
        Computadora compuCustom = 
                new Computadora(
                        "Computadora Custom", 
                        monitorHP, 
                        tecladoRedDragon, 
                        ratonLogitech
                );
        
        // Crear otro objeto Computadora
        Monitor monitorAcer = new Monitor("Acer", 20.5);
        Teclado tecladoAcer = new Teclado("USB", "Acer");
        Raton ratonAcer = new Raton("USB", "Acer");
        Computadora compuAcer = 
                new Computadora(
                        "Computadora Acer", 
                        monitorAcer, 
                        tecladoAcer, 
                        ratonAcer
                );
        
        // Creamos una orden
        Orden orden1 = new Orden();
        orden1.agregarComputadora(compuCustom);
        orden1.agregarComputadora(compuAcer);
        orden1.mostrarOrden();
    }
}
