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

    public static final Border UNARCHIVED_LOW_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.TURQUOISE_BORDER,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background UNARCHIVED_LOW_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.TURQUOISE,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border ARCHIVED_LOW_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.TURQUOISE_BORDER_DARK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background ARCHIVED_LOW_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.TURQUOISE_DARK,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border UNARCHIVED_MEDIUM_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.ORANGE_BORDER,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background UNARCHIVED_MEDIUM_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.ORANGE,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border ARCHIVED_MEDIUM_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.ORANGE_BORDER_DARK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background ARCHIVED_MEDIUM_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.ORANGE_DARK,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border UNARCHIVED_HIGH_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.RED_BORDER,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background UNARCHIVED_HIGH_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.RED,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border ARCHIVED_HIGH_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.RED_BORDER_DARK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background ARCHIVED_HIGH_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.RED_DARK,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border UNARCHIVED_UNDEFINED_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.WHITE_BORDER,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background UNARCHIVED_UNDEFINED_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.WHITE,
            CornerRadii.EMPTY,
            Insets.EMPTY));

    public static final Border ARCHIVED_UNDEFINED_PRIORITY_BORDER = new Border(new BorderStroke(
            ColorPicker.WHITE_BORDER_DARK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(BORDER_SIZE)));

    public static final Background ARCHIVED_UNDEFINED_PRIORITY_BACKGROUND = new Background(new BackgroundFill(
            ColorPicker.WHITE_DARK,
            CornerRadii.EMPTY,
            Insets.EMPTY));

}
