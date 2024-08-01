package franc.tasks.controllers;

import franc.tasks.models.Priority;
import franc.tasks.models.Status;
import franc.tasks.models.Task;
import franc.tasks.services.TaskService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    public TextField titleTextField;
    public TextField workerTextField;
    public ComboBox priorityComboBox;
    public ComboBox statusComboBox;
    public Button addTaskButton;
    public Button editTaskButton;
    public Button deleteTaskButton;
    public Button ClearFormButton;

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
        populateComboBoxes();
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

    private void populateComboBoxes() {
        priorityComboBox.setItems(FXCollections.observableArrayList(Priority.values()));
        statusComboBox.setItems(FXCollections.observableArrayList(Status.values()));
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

    public void addTask() {
        String title = titleTextField.getText();
        String worker = workerTextField.getText();
        Priority priority = (Priority) priorityComboBox.getValue();
        Status status = (Status) statusComboBox.getValue();

        if (!validateFields(title, worker, priority, status)) return;

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setWorkerInCharge(worker);
        newTask.setPriority(priority);
        newTask.setStatus(status);
        newTask.setDeleted(false);

        taskService.saveTask(newTask);
        listTasks();
    }

    public void editTask(ActionEvent actionEvent) {
    }

    public void deleteTask(ActionEvent actionEvent) {
    }

    public void clearForm(ActionEvent actionEvent) {
    }

    public void clearForm() {
        titleTextField.clear();
        workerTextField.clear();
        priorityComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
    }

    private boolean validateFields(String title, String worker, Priority priority, Status status) {
        if (title.isEmpty()) {
            logger.warn("Please enter a title before adding a task!");
            showErrorMessage("Error", "Por favor, introduce un nombre para la tarea.");
            titleTextField.requestFocus();
            return false;
        }
        if (worker.isEmpty()) {
            logger.warn("Please enter a worker before adding a task!");
            showErrorMessage("Error", "Por favor, introduce un responsable para la tarea.");
            workerTextField.requestFocus();
            return false;
        }
        if (priority == null) {
            logger.warn("Please select a priority before adding a task!");
            showErrorMessage("Error", "Por favor, selecciona una prioridad para la tarea.");
            priorityComboBox.requestFocus();
            return false;
        }
        if (status == null) {
            logger.warn("Please select a status before adding a task!");
            showErrorMessage("Error", "Por favor, selecciona un estado para la tarea.");
            statusComboBox.requestFocus();
            return false;
        }
        return true;
    }

    public void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
