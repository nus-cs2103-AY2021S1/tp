package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import jfxtras.scene.control.agenda.Agenda;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private Agenda calendar;

    @FXML
    private StackPane calendarPlaceholder;

    // TODO: change constructor to enable linking to main logic
    public CalendarDisplay() {
        super(FXML);
        calendar = new Agenda();
        calendarPlaceholder.getChildren().add(calendar);
    }

}
