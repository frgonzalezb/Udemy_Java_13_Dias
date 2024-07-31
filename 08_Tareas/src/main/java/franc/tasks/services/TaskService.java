package franc.tasks.services;

import franc.tasks.models.Task;
import franc.tasks.repositories.TaskRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Override
    public List<Task> listTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public void saveTask(Task task) {
        // Create or update
        taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        // Soft deleting only!
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null) {
            return;
        }
        task.setDeleted(true);
        taskRepo.save(task);
    }
}
