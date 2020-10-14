package seedu.address.ui;

/**
 * Theme stores the style sheet path and theme preview image path information.
 */
public class Theme {

    private String styleSheetPath;
    private String themePreviewPath;

    /**
     * Constructs a theme with the given style sheet path and theme preview image path.
     *
     * @param styleSheetPath the path of the style sheet
     * @param themePreviewPath the path of the preview image
     */
    public Theme(String styleSheetPath, String themePreviewPath) {
        this.styleSheetPath = styleSheetPath;
        this.themePreviewPath = themePreviewPath;
    }

    public String getStyleSheetPath() {
        return styleSheetPath;
    }

    public String getThemePreviewPath() {
        return themePreviewPath;
    }
}
