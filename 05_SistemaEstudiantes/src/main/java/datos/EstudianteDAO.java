/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import conexion.DatabaseConnection;
import dominio.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
// DAO - Data Access Object
public class EstudianteDAO {
    
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;   // Prepara secuencia SQL hacia BD
        ResultSet rs;           // Almacena resultado de BD
        Connection conn = DatabaseConnection.getConnection();
        String sqlStatement = "SELECT * FROM estudiante ORDER BY id_estudiante;";
        
        try {
            ps = conn.prepareStatement(sqlStatement);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono")); 
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e);
            }
        }
        
        return estudiantes;
    }
    
}
