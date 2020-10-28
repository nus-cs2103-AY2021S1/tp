package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.AppUtil;
import seedu.address.ui.theme.Theme;
import seedu.address.ui.theme.ThemeSet;
import seedu.address.ui.util.UiUtil;

/**
 * Controller for a theme selection page.
 */
public class ThemeWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ThemeWindow.class);
    private static final String FXML = "ThemeWindow.fxml";

    private Theme currentTheme = MainWindow.getInstance().getCurrentTheme();
    private Theme selectedTheme = currentTheme;

    @FXML
    private ListView<Region> themeList;
    @FXML
    private ImageView themeImage;

    /**
     * Creates a new ThemeWindow.
     *
     * @param root Stage to use as the root of the ThemeWindow.
     */
    public ThemeWindow(Stage root) {
        super(FXML, root);
        UiUtil.setTheme(root, currentTheme);
        root.setTitle("Select a Theme");
    }

    /**
     * Creates a new ThemeWindow.
     */
    public ThemeWindow() {
        this(new Stage());
    }

    private void fillThemeCards() {
        ThemeCard defaultThemeCard = new ThemeCard(ThemeSet.DEFAULT_THEME, this);
        ThemeCard darkThemeCard = new ThemeCard(ThemeSet.DARK_THEME, this);

        themeList.getItems().setAll(
                defaultThemeCard.getRoot(),
                darkThemeCard.getRoot()
        );
    }

    /**
     * Displays the preview image of the given theme.
     * @param theme the theme to display
     */
    public void showThemePreview(Theme theme) {
        themeImage.setImage(AppUtil.getImage(theme.getThemePreviewPath()));
        selectedTheme = theme;
    }

    /**
     * Applies the selected theme to the application.
     */
    public void applySelectedTheme() {
        MainWindow.getInstance().setTheme(selectedTheme);
        hide();
    }

    /**
     * Shows the theme selection window.
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
        logger.fine("Showing theme page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
        fillThemeCards();
        showThemePreview(selectedTheme);
    }

    /**
     * Returns true if the theme window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the theme window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the theme window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
