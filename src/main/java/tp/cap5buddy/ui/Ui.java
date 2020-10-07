package tp.cap5buddy.ui;

import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import tp.cap5buddy.ui.gui.MainWindow;

import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Represents the UI object that the user interacts with.
 */
public class Ui {
    private static final Scanner SC = new Scanner(System.in);
    private static final Logger logger = LogsCenter.getLogger(Ui.class);

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

    public void start(Stage primaryStage) {
//        logger.info("Starting UI...");
//        MainWindow main = new MainWindow(primaryStage);
//        main.initialize();
//        main.show();
    }
}
