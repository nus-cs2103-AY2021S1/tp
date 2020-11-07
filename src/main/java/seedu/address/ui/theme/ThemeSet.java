package seedu.address.ui.theme;

/**
 * ThemeSet stores Themes.
 */
public class ThemeSet {

    private static final String LIGHT_THEME_NAME = "Light Theme";
    private static final String DARK_THEME_NAME = "Dark Theme";
    private static final String GALAXY_THEME_NAME = "Galaxy Theme";
    private static final String SKY_THEME_NAME = "Sky Theme";

    public static final Theme LIGHT_THEME =
            new Theme(LIGHT_THEME_NAME, "view/LightTheme.css", "/images/light_theme.png");

    public static final Theme DARK_THEME =
            new Theme(DARK_THEME_NAME, "view/HelloFileDarkTheme.css", "/images/dark_theme.png");

    public static final Theme GALAXY_THEME =
            new Theme(GALAXY_THEME_NAME, "view/GalaxyTheme.css", "/images/galaxy_theme.png");

    public static final Theme SKY_THEME =
            new Theme(SKY_THEME_NAME, "view/SkyTheme.css", "/images/sky_theme.png");

    public static final Theme getTheme(String themeName) {
        assert themeName != null;

        switch (themeName) {
        case DARK_THEME_NAME:
            return DARK_THEME;
        case GALAXY_THEME_NAME:
            return GALAXY_THEME;
        case SKY_THEME_NAME:
            return SKY_THEME;
        default:
            return LIGHT_THEME;
        }
    }
}
