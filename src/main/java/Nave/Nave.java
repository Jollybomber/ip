package Nave;

import java.util.Scanner;

public class Nave {
    private final TaskList tasks;
    private final TaskStorage storage;
    private final Ui ui;
    private final Parser parser;

    public Nave(String filePathString) {
        this.tasks = new TaskList();
        this.storage = new TaskStorage(filePathString);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void run() {
        //Greet User
        ui.greet();

        storage.onStart(tasks);

        Scanner inputReader = new Scanner(System.in);

        //Get user's input
        String userInput = inputReader.nextLine();
        int place;
        while (!userInput.equals("bye")) {
            switch(parser.handleInput(userInput)) {
                case LIST:
                    ui.showResponse(tasks.listItems());
                    break;
                case HELP:
                    ui.helpMessage();
                    break;
                case MARK:
                    place = parser.parseMark(userInput);
                    ui.showResponse(tasks.markItem(place));
                    break;
                case UNMARK:
                    place = parser.parseUnmark(userInput);
                    ui.showResponse(tasks.unmarkItem(place));
                    break;
                case TASK:
                    try {
                        Task curr = parser.parseTask(userInput);
                        tasks.addTask(curr);
                        storage.saveToFile(curr.toFileFormat());
                        ui.showResponse(curr.creationResponse() + tasks.countTasks());
                    } catch (WrongInputException e) {
                        ui.showResponse(e.getMessage());
                    }
                    break;
                case DELETE:
                    place = parser.parseDelete(userInput);
                    storage.deleteFromFile(place);
                    ui.showResponse(tasks.deleteItem(place));
                    break;
                case UNSURE:
                    ui.unsureMessage();
                    break;
                case FIND:
                    String keyword = parser.parseFind(userInput);
                    ui.showResponse(tasks.findTasks(keyword));
                    break;
            }
            userInput = inputReader.nextLine();
        }

        //Say goodbye
        ui.sayFarewell();
    }

    public static void main(String[] args) {
        new Nave("./data/tasks.txt").run();
    }
}
