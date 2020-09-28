package tp.cap5buddy.ui;

import java.util.Scanner;

import tp.cap5buddy.parser.ParserManager;



/**
 * Represents the UI object that the user interacts with.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);
    private static ParserManager pm = new ParserManager();
    private boolean isExit;
    private String currentInput;

    /**
     * Represents that constructor that creates the Ui object.
     */
    public Ui() {
        this.isExit = false;
        this.currentInput = null;
    }

    /**
     * Sets the user input into the Ui object.
     * @param input user input
     */
    private void setInput(String input) {
        this.currentInput = input;
    }

    /**
     * Sends the user input to the parser to be executed.
     * @return String the result message of the user command.
     */
    private String sendToParser() {
        return pm.parse(this.currentInput);
    }

    /**
     * Prints the result message.
     * @param result
     */
    private void printResult(String result) {
        System.out.println(result);
    }

    /**
     * Starts the scanning of user input.
     */
    public void startScanner() {
        System.out.println("Starting scanner");
        String current = sc.nextLine();
        setInput(current);
        String result = sendToParser();
        printResult(result);
        System.out.println("Entering while loop");
        while (sc.hasNextLine()) {
            current = sc.nextLine();
            setInput(current);
            result = sendToParser();
            printResult(result);
        }
        sc.close();
    }
}
