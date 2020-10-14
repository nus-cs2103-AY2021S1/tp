package seedu.address.ui.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.ui.Theme;

public class UiUtil {

    /**
     * Gets the image with the given imagePath.
     *
     * @param imagePath the image path
     * @return the image
     */
    public static Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    public static void setTheme(Stage root, Theme theme) {
        root.getScene().getStylesheets().clear();
        root.getScene().getStylesheets().add(theme.getStyleSheetPath());
    }
}
