package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;

/**
 * A ui for the calendar displayed in one of the tabs of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";

    private ICalendarAgenda calendar;

    @FXML
    private StackPane calendarPlaceholder;

    /**
     * Creates a {@code Calendar} with a blank {@code Agenda}.
     */
    public CalendarDisplay() { // TODO: change constructor to enable linking to main logic
        super(FXML);
        calendar = new ICalendarAgenda();
        disableMouseInteraction(calendar);
        calendarPlaceholder.getChildren().add(calendar);
    }

    private static void disableMouseInteraction(ICalendarAgenda agenda) {
        agenda.setAllowDragging(false);
        agenda.setAllowResize(false);
        agenda.setActionCallback(null);
        agenda.setNewAppointmentCallback(null);
        agenda.setSelectedOneAppointmentCallback(null);
        agenda.setNewAppointmentDrawnCallback(null);
        agenda.setAppointmentChangedCallback(null);
        agenda.setOnMouseClicked(null);
        agenda.setOnMousePressed(null);
        agenda.setAllowDragging(false);
        agenda.setOnTouchPressed(null);
        agenda.setOnMouseEntered(null);
        agenda.setOnMouseExited(null);
    }

}
