package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.util.UiUtil;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String WEBSITE_MESSAGE = "Visit our website for more information of HelloFile";
    public static final String WEBSITE_URL = "https://ay2021s1-cs2103t-f12-1.github.io/tp/";
    public static final String USERGUIDE_MESSAGE = "Refer to the User Guide";
    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f12-1.github.io/tp/UserGuide.html#quick-start";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label message01;
    @FXML
    private Label message02;
    @FXML
    private Label url01;
    @FXML
    private Label url02;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        message01.setText(WEBSITE_MESSAGE);
        message02.setText(USERGUIDE_MESSAGE);
        url01.setText(WEBSITE_URL);
        url02.setText(USERGUIDE_URL);
        root.setTitle("Help");
        UiUtil.setTheme(root, MainWindow.getInstance().getCurrentTheme());
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
     * Copies the URL to the product website to the clipboard.
     */
    @FXML
    private void copyWebsiteUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(WEBSITE_URL);
        clipboard.setContent(url);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUserGuideUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
