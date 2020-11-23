package seedu.tr4cker.ui.daily;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.ui.UiPart;

public class DailyTabWindow extends UiPart<Region> {

    private static final String FXML = "DailyTabWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(DailyPanel.class);
    private final DailyPanel dailyPanel;

    @FXML
    private StackPane dailyPanelPlaceholder;

    /**
     * Constructor for DailyTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public DailyTabWindow(Logic logic) {
        super(FXML);
        logger.fine("Initialising Daily Tab Window...");
        this.dailyPanel = new DailyPanel(logic.getFilteredTodoList());
        dailyPanelPlaceholder.getChildren().add(dailyPanel.getRoot());
        logger.fine("Created Daily Tab Window.");
    }
}
