package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String COMMAND_SUMMARY = "                                          COMMAND SUMMARY"
            + "                                         \n\n"
            + "Action │ Format, Examples \n"
            + "───────┼────────────────"
            + "────────────────────────"
            + "────────────────────────"
            + "────────────────────────\n"
            + "Add    │ add n/NAME ic/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ \n"
            + "       | e.g., add n/James Ho ic/S1234567A p/22224444 e/jamesho@example.com a/123, Clementi Rd, \n"
            + "       | 1234665 t/friend t/colleague \n"
            + "Clear  │ clear \n"
            + "Delete │ delete INDEX \n"
            + "       | e.g., delete 3 \n"
            + "       | delete NRIC \n"
            + "       | e.g., delete S1234567A \n"
            + "Edit   │ edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ic/NRIC] [t/TAG]…​ \n"
            + "       | e.g.,edit 2 n/James Lee e/jameslee@example.com ic/S1234567A \n"
            + "Find   │ find KEYWORD [MORE_KEYWORDS] [NRIC] [MORE_NRICs] \n"
            + "       | e.g., find James Jake \n"
            + "       | e.g., find Curry Davis Heskey S1234567A \n"
            + "List   │ list \n"
            + "Help   │ help \n"
            + "Count  │ count \n\n";

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-w15-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = COMMAND_SUMMARY
            + "For more information, please refer to the user guide: \n"
            + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
