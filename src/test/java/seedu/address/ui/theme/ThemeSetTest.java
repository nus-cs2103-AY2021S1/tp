package seedu.address.ui.theme;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ThemeSetTest {

    public static final String DEFAULT_THEME_NAME = "Default Theme";
    public static final String DARK_THEME_NAME = "Dark Theme";
    public static final String INVALID_THEME_NAME = "hahaha";

    @Test
    public void getTheme_getDefaultTheme_success() {
        Theme themeGet = ThemeSet.getTheme(DEFAULT_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.DEFAULT_THEME));
    }

    @Test
    public void getTheme_getDarkTheme_success() {
        Theme themeGet = ThemeSet.getTheme(DARK_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.DARK_THEME));
    }

    @Test
    public void getTheme_invalidThemeName_returnDefaultTheme() {
        Theme themeGet = ThemeSet.getTheme(INVALID_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.DEFAULT_THEME));
    }
}
