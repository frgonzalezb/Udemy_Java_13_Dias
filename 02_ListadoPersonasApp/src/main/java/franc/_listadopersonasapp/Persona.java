/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franc._listadopersonasapp;

/**
 *
 * @author franc
 */
public class Persona {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private static int numeroPersonas = 0;
    
    // Constructor vacío
    public Persona() {
        this.id = ++Persona.numeroPersonas; // Autoincrementable
    }
    
    // Constructor con argumentos
    public Persona(String nombre, String telefono, String email) {
        this(); // Opc: Llamar explícitamnete al constructor vacío (DRY)
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        // return "Persona{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + '}';
        String tagId = "Id: " + id;
        String tagNombre = "Nombre: " + nombre;
        String tagTelefono = "Telefono: " + telefono;
        String tagEmail = "Email: " + email;
        
        return tagId + "\n\t" + tagNombre + "\n\t" + tagTelefono + "\n\t" + tagEmail;
    }
    
    public static void main(String[] args) {
        Persona persona1 = new Persona("Juan Pérez", "987654321", "jperez@gmail.com");
        System.out.println(persona1);
    }
}
