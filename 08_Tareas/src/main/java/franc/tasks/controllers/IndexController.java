package franc.tasks.controllers;

import franc.tasks.models.Task;
import franc.tasks.services.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class IndexController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Long> idCol;
    @FXML
    private TableColumn<Task, String> titleCol;
    @FXML
    private TableColumn<Task, String> workerCol;
    @FXML
    private TableColumn<Task, String> priorityCol;
    @FXML
    private TableColumn<Task, String> statusCol;

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configColumns();
        listTasks();
    }

    private void configColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        workerCol.setCellValueFactory(new PropertyValueFactory<>("workerInCharge"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void listTasks() {
        logger.info("List tasks");
        tasks.clear();
        // Fetch all tasks and filter out the ones with isDeleted set to true
        List<Task> activeTasks = taskService.listTasks()
                .stream()
                .filter(task -> !task.isDeleted())
                .toList();

        tasks.addAll(activeTasks);
        taskTable.setItems(tasks);
    }
}
