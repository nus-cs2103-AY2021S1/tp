package tp.cap5buddy.parser;

import java.util.Scanner;

public class Parser {
    private String userInput;
    private String reply;
    private String command;
    public Parser() {
        this.userInput = null;
        this.reply = null;
        this.command = null;
    }

    public void setUserInput(String input) {
        this.userInput = input;
        parse();
    }

    public String getReply() {
        return this.reply;
    }

    private void parse() { // this is the breaking down part, current set to echo user input.
        this.reply = this.userInput;
    }

    private void setCommand() {
        Scanner sc = new Scanner(this.userInput);
        this.command = sc.next();
    }
}
