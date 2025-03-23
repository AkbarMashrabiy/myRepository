package ui;


import task.Task;
import task.TaskService;
import user.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserUi {
    public static Scanner scnInt = new Scanner(System.in);
    public static Scanner scnStr = new Scanner(System.in);
    public static TaskService taskService = new TaskService();
    public static User currentUser;
    public static void userUi(User user) {
      currentUser=user;
        boolean isExited= false;

        while (!isExited) {
            System.out.print("""
                    1 Task yaratish
                    2 tasklar ro'yxatini ko'rish
                    3 Taskni taxrirlash
                    4 Taskni o'chirish
                    \s
                    0 Exit
                    >>>\s""");

            switch (scnInt.nextInt()) {
                case 1: {
                    createTask();
                    break;
                }
                case 2:{
                    showTasks();
                    break;
                }
                case 3: {
                    editTask();
                    break;
                }
                case 4: {
                    removeTask();
                    break;
                }
                case 0: {
                    isExited = true;
                    break;
                }
                default: {
                    System.out.println("Invalid command");
                }
            }
        }
    }

    private static void editTask() {
        showTasks();
        System.out.println("Task raqamini kiriting");
        int index = scnInt.nextInt();

        List<Task> userTasks = taskService.getTasksByUserId(currentUser.getId());

        for (int i = 0; i < userTasks.size(); i++) {
            if (i == index){
                System.out.print("Edit task name: ");
                String taskName = scnStr.nextLine();

                System.out.print("Edit task description: ");
                String description = scnStr.nextLine();

                userTasks.get(index).setName(taskName);
                userTasks.get(index).setDescription(description);
                return;
            }
        }
    }

    private static void removeTask() {
        showTasks();
        System.out.println("Task raqamini kiriting");
        int index = scnInt.nextInt();
        List<Task> userTasks = taskService.getTasksByUserId(currentUser.getId());

        for (int i = 0; i < userTasks.size(); i++) {
            if (i == index){
                userTasks.get(index).setAvailable(false);
            }
        }


    }

    public static void createTask(){
        System.out.print("Enter task name: ");
        String taskName = scnStr.nextLine();

        System.out.print("Enter task description: ");
        String description = scnStr.nextLine();

        Task task = new Task(UUID.randomUUID(), taskName, description, currentUser.getId());
        taskService.add(task);
        System.out.println("Task added!");
    }

    public static void showTasks(){
        List<Task> userTasks = taskService.getTasksByUserId(currentUser.getId());
        for (int i = 0; i < userTasks.size(); i++){
            System.out.println(i + " -> " + userTasks.get(i));
        }
    }

}


