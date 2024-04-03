package franc._sistemaestudiantes._SistemaEstudiantes;

import franc._sistemaestudiantes._SistemaEstudiantes.servicio.EstudianteServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
