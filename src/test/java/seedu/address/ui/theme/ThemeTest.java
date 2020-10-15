package seedu.address.ui.theme;

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
}
