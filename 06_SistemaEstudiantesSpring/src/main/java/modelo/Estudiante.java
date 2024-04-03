// NOTAS DE LA CLASE:

// Las librerías jakarta y lombok brindan anotaciones (decoradores) para evitar el boilerplate

// @Entity indica que la clase es una tabla de base de datos (para objetos persistentes)
// @Data implementa los getters/setters
// @NoArgsConstructor implementa el constructor vacío
// @AllArgsConstructor implementa el constructor con parámetros
// @ToString implementa el método del mismo nombre

// @Id indica claramente el atributo destinado a id
// @GeneratedValue y GenerationType.IDENTITY son vitales para columnas id auto-incrementables!!

package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

}
