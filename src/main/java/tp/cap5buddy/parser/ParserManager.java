package tp.cap5buddy.parser;

import tp.cap5buddy.commands.ResultCommand;

import java.util.Scanner;

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
     * @param input user input.
     * @return String result message.
     */
    public String parse(String input) {
        this.currentInput = input;
        getCommand();
        getNonCommand();
        Tokenizer token = new Tokenizer(this.nonCommand);
        String[] words = token.getWords();
        ResultCommand result;

        switch (this.command) {
        case "addmodule":
            AddModuleParser parser = new AddModuleParser();
            parser.parse(words);
            result = parser.execute();
            break;
        default:
            result = new ResultCommand("Nothing happens");
        }
        return result.getMessage();
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
