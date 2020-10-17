package seedu.address.ui.theme;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ThemeTest {

    public static final String VALID_THEME_NAME = "Dark Theme";
    public static final String VALID_STYLE_SHEET_PATH = "view/HelloFileDarkTheme.css";
    public static final String VALID_THEME_PREVIEW_PATH = "/images/dark_theme.png";

    @Test
    public void constructor_nullThemeName_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new Theme(null, VALID_STYLE_SHEET_PATH, VALID_THEME_PREVIEW_PATH));
    }

    @Test
    public void constructor_nullStyleSheetPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Theme(VALID_THEME_NAME, null, VALID_THEME_PREVIEW_PATH));
    }

    @Test
    public void constructor_nullThemePreviewPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Theme(VALID_THEME_NAME, VALID_STYLE_SHEET_PATH, null));
    }

    @Test
    public void getThemeName() {
        String themeName = "name";
        Theme theme = new Theme(themeName, "style sheet", "preview");
        assertTrue(themeName.equals(theme.getThemeName()));
    }

    @Test
    public void getStyleSheetPath() {
        String styleSheetPath = "style sheet";
        Theme theme = new Theme("name", styleSheetPath, "preview");
        assertTrue(styleSheetPath.equals(theme.getStyleSheetPath()));
    }

    @Test
    public void getThemePreviewPath() {
        String previewPath = "preview";
        Theme theme = new Theme("name", "style sheet", previewPath);
        assertTrue(previewPath.equals(theme.getThemePreviewPath()));
    }

    @Test
    public void equals() {
        Theme theme = new Theme("name", "style sheet", "preview");

        // same values -> returns true
        assertTrue(theme.equals(new Theme("name", "style sheet", "preview")));

        // same object -> returns true
        assertTrue(theme.equals(theme));

        // null -> returns false
        assertFalse(theme.equals(null));

        // different types -> returns false
        assertFalse(theme.equals("lalala"));

        // different theme name -> returns false
        assertFalse(theme.equals(new Theme("different", "style sheet", "preview")));

        // different style sheet path -> returns false
        assertFalse(theme.equals(new Theme("name", "different", "preview")));

        // different preview image path
        assertFalse(theme.equals(new Theme("name", "style sheet", "different")));
    }

    @Test
    public void themeToString() {
        String themeName = "a theme";
        Theme theme = new Theme(themeName, "style sheet", "preview");
        assertTrue(theme.toString().equals(themeName));
    }
}
