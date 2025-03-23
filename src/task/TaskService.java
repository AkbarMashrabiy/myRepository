package task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ui.UserUi.currentUser;

public class TaskService {
    public static List<Task>  tasks = new ArrayList<>();

    public void add(Task task){
        tasks.add(task);
    }
    public List<Task> getTasksByUserId(UUID userId){
        List<Task> tempTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getUserId().equals(userId) && task.isAvailable()){
                tempTasks.add(task);
            }
        }
        return tempTasks;
    }
    public boolean remove(UUID userId, UUID id){
        for (Task task : tasks) {
            if (task.getUserId().equals(userId) && task.getId().equals(id)){
                task.setAvailable(false);
                return true;
            }
        }
        return false;
    }


}
