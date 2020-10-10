package tp.cap5buddy.ui.gui;

import java.util.Scanner;
import java.util.logging.Logger;

import javafx.stage.Stage;
import tp.cap5buddy.commons.LogsCenter;
import tp.cap5buddy.ui.Messages;


public class UiManager implements Ui {
    private static final Scanner SC = new Scanner(System.in);
    private static final Logger logger = LogsCenter.getLogger(tp.cap5buddy.ui.Ui.class);

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

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");
        MainWindowController main = new MainWindowController(primaryStage);
    }
}
