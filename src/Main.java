public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("task1", "description1");
        Task task2 = new Task("task2", "description2");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Epic epic1 = new Epic("epic1", "description1");
        Epic epic2 = new Epic("epic2", "description2");
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        Subtask subtask1 = new Subtask("subtask1,1", "description1,1", epic1.getId());
        Subtask subtask2 = new Subtask("subtask1,2", "description1,2", epic1.getId());
        Subtask subtask3 = new Subtask("subtask2,1", "description2,1", epic2.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        Task task1Update = new Task("task1Update", "description1Update", Status.IN_PROGRESS, task1.getId());
        Task task2Update = new Task("task2Update", "description2Update", Status.DONE, task2.getId());

        taskManager.updateTask(task1Update);
        taskManager.updateTask(task2Update);

        Subtask subtask1Update = new Subtask("subtask1Update", "description1Update", Status.IN_PROGRESS, subtask1.getId(), subtask1.getEpicId());
        Subtask subtask2Update = new Subtask("subtask2Update", "description2Update", Status.DONE, subtask2.getId(), subtask2.getEpicId());
        Subtask subtask3Update = new Subtask("subtask3Update", "description3Update", Status.DONE, subtask3.getId(), subtask3.getEpicId());

        taskManager.updateSubtask(subtask1Update);
        taskManager.updateSubtask(subtask2Update);
        taskManager.updateSubtask(subtask3Update);

        System.out.println();
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        taskManager.removeTaskById(1);
        taskManager.removeEpicById(4);
        taskManager.removeSubtaskById(5);

        System.out.println();
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
    }
}
