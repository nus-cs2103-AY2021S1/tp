package tp.cap5buddy.ui;

import tp.cap5buddy.parser.Parser;

public class Ui {
    private String userInput;
    private String reply;

    public Ui() {
        this.userInput = null;
        this.reply = null;
    }

    public void input(String input) {
        this.userInput = input;
        callParser(this.userInput);
    }

    public String reply() {
        return this.reply;
    }

    private void callParser(String userInput) {
        Parser parser = new Parser();
        parser.setUserInput(userInput);
        this.reply = parser.getReply();
    }
}
