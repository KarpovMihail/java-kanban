import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIdList;

    public Epic(String name, String description) {
        super(name, description);
        subtaskIdList = new ArrayList<>();
    }

    public Epic(String name, String description, Status status, int id, ArrayList<Integer> subtaskIdList) {
        super(name, description, status, id);
        this.subtaskIdList = subtaskIdList;
    }

    public void addSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        subtaskIdList.add(subtaskId);
    }

    public void clearSubtaskIdList() {
        subtaskIdList.clear();
    }

    public ArrayList<Integer> getSubtaskIdList() {
        return subtaskIdList;
    }

    public void setSubtaskIdList(ArrayList<Integer> subtaskIdList) {
        this.subtaskIdList = subtaskIdList;
    }   // Вопрос необходимости

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskList=" + subtaskIdList +
                "} " + super.toString();
    }
}
