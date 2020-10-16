package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import jfxtras.scene.control.agenda.Agenda;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    @FXML
    private Agenda calendar;

    public CalendarDisplay() {
        super(FXML);
    }

}
