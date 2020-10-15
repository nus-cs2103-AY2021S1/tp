package seedu.address.ui.theme;

/**
 * ThemeSet stores Themes.
 */
public class ThemeSet {

    private static final String DEFAULT_THEME_NAME = "Default Theme";
    private static final String DARK_THEME_NAME = "Dark Theme";
    private static final String GALAXY_THEME_NAME = "Galaxy Theme";

    public static final Theme DEFAULT_THEME =
            new Theme(DEFAULT_THEME_NAME, "view/DefaultTheme.css", "/images/default_theme.png");

    public static final Theme DARK_THEME =
            new Theme(DARK_THEME_NAME, "view/HelloFileDarkTheme.css", "/images/dark_theme.png");

    public static final Theme GALAXY_THEME =
            new Theme(GALAXY_THEME_NAME, "view/GalaxyTheme.css", "/images/galaxy_theme.png");

    public static final Theme getTheme(String themeName) {
        switch (themeName) {
        case DARK_THEME_NAME:
            return DARK_THEME;
        case GALAXY_THEME_NAME:
            return GALAXY_THEME;
        default:
            return DEFAULT_THEME;
        }
    }
}
