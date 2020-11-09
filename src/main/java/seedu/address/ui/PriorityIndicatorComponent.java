package seedu.address.ui;


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

/**
 * Contains the priority indicator components used to change the PriorityShape in PersonCard.
 */
public class PriorityIndicatorComponent {
    public static final int BORDER_SIZE = 5;

    public static Border getBorder(Boolean isArchive, String priority) {
        if (isArchive) {
            switch (priority) {
            case "undefined":
                return createBorder(ColorPicker.WHITE_BORDER_DARK);
            case "low":
                return createBorder(ColorPicker.TURQUOISE_BORDER_DARK);
            case "medium":
                return createBorder(ColorPicker.ORANGE_BORDER_DARK);
            case "high":
                return createBorder(ColorPicker.RED_BORDER_DARK);
            default:
                assert false : "Border priority not found";
                return null;
            }
        } else {
            switch (priority) {
            case "undefined":
                return createBorder(ColorPicker.WHITE_BORDER);
            case "low":
                return createBorder(ColorPicker.TURQUOISE_BORDER);
            case "medium":
                return createBorder(ColorPicker.ORANGE_BORDER);
            case "high":
                return createBorder(ColorPicker.RED_BORDER);
            default:
                assert false : "Border priority not found";
                return null;
            }
        }
    }

    private static Border createBorder(javafx.scene.paint.Paint color) {
        return new Border(new BorderStroke(
                color,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(BORDER_SIZE)));
    }

    public static Background getBackground(Boolean isArchive, String priority) {
        if (isArchive) {
            switch (priority) {
            case "undefined":
                return createBackground(ColorPicker.WHITE_DARK);
            case "low":
                return createBackground(ColorPicker.TURQUOISE_DARK);
            case "medium":
                return createBackground(ColorPicker.ORANGE_DARK);
            case "high":
                return createBackground(ColorPicker.RED_DARK);
            default:
                assert false : "Background priority not found";
                return null;
            }
        } else {
            switch (priority) {
            case "undefined":
                return createBackground(ColorPicker.WHITE);
            case "low":
                return createBackground(ColorPicker.TURQUOISE);
            case "medium":
                return createBackground(ColorPicker.ORANGE);
            case "high":
                return createBackground(ColorPicker.RED);
            default:
                assert false : "Background priority not found";
                return null;
            }
        }
    }

    private static Background createBackground(javafx.scene.paint.Paint color) {
        return new Background(new BackgroundFill(
                color,
                CornerRadii.EMPTY,
                Insets.EMPTY));
    }

}
