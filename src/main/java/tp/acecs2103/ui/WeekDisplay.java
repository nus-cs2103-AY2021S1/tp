package tp.acecs2103.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the week display that is displayed at the top of the application.
 */
public class WeekDisplay extends UiPart<Region> {
    private static final String FXML = "WeekDisplay.fxml";

    @FXML
    private Label projectName;

    @FXML
    private Label weekNumber;

    /**
     * Creates a {@code WeekDisplay} with the given {@code Path}.
     */
    // TODO: Connect method to weekToDisplay
    public WeekDisplay(int weekToDisplay) {
        super(FXML);

        projectName.setText("Welcome to Ace CS2103/T!");
        int remainWeek = 13 - weekToDisplay;
        if (remainWeek < 0) {
            weekNumber.setText("The end is near!");
        } else if (remainWeek == 0) {
            weekNumber.setText("The end is near!");
        } else if (remainWeek == 1) {
            weekNumber.setText("Now is Week " + weekToDisplay + ". There is " + remainWeek + " week left.");
        } else {
            weekNumber.setText("Now is Week " + weekToDisplay + ". There are " + remainWeek + " weeks left.");
        }

    }
}
