/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franc._ventacomputadorasapp.modelo;

/**
 *
 * @author franc
 */
public class Computadora {
    private int idComputadora;
    private String nombre;
    private Monitor monitor;
    private Teclado teclado;
    private Raton raton;
    private static int contadorComputadoras;
    
    private Computadora() {
        idComputadora = ++contadorComputadoras;
    }
    
    public Computadora(
            String nombre, 
            Monitor monitor, 
            Teclado teclado, 
            Raton raton
            ) {
        this();
        this.nombre = nombre;
        this.monitor = monitor;
        this.teclado = teclado;
        this.raton = raton;
    }

    @Override
    public String toString() {
        // return "Computadora{" + "idComputadora=" + idComputadora + ", nombre=" + nombre + ", monitor=" + monitor + ", teclado=" + teclado + ", raton=" + raton + '}';
        String tagIdComputadora = "Id: " + idComputadora;
        String tagNombreComputadora = "Nombre: " + nombre;
        
        return tagIdComputadora 
                + "\n\t" 
                + tagNombreComputadora 
                + "\n\tMonitor:"
                + "\n\t\t" + monitor
                + "\n\tTeclado:"
                + "\n\t\t" + teclado
                + "\n\tRatón:"
                + "\n\t\t" + raton;
    }
}
