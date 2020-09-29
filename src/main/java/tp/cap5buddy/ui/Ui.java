package tp.cap5buddy.ui;

import java.util.Scanner;


/**
 * Represents the UI object that the user interacts with.
 */
public class Ui {
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Prints the result message.
     * @param result
     */
    public void printResult(String result) {
        System.out.println(result);
    }

    /**
     * Starts the scanning of user input.
     */
    public void startScanner() {
        printResult(Messages.getStart());
    }

    public void closeScanner() {
        printResult(Messages.getClose());
    }

    public String getInput() {
        return SC.nextLine();
    }
}
