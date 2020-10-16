package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import jfxtras.scene.control.agenda.Agenda;

/**
 * A ui for the calendar displayed in one of the tabs of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private Agenda calendar;

    @FXML
    private StackPane calendarPlaceholder;

    /**
     * Creates a {@code Calendar} with a blank {@code Agenda}.
     */
    public CalendarDisplay() { // TODO: change constructor to enable linking to main logic
        super(FXML);
        calendar = new Agenda();
        calendarPlaceholder.getChildren().add(calendar);
    }

}
