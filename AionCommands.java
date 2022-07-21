package lusaris;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the commands given to the Aion program.
 */
public class AionCommands {

    /**
     * This function takes a new task we want to add and adds it to our
     * activeTasks HashMap and also writes it to our activeTasksFile file.
     * @param taskName The name of the task we want to create.
     */
    public static void create(String taskName) {

        Task newTask = new Task(taskName);
        int newTaskHash = newTask.hashCode();
        AionUtils.serializeObject(Aion.activeFolder.getPath() + "/"
                + newTaskHash, newTask);
        if (Aion.activeTasks.containsKey(taskName)) {
            ArrayList<String> task = Aion.activeTasks.get(taskName);
            task.add(String.valueOf(newTaskHash));
        } else {
            ArrayList<String> newHashList = new ArrayList<>();
            newHashList.add(String.valueOf(newTaskHash));
            Aion.activeTasks.put(taskName, newHashList);
        }

        AionUtils.writeHashMaptoJSON(Aion.activeTasksFile, Aion.activeTasks);
    }

    public static void take(String taskName) {

    }

    public static void drop(String taskName) {

    }

    public static void archive(String taskName) {
        if (!Aion.activeTasks.containsKey(taskName)) {
            System.out.println("The task you entered does not exist.");
        } else {
            ArrayList<String> taskHashes = Aion.activeTasks.get(taskName);
            String selectedTaskToRemove = null;
            if (taskHashes.size() > 1) {
                boolean correctIn = false;
                while (!correctIn) {
                    Scanner userInScanner = new Scanner(System.in);
                    System.out.println("Options: ");
                    for (int i = 0; i < taskHashes.size(); i++) {
                        System.out.printf("%d ..... ", i + 1);
                        Task taskObj =
                                AionUtils.deserializeObject(
                                        Aion.activeFolder.getPath()
                                                + "/" + taskHashes.get(i),
                                        Task.class);
                        System.out.println(taskObj);
                    }
                    String userInput = userInScanner.nextLine();
                    if (userInput.matches("^[1-9][0-9]*")
                            && Integer.parseInt(userInput)
                            <= taskHashes.size()) {
                        correctIn = true;
                        selectedTaskToRemove =
                                taskHashes.get(Integer.parseInt(userInput) - 1);
                    } else {
                        System.out.println("Please enter one of the "
                                + "numbers above.");
                    }
                }
            } else {
                selectedTaskToRemove = taskHashes.get(0);
            }
            File fileToDelete = new File(Aion.activeFolder + "/"
                    + selectedTaskToRemove);
            if (fileToDelete.delete()) {
                System.out.printf("The task %s was successfully deleted.",
                        taskName);
                taskHashes.remove(selectedTaskToRemove);
            }
            if (taskHashes.size() == 0) {
                Aion.activeTasks.remove(taskName);
            }
            AionUtils.writeHashMaptoJSON(Aion.activeTasksFile,
                    Aion.activeTasks);
        }
    }

    public static void bank() {

    }
}
