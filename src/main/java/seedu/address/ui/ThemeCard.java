package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.theme.Theme;

/**
 * An UI component that displays information of a {@code Theme}.
 */
public class ThemeCard extends UiPart<Region> {

    private static final String FXML = "ThemeCard.fxml";

    private ThemeWindow themeWindow;
    private Theme theme;

    @FXML
    private Label themeName;

    /**
     * Constructs a ThemeCard.
     *
     * @param theme the theme
     * @param themeWindow the theme choosing window
     */
    public ThemeCard(Theme theme, ThemeWindow themeWindow) {
        super(FXML);
        this.theme = theme;
        this.themeWindow = themeWindow;
        themeName.setText(theme.getThemeName());
    }

    public void showThemePreview() {
        themeWindow.showThemePreview(theme);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ThemeCard)) {
            return false;
        }

        // state check
        ThemeCard card = (ThemeCard) other;
        return themeName.getText().equals(card.themeName.getText());
    }
}
