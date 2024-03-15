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
    
    public List<Estudiante> listarEstudiantes() {
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
            System.out.println("Ocurrió un error al listar estudiante: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        
        return estudiantes;
    }
    
    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = DatabaseConnection.getConnection();
        String sqlStatement = "SELECT * FROM estudiante WHERE id_estudiante = ?;";
        
        try {
            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1, estudiante.getIdEstudiante()); // Parámetro
            rs = ps.executeQuery();
            
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono")); 
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar estudiante: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        
        return false;
    }
    
    public boolean agregarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conn = DatabaseConnection.getConnection();
        String sqlStatement = "INSERT INTO estudiante(nombre, apellido, telefono, email) "
                            + "VALUES(?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al agregar estudiante: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        
        return false;
    }
    
    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conn = DatabaseConnection.getConnection();
        String sqlStatement = "UPDATE estudiante " 
                            + "SET nombre=?, apellido=?, telefono=?, email=? "
                            + "WHERE id_estudiante = ?;";
        
        try {
            ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al modificar estudiante: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        
        return false;
    }
    
    public boolean eliminarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conn = DatabaseConnection.getConnection();
        String sqlStatement = "DELETE FROM estudiante " 
                            + "WHERE id_estudiante = ?;";
        
        try {
            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al eliminar estudiante: " + ex.getMessage());
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        
        return false;
    }
    
}
