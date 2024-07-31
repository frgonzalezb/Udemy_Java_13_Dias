package franc.tasks.services;

import franc.tasks.models.Task;

import java.util.List;

public interface ITaskService {
    public List<Task> listTasks();
    public Task getTask(Long id);
    public void saveTask(Task task); // create or update
    public void deleteTask(Long id); // soft deleting only!
}
