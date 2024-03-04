/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franc._ventacomputadorasapp.modelo;

/**
 *
 * @author franc
 */
public class Teclado extends DispositivoEntrada {
    private final int idTeclado;
    private static int contadorTeclados;
    
    public Teclado(String tipoEntrada, String marca) {
        super(tipoEntrada, marca);
        idTeclado = ++contadorTeclados;
    }
    
    @Override
    public String toString() {
        // return "Teclado{" + "idTeclado=" + idTeclado + '}' + super.toString();
        return "Id: " + idTeclado + super.toString();
    }
}
