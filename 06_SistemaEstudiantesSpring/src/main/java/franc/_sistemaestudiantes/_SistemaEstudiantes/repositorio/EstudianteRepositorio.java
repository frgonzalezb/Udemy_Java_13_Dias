package franc._sistemaestudiantes._SistemaEstudiantes.repositorio;

import franc._sistemaestudiantes._SistemaEstudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {

}
