package seedu.zookeep.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.zookeep.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2021s1-cs2103t-w15-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: ";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private TextFlow helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.getChildren().addAll(buildClickableText());
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
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Creates a TextFlow with a clickable URL leading to the User Guide.
     *
     * @return TextFlow containing help message and clickable User Guide URL.
     */
    private TextFlow buildClickableText() {
        Text text = new Text(HELP_MESSAGE);
        Hyperlink hyperlink = new Hyperlink(USER_GUIDE_URL);
        hyperlink.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL(USER_GUIDE_URL).toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        hyperlink.setBorder(Border.EMPTY);
        hyperlink.setPadding(new Insets(5, 0, 0, 0));
        hyperlink.setStyle("-fx-font-weight: bold;");

        return new TextFlow(text, hyperlink);
    }
}
