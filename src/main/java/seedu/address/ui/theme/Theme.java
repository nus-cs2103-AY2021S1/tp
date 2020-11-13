package seedu.address.ui.theme;

import static java.util.Objects.requireNonNull;

/**
 * Theme stores the style sheet path and theme preview image path information.
 */
public class Theme {

    private String themeName;
    private String styleSheetPath;
    private String themePreviewPath;

    /**
     * Constructs a theme with the given style sheet path and theme preview image path.
     *
     * @param themeName the name of the theme
     * @param styleSheetPath the path of the style sheet
     * @param themePreviewPath the path of the preview image
     */
    public Theme(String themeName, String styleSheetPath, String themePreviewPath) {
        requireNonNull(themeName);
        requireNonNull(styleSheetPath);
        requireNonNull(themePreviewPath);
        this.themeName = themeName;
        this.styleSheetPath = styleSheetPath;
        this.themePreviewPath = themePreviewPath;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getStyleSheetPath() {
        return styleSheetPath;
    }

    public String getThemePreviewPath() {
        return themePreviewPath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Theme)) { //this handles null as well.
            return false;
        }

        Theme o = (Theme) other;

        return themeName.equals(o.themeName)
                && styleSheetPath.equals(o.styleSheetPath)
                && themePreviewPath.equals(o.themePreviewPath);
    }

    @Override
    public String toString() {
        return themeName;
    }
}
