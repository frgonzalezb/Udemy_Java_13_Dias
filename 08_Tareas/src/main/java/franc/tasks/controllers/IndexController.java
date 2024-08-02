package franc.tasks.controllers;

import franc.tasks.models.Priority;
import franc.tasks.models.Status;
import franc.tasks.models.Task;
import franc.tasks.services.TaskService;

import franc.tasks.utils.TranslationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    private Long selectedTaskId; // hidden for the user

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
        priorityCol.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            return new SimpleStringProperty(TranslationUtil.getTranslatedPriority(task.getPriority()));
        });

        statusCol.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            return new SimpleStringProperty(TranslationUtil.getTranslatedStatus(task.getStatus()));
        });
    }

    private void populateComboBoxes() {
        ObservableList<String> priorityItems = FXCollections.observableArrayList(
                Arrays.stream(Priority.values())
                        .map(TranslationUtil::getTranslatedPriority)
                        .collect(Collectors.toList())
        );
        priorityComboBox.setItems(priorityItems);

        ObservableList<String> statusItems = FXCollections.observableArrayList(
                Arrays.stream(Status.values())
                        .map(TranslationUtil::getTranslatedStatus)
                        .collect(Collectors.toList())
        );
        statusComboBox.setItems(statusItems);
    }

    private void listTasks() {
        tasks.clear();
        // Fetch all tasks and filter out the ones with isDeleted set to true
        List<Task> activeTasks = taskService.listTasks()
                .stream()
                .filter(task -> !task.isDeleted())
                .toList();

        tasks.addAll(activeTasks);
        taskTable.setItems(tasks);
        logger.info("All tasks have been listed.");
    }

    public void addTask() {
        if (selectedTaskId != null) {
            logger.warn("Please clear the form before adding a new task!");
            showErrorMessage("Error", "Por favor, limpia el formulario antes de agregar una nueva tarea.");
            ClearFormButton.requestFocus();
            return;
        }
        String title = titleTextField.getText();
        String worker = workerTextField.getText();
        String selectedPriorityTranslation = (String) priorityComboBox.getValue();
        Priority priority = TranslationUtil.getPriorityFromTranslation(selectedPriorityTranslation);
        String selectedStatusTranslation = (String) statusComboBox.getValue();
        Status status = TranslationUtil.getStatusFromTranslation(selectedStatusTranslation);

        if (!validateFields(title, worker, priority, status)) return;

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setWorkerInCharge(worker);
        newTask.setPriority(priority);
        newTask.setStatus(status);
        newTask.setDeleted(false);

        taskService.saveTask(newTask);
        logger.info("Task created: {}", newTask);
        showInformationMessage("Información", "Tarea creada.");
        clearForm();
        listTasks();
    }

    public void loadTaskToForm() {
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task == null) {
            return;
        }
        selectedTaskId = task.getId();
        titleTextField.setText(task.getTitle());
        workerTextField.setText(task.getWorkerInCharge());
        priorityComboBox.getSelectionModel().select(TranslationUtil.getTranslatedPriority(task.getPriority()));
        statusComboBox.getSelectionModel().select(TranslationUtil.getTranslatedStatus(task.getStatus()));
        logger.info("The selected task {} has been loaded to the form.", task);
    }

    public void editTask() {
        if (selectedTaskId == null) {
            logger.warn("Please select a task before editing it!");
            showErrorMessage("Error", "Por favor, selecciona una tarea antes de editarla.");
            taskTable.requestFocus();
            return;
        }
        Task task = taskService.getTask(selectedTaskId);
        if (task == null) {
            return;
        }
        String title = titleTextField.getText();
        String worker = workerTextField.getText();
        Priority priority = TranslationUtil.getPriorityFromTranslation((String) priorityComboBox.getValue());
        Status status = TranslationUtil.getStatusFromTranslation((String) statusComboBox.getValue());

        if (!validateFields(title, worker, priority, status)) return;

        task.setTitle(title);
        task.setWorkerInCharge(worker);
        task.setPriority(priority);
        task.setStatus(status);
        taskService.saveTask(task);
        logger.info("Task edited: {}", task);
        showInformationMessage("Información", "Tarea editada.");
        clearForm();
        listTasks();
    }

    public void deleteTask() {
        if (selectedTaskId == null) {
            logger.warn("Please select a task before deleting it!");
            showErrorMessage("Error", "Por favor, selecciona una tarea antes de eliminarla.");
            taskTable.requestFocus();
            return;
        }
        Task task = taskService.getTask(selectedTaskId);
        if (task == null) {
            return;
        }
        if(!showConfirmationMessage("Confirmar", "¿Seguro que quieres borrar esta tarea?")) {
            logger.info("Task deletion has been cancelled.");
            return;
        }
        task.setDeleted(true);
        taskService.saveTask(task);
        logger.info("Task deleted: {}", task);
        showInformationMessage("Información", "Tarea eliminada.");
        clearForm();
        listTasks();
    }

    public void clearForm() {
        selectedTaskId = null;
        titleTextField.clear();
        workerTextField.clear();
        priorityComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
        logger.info("Form has been cleared.");
    }

    private boolean validateFields(String title, String worker, Priority priority, Status status) {
        if (title.isEmpty()) {
            logger.warn("Please enter a title before adding or editing a task!");
            showErrorMessage("Error", "Por favor, introduce un nombre para la tarea.");
            titleTextField.requestFocus();
            return false;
        }
        if (worker.isEmpty()) {
            logger.warn("Please enter a worker before adding or editing a task!");
            showErrorMessage("Error", "Por favor, introduce un responsable para la tarea.");
            workerTextField.requestFocus();
            return false;
        }
        if (priority == null) {
            logger.warn("Please select a priority before adding or editing a task!");
            showErrorMessage("Error", "Por favor, selecciona una prioridad para la tarea.");
            priorityComboBox.requestFocus();
            return false;
        }
        if (status == null) {
            logger.warn("Please select a status before adding or editing a task!");
            showErrorMessage("Error", "Por favor, selecciona un estado para la tarea.");
            statusComboBox.requestFocus();
            return false;
        }
        return true;
    }

    public void showInformationMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean showConfirmationMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().get() == ButtonType.OK;
    }
}
