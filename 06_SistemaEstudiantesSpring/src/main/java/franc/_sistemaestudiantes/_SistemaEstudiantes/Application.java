package franc._sistemaestudiantes._SistemaEstudiantes;

import franc._sistemaestudiantes._SistemaEstudiantes.modelo.Estudiante;
import franc._sistemaestudiantes._SistemaEstudiantes.servicio.EstudianteServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	// Implementar logger para evitar uso de prints para debuggear
	// Véase archivo logback-spring.xml
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	// Atributo para salto de línea en consola (para cualquier SO)
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		// Levantar la fábrica de Spring
		SpringApplication.run(Application.class, args);
		logger.info("Aplicación finalizada.");
	}

	@Override
	public void run(String... args) throws Exception {
		// Aplicar aquí lógica de negocio / lógica de capa de presentación
		logger.info(nl + "Ejecutando método run de Spring..." + nl);
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(scanner);

			saltarEspacio();
		}
	}

	private void mostrarMenu() {
		saltarEspacio();
		logger.info("""
			*** Sistema de Estudiantes ***
			
			1. Listar estudiantes
			2. Buscar estudiante
			3. Agregar estudiante
			4. Modificar estudiante
			5. Eliminar estudiante
			6. Salir
			
			Proporciona la opción: 
	    	""");
	}

	private boolean ejecutarOpciones(Scanner scanner) {
		boolean salir = false;
		int idEstudiante;
		String nombre;
		String apellido;
		String telefono;
		String email;
		List<Estudiante> estudiantes;

		int opcion = Integer.parseInt(scanner.nextLine());

		switch(opcion) {
			case 1 -> {
				mostrarSubtitulo("Listar estudiantes");
				estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
				continuar(scanner);
			}
			case 2 -> {
				mostrarSubtitulo("Buscar estudiante");
				idEstudiante = obtenerId(scanner);
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					logger.info("Estudiante encontrado: " + estudiante + nl);
				} else {
					logger.info("No se encontró estudiante con ese id." + nl);
				}
				continuar(scanner);
			}
			case 3 -> {
				mostrarSubtitulo("Agregar estudiante");
				nombre = obtenerDato(scanner, "nombre");
				apellido = obtenerDato(scanner, "apellido");
				telefono = obtenerDato(scanner, "telefono");
				email = obtenerDato(scanner, "email");
				Estudiante estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				continuar(scanner);
			}
			case 4 -> {
				mostrarSubtitulo("Modificar estudiante");
				idEstudiante = obtenerId(scanner);
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					nombre = obtenerDato(scanner, "nombre");
					apellido = obtenerDato(scanner, "apellido");
					telefono = obtenerDato(scanner, "telefono");
					email = obtenerDato(scanner, "email");
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
				} else {
					logger.info("No se encontró estudiante con ese id." + nl);
				}
				continuar(scanner);
			}
			case 5 -> {
				mostrarSubtitulo("Eliminar estudiante");
				idEstudiante = obtenerId(scanner);
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					boolean confirmado = confirmarEliminacion(scanner);
					if (confirmado) {
						estudianteServicio.eliminarEstudiante(estudiante);
					} else {
						logger.info("Eliminación cancelada." + nl);
					}
				} else {
					logger.info("No se encontró estudiante con ese id." + nl);
				}
				continuar(scanner);
			}
			case 6 -> {
				logger.info(nl + "Hasta pronto..." + nl);
				salir = true;
			}
			default -> {
				logger.info(nl + "Opción inválida: " + nl);
				continuar(scanner);
			}
		}
		return salir;
	}

	private int obtenerId(Scanner scanner) {
		logger.info(nl + "Ingrese el id del estudiante: " + nl);
		return Integer.parseInt(scanner.nextLine());
	}

	private String obtenerDato(Scanner scanner, String dato) {
		logger.info(nl + "Ingrese el " + dato + ": " + nl);
		return scanner.nextLine();
	}

	private boolean confirmarEliminacion(Scanner scanner) {
		logger.info(nl + "Esta acción es irreversible, ¿desea proceder? (s/n): " + nl);
		String respuesta = scanner.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			return true;
		}
		return false;
	}

	private void mostrarSubtitulo(String texto) {
		logger.info(nl + "## " + texto + ":" + nl);
	}

	private void continuar(Scanner scanner) {
		logger.info(nl + "Presione una tecla para continuar..." + nl);
		scanner.nextLine();
	}

	private void saltarEspacio() {
		logger.info(nl);
	}
}
