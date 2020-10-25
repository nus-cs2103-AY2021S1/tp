package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.icalendarfx.components.VEvent;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import seedu.address.commons.core.LogsCenter;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Locale;
import java.util.logging.Logger;

public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";
    private static final Locale UK = Locale.UK;
    private VCalendar vCalendar;
    private ICalendarAgenda agenda;
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private BorderPane borderPane;

    public SchedulePanel(ObservableList<VEvent> list) {
        super(FXML);
        this.vCalendar = new VCalendar();
        VEvent vEvent = new VEvent().withSummary("HAHAHHA").withDateTimeStart(LocalDateTime.of(2020, Month.OCTOBER,22
                ,20,0))
                .withDateTimeEnd(LocalDateTime.of(2020, Month.OCTOBER,22
                        ,21,23));
        list.add(vEvent);
        vCalendar.setVEvents(list);
        this.agenda = new ICalendarAgenda(this.vCalendar);
        init(this.agenda);
        borderPane.setStyle("-fx-background-color: #FFE8D7;");
        borderPane.setCenter(agenda);
        borderPane.setCenter(agenda);
        borderPane.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Initialises the scheduler panel
     */
    public void init(ICalendarAgenda agenda) {
        agenda.setLocale(UK);
        AgendaWeekSkin weekSkin = new AgendaWeekSkin(this.agenda);
        agenda.setSkin(weekSkin);
        setSettings(this.agenda);
    }


    /**
     * Configures settings for icalendar
     */
    public void setSettings(ICalendarAgenda agenda) {
        agenda.setAllowResize(false);
        agenda.setAllowDragging(false);
        agenda.setOnMousePressed(null);
        agenda.setOnTouchPressed(null);
        agenda.setEditAppointmentCallback(null);
        agenda.setAppointmentChangedCallback(null);
        agenda.setSelectedOneAppointmentCallback(null);
        agenda.setNewAppointmentCallback(null);
        agenda.setNewAppointmentDrawnCallback(null);
    }

    /**
     * Updates scheduler
     */
    public void update() {
        this.agenda.updateAppointments();
    }

    /**
     * Sets the calendar to week view format.
     */
    public void setWeekly() {
        AgendaWeekSkin weekSkin = new AgendaWeekSkin(this.agenda);
        agenda.setSkin(weekSkin);
    }

    /**
     * Sets the calendar to day view format.
     */
    public void setDaily() {
        AgendaDaySkin dailySkin = new AgendaDaySkin(this.agenda);
        agenda.setSkin(dailySkin);
    }


    /**
     * Changes panel to show time interval
     *
     * @param localDateTime local date and time.
     */
    public void setDisplayedDateTime(LocalDateTime localDateTime) {
        this.agenda.setDisplayedLocalDateTime(localDateTime);
    }

}
