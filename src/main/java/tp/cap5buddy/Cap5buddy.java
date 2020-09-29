package tp.cap5buddy;

import tp.cap5buddy.logic.LogicManager;
import tp.cap5buddy.logic.commands.ResultCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.ui.Ui;

/**
 * Represents the cap5buddy program.
 */
public class Cap5buddy {

    /**
     * Represents the main body of the program.
     */
    public static void main(String[] args) throws ParseException {
        // Start up, create the UI object
        Ui userInterface = new Ui();
        userInterface.startScanner(); // creates the scanner
        run(userInterface);
    }

    private static void run(Ui ui) throws ParseException {
        boolean isExit = false;
        LogicManager lm = new LogicManager();
        while (!isExit) {
            String current = ui.getInput();
            ResultCommand res = lm.execute(current);
            isExit = res.getExit();
            ui.printResult(res.getMessage());
        }
        ui.closeScanner();
    }
}
