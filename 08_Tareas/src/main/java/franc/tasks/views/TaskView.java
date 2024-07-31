package franc.tasks.views;

import franc.tasks.TasksApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class TaskView extends Application {

    private ConfigurableApplicationContext applicationContext;

//    public static void main(String[] args) {
//        launch(args);
//    }

    public void init() {
        applicationContext = new SpringApplicationBuilder(TasksApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(TasksApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Tasks");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
