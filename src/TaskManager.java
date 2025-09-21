import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    int id = 1;

    private int getNextId() {
        return id++;
    }

    public Task addTask(Task task) {
        task.setId(getNextId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic addEpic(Epic epic) {
        epic.setId(getNextId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        subtask.setStatus(Status.NEW);
        subtasks.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        epics.get(epicId).addSubtask(subtask);
        return subtask;
    }

    public Task updateTask(Task task) {
        int taskId = task.getId();
        tasks.put(taskId, task);
        return task;
    }

    public Epic updateEpic(Epic epic) {
        int epicId = epic.getId();
        epics.put(epicId, epic);
        return epic;
    }

    public Subtask updateSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        subtasks.put(subtaskId, subtask);
        int epicId = subtask.getEpicId();
        updateEpicStatus(epics.get(epicId));
        return subtask;
    }

    private void updateEpicStatus(Epic epic) {
        boolean epicStatusNew = true;
        boolean epicStatusDone = true;
        ArrayList<Integer> subtaskIdList = epic.getSubtaskIdList();

        for (Integer id : subtaskIdList) {
            if (subtasks.get(id).getStatus() == Status.NEW) {
                epicStatusDone = false;
            } else if (subtasks.get(id).getStatus() == Status.DONE) {
                epicStatusNew = false;
            } else {
                epicStatusNew = false;
                epicStatusDone = false;
            }
        }
        if (!epicStatusNew && !epicStatusDone) {
            epic.setStatus(Status.IN_PROGRESS);
        } else if (epicStatusNew) {
            epic.setStatus(Status.NEW);
        } else if (epicStatusDone) {
            epic.setStatus(Status.DONE);
        }
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpicById(int epicId) {
        return epics.get(epicId);
    }

    public Subtask getSubtaskById(int subtaskId) {
        return subtasks.get(subtaskId);
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasksList = new ArrayList<>(tasks.values());
        return tasksList;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicsList = new ArrayList<>(epics.values());
        return epicsList;
    }

    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtasksList = new ArrayList<>(subtasks.values());
        return subtasksList;
    }

    public ArrayList<Subtask> getSubtaskListByEpic(Epic epic) {
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        ArrayList<Integer> subtaskIdList = epic.getSubtaskIdList();
        if (subtaskIdList.isEmpty()) {
            return null;
        } else {
            for (Integer subtaskId : subtaskIdList) {
                subtaskList.add(subtasks.get(subtaskId));
            }
        }
        return subtaskList;
    }

    public void removeAllTasks() {
        removeEpics();
        removeTasks();
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeSubtasks() {
        subtasks.clear();
    }

    public void removeEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeTaskById(int taskId) {
        tasks.remove(taskId);
    }

    public void removeEpicById(int epicId) {
        ArrayList<Integer> subtaskIdList = epics.get(epicId).getSubtaskIdList();
        for (Integer subtaskId : subtaskIdList) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }

    public void removeSubtaskById(int subtaskId) {
        int epicId = subtasks.get(subtaskId).getEpicId();
        subtasks.remove(subtaskId);
        ArrayList<Integer> subtaskIdList = epics.get(epicId).getSubtaskIdList();
        subtaskIdList.remove(Integer.valueOf(5));
        updateEpicStatus(epics.get(epicId));
    }
}
