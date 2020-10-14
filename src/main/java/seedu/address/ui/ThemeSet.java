package seedu.address.ui;

/**
 * ThemeSet stores Themes.
 */
public class ThemeSet {

    public static final Theme DEFAULT_THEME =
            new Theme("Default Theme", "view/DefaultTheme.css", "/images/default_theme.png");

    public static final Theme DARK_THEME =
            new Theme("Dark Theme", "view/HelloFileDarkTheme.css", "/images/dark_theme.png");

    public static final Theme GALAXY_THEME =
            new Theme("Galaxy Theme", "view/GalaxyTheme.css", "/images/galaxy_theme.png");
}
