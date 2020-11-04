package seedu.address.ui.theme;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ThemeSetTest {

    public static final String LIGHT_THEME_NAME = "Light Theme";
    public static final String DARK_THEME_NAME = "Dark Theme";
    public static final String GALAXY_THEME_NAME = "Galaxy Theme";
    public static final String SKY_THEME_NAME = "Sky Theme";
    public static final String INVALID_THEME_NAME = "hahaha";

    @Test
    public void getTheme_getLightTheme_success() {
        Theme themeGet = ThemeSet.getTheme(LIGHT_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.LIGHT_THEME));
    }

    @Test
    public void getTheme_getDarkTheme_success() {
        Theme themeGet = ThemeSet.getTheme(DARK_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.DARK_THEME));
    }

    @Test
    public void getTheme_getGalaxyTheme_success() {
        Theme themeGet = ThemeSet.getTheme(GALAXY_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.GALAXY_THEME));
    }

    @Test
    public void getTheme_getSkyTheme_success() {
        Theme themeGet = ThemeSet.getTheme(SKY_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.SKY_THEME));
    }

    @Test
    public void getTheme_invalidThemeName_returnLightTheme() {
        Theme themeGet = ThemeSet.getTheme(INVALID_THEME_NAME);
        assertTrue(themeGet.equals(ThemeSet.LIGHT_THEME));
    }
}
