package Nave;

import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a list of {@code Task} objects.
 * It provides methods for adding, listing, marking, unmarking, and deleting tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>
     * Initializes an empty list of tasks.
     * </p>
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a {@code Task} to the task list.
     *
     * @param task the {@code Task} to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a message indicating the number of tasks currently in the list.
     *
     * @return a string message stating the number of tasks
     */
    public String countTasks() {
        return "You have " + tasks.size() + " tasks now.";
    }

    /**
     * Returns a string listing all tasks in the list.
     * <p>
     * If the list is empty, returns a message indicating that no tasks have been added.
     * Otherwise, returns a formatted list of all tasks with their index.
     * </p>
     *
     * @return a string containing all tasks in the list
     */
    public String listItems() {
        //Check if there are any tasks
        if (tasks.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : tasks) {
            String currItem = tasks.indexOf(t) + 1 + "." + t + "\n";
            fullList.append(currItem);
        }
        fullList.append("That's all, you can do this!");
        return fullList.toString();
    }

    /**
     * Marks a task as completed based on its position in the list.
     *
     * @param place the position (1-based index) of the task to be marked
     * @return a message indicating whether the task was successfully marked or already marked
     */
    public String markItem(int place) {
        try {
            Task curr = tasks.get(place - 1);
            return curr.mark()
                ? "Well done! I have checked this item off the list:\n" + curr
                : "This item was already marked as done previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    /**
     * Unmarks a task as completed based on its position in the list.
     *
     * @param place the position (1-based index) of the task to be unmarked
     * @return a message indicating whether the task was successfully unmarked or already unchecked
     */
    public String unmarkItem(int place) {
        try {
            Task curr = tasks.get(place - 1);
            return curr.unmark()
                ? "Oh no! It's ok, I will uncheck this item for now:\n" + curr
                : "This item was already unchecked previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    /**
     * Deletes a task from the list based on its position.
     *
     * @param place the position (1-based index) of the task to be deleted
     * @return a message indicating that the task was deleted, along with the updated count of tasks
     */
    public String deleteItem(int place) {
        try {
            Task deleted = tasks.remove(place - 1);
            return "I have taken this item off the list:\n" + deleted +
                    "\n" + countTasks();
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }
}
