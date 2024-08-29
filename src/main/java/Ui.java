import Tasks.TaskList;

import java.util.Scanner;

public class Ui {

    private TaskList taskList;
    private Storage storage;

    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        botSay("Hello I'm Peridot!!");
        botSay("What's up?");
        String userResponse = scanner.nextLine();
        while (!userResponse.equals("bye")) {
            try {
                Parser.answer(userResponse, taskList);
                this.storage.write(taskList.getTaskList());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                userResponse = scanner.nextLine();
            }
        }
        botSay("Bye!");
    }

    public static void botSay(String string) {
        System.out.println("Peridot: " + string);
    }
}
