package eu.ase.angedasincronizareonline.utils;

public class Task {
    private String task;
    private int importance;

    public Task(String task, int importance) {
        this.task = task;
        this.importance = importance;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", importance=" + importance +
                '}';
    }
}
