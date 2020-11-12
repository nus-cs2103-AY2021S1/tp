package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

public class TimetablePanel extends UiPart<Region> {
    private static final String FXML = "TimetablePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private SlotListPanel mondayListPanel;
    private SlotListPanel tuesdayListPanel;
    private SlotListPanel wednesdayListPanel;
    private SlotListPanel thursdayListPanel;
    private SlotListPanel fridayListPanel;

    @FXML
    private StackPane mondayListPanelPlaceholder;

    @FXML
    private StackPane tuesdayListPanelPlaceholder;

    @FXML
    private StackPane wednesdayListPanelPlaceholder;

    @FXML
    private StackPane thursdayListPanelPlaceholder;

    @FXML
    private StackPane fridayListPanelPlaceholder;


    /** Constructs TimetablePanel
     *
     * @param logic
     */
    public TimetablePanel(Logic logic) {
        super(FXML);

        this.logic = logic;

        mondayListPanel = new SlotListPanel(logic.getFilteredSlotList("Monday"));
        mondayListPanelPlaceholder.getChildren().add(mondayListPanel.getRoot());

        tuesdayListPanel = new SlotListPanel(logic.getFilteredSlotList("Tuesday"));
        tuesdayListPanelPlaceholder.getChildren().add(tuesdayListPanel.getRoot());

        wednesdayListPanel = new SlotListPanel(logic.getFilteredSlotList("Wednesday"));
        wednesdayListPanelPlaceholder.getChildren().add(wednesdayListPanel.getRoot());

        thursdayListPanel = new SlotListPanel(logic.getFilteredSlotList("Thursday"));
        thursdayListPanelPlaceholder.getChildren().add(thursdayListPanel.getRoot());

        fridayListPanel = new SlotListPanel(logic.getFilteredSlotList("Friday"));
        fridayListPanelPlaceholder.getChildren().add(fridayListPanel.getRoot());
    }
}
