package seedu.address.ui.theme;

/**
 * ThemeSet stores Themes.
 */
public class ThemeSet {

    private static final String DEFAULT_THEME_NAME = "Default Theme";
    private static final String DARK_THEME_NAME = "Dark Theme";

    public static final Theme DEFAULT_THEME =
            new Theme(DEFAULT_THEME_NAME, "view/DefaultTheme.css", "/images/default_theme.png");

    public static final Theme DARK_THEME =
            new Theme(DARK_THEME_NAME, "view/HelloFileDarkTheme.css", "/images/dark_theme.png");

    public static final Theme getTheme(String themeName) {
        switch (themeName) {
        case DARK_THEME_NAME:
            return DARK_THEME;
        default:
            return DEFAULT_THEME;
        }
    }
}
