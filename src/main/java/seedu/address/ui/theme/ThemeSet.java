package seedu.address.ui.theme;

/**
 * ThemeSet stores Themes.
 */
public class ThemeSet {

    private static final String LIGHT_THEME_NAME = "Light Theme";
    private static final String DARK_THEME_NAME = "Dark Theme";

    public static final Theme LIGHT_THEME =
            new Theme(LIGHT_THEME_NAME, "view/LightTheme.css", "/images/light_theme.png");

    public static final Theme DARK_THEME =
            new Theme(DARK_THEME_NAME, "view/HelloFileDarkTheme.css", "/images/dark_theme.png");

    public static final Theme getTheme(String themeName) {
        switch (themeName) {
        case DARK_THEME_NAME:
            return DARK_THEME;
        default:
            return LIGHT_THEME;
        }
    }
}
