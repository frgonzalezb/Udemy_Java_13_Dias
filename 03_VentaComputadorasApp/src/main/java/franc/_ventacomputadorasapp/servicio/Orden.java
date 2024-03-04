/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franc._ventacomputadorasapp.servicio;

import java.util.List;
import java.util.ArrayList;

import franc._ventacomputadorasapp.modelo.Computadora;

/**
 *
 * @author franc
 */
public class Orden {
    private final int idOrden;
    private final List<Computadora> computadoras;
    private static int contadorOrdenes;
    
    public Orden() {
        computadoras = new ArrayList<>();
        this.idOrden = ++contadorOrdenes;
    }
    
    public void agregarComputadora(Computadora computadora) {
        computadoras.add(computadora);
    }
    
    public void mostrarOrden() {
        System.out.println("Orden #: " + idOrden);
        System.out.println("Total computadoras: " + computadoras.size());
        System.out.println("");
        computadoras.forEach(System.out::println);
    }
}
