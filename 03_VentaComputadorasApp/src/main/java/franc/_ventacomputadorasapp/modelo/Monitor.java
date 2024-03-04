/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franc._ventacomputadorasapp.modelo;

/**
 *
 * @author franc
 */
public class Monitor {
    private final int idMonitor;
    private String marca;
    private double tamanio;
    private static int contadorMonitores;
    
    private Monitor() {
        idMonitor = ++contadorMonitores;
    }
    
    public Monitor(String marca, double tamanio) {
        this();
        this.marca = marca;
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Id: " + idMonitor 
                + "\n\t\tMarca: " + marca 
                + "\n\t\tTamaño: " + tamanio + " pulgadas";
    }
}
