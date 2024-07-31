package franc.tasks;

import franc.tasks.views.TaskView;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TasksApplication.class, args);
		Application.launch(TaskView.class, args);
	}

}
