package tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store TaskList
 */
public class TaskList {

    private List<Task> list;

    /**
     * Initialise tasklist with local data.
     *
     * @param loadedData List of tasks to store into tasklist.
     */
    public TaskList(List<Task> loadedData) {
        this.list = loadedData;
    }

    /**
     * Initialise empty tasklist.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task Task to be added.
     * @param saved True if the task to added should be pre-marked as done, otherwise False.
     */
    public String add(Task task, boolean saved) {
        this.list.add(task);
        String reply = "I've added the task: \n" + task
                + "\n Now you have " + this.list.size() + " tasks in the list";
        if (!saved) {
            return reply;
        } else {
            return "Tasks Loaded";
        }
    }

    /**
     * Lists out all the tasks in the task list.
     */
    public String listOut() {
        StringBuilder reply = new StringBuilder();
        for (int i = 1; i < this.list.size(); i++) {
            reply.append(i).append(".").append(this.list.get(i - 1)).append("\n");
        }
        reply.append(this.list.size()).append(".").append(this.list.get(this.list.size() - 1));
        return reply.toString();
    }

    /**
     * Marks the specified task as done.
     *
     * @param index Index of task to be marked as done.
     */
    public String markTask(int index) {
        this.list.get(index).mark();
        String reply = "You have marked the following task as done!\n" + this.list.get(index);
        return reply;
    }

    /**
     * Marks the specified task as undone.
     *
     * @param index Index of task to be marked as done.
     */
    public String unmarkTask(int index) {
        this.list.get(index).unmark();
        String reply = "You have unmarked the following task!\n" + this.list.get(index);
        return reply;
    }

    /**
     * Deletes the specified task.
     *
     * @param index Index of task to be deleted.
     */
    public String delete(int index) {
        Task deleted = this.list.get(index);
        this.list.remove(index);
        String reply = "Let's go deleting!\nDeleted task " + deleted;
        return reply;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.list;
    }

    /**
     * Filter tasklist by keyword.
     *
     * @param word Keyword to filter by.
     * @return Filtered list.
     */
    public List<Task> filterByWord(String word) {
        return this.list.stream().filter(task -> task.containsWord(word)).toList();
    }
}
