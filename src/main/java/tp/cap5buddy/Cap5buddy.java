package tp.cap5buddy;

import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.parser.exception.ParseException;
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
        userInterface.startScanner();
        // Find the save file and load it
    }
}
