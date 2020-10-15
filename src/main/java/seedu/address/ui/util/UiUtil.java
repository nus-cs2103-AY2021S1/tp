package seedu.address.ui.util;

import static java.util.Objects.requireNonNull;

import javafx.stage.Stage;
import seedu.address.ui.theme.Theme;

public class UiUtil {

    /**
     * Sets the theme of the given root to the give theme.
     *
     * @param root the Stage to set theme
     * @param theme the theme to be set
     */
    public static void setTheme(Stage root, Theme theme) {
        requireNonNull(root);
        requireNonNull(theme);
        root.getScene().getStylesheets().clear();
        root.getScene().getStylesheets().add(theme.getStyleSheetPath());
    }
}
