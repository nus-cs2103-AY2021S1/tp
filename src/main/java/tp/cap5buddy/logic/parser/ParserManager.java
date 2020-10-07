package tp.cap5buddy.logic.parser;

import java.util.Scanner;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;



/**
 * Represents the manager that handles all parser related actions and requests.
 */
public class ParserManager {
    private String currentInput;
    private String command;
    private String nonCommand;
    private int count;

    /**
     * Represents the constructor that creates the manager object.
     */
    public ParserManager() {
        this.currentInput = null;
        this.command = null;
        this.nonCommand = null;
    }

    /**
     * Returns the String of the result after parsing the input.
     *
     * @param input user input.
     * @return String result message.
     */
    public Command parse(String input) throws ParseException {
        this.currentInput = input;
        getCommand();
        getNonCommand();
        Parser parser;
        Command command;
        switch (this.command) {
        case "addmodule":
            parser = new AddModuleParser();
            command = parser.parse(input);
            return command;
        case "addzoom":
            parser = new AddZoomLinkParser();
            command = parser.parse(input);
            return command;
        case "editmodule":
            parser = new EditModuleParser();
            command = parser.parse(this.nonCommand);
            return command;
        case "viewmodule":
            parser = new ViewModuleParser();
            command = parser.parse(this.nonCommand);
            return command;
        case "deletemodule":
            parser = new DeleteModuleParser();
            command = parser.parse(this.nonCommand);
            return command;
        case "addcontact":
            parser = new AddContactParser();
            command = parser.parse(input);
            return command;
        case "edittask":
            parser = new EditTaskParser();
            command = parser.parse(input);
            return command;
        default:
            throw new ParseException("Invalid Command");
        }
    }

    private void getCommand() {
        String command = "";
        Scanner sc = new Scanner(this.currentInput);
        while (sc.hasNext()) {
            String now = sc.next();
            if (Prefix.isPrefix(now)) {
                break;
            } else {
                this.count++;
                command += now;
            }
        }
        this.command = command;
    }

    private void getNonCommand() {
        String nonCommand = "";
        String[] input = this.currentInput.split(" ");
        int limit = input.length;
        for (int i = this.count; i < limit; i++) {
            nonCommand += input[i] + " ";
        }
        this.nonCommand = nonCommand;
        this.count = 0; // to reset the counter
    }
}
