package seedu.homerce.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;
import seedu.homerce.ui.clientpanel.ClientListPanel;
import seedu.homerce.ui.expensepanel.ExpenseListPanel;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;
import seedu.homerce.ui.schedulepanel.SchedulePanel;
import seedu.homerce.ui.servicepanel.ServiceListPanel;

/**
 * Panel containing the list of tabs.
 */
public class SideTabsBar extends UiPart<Region> {
    private static final String FXML = "SideTabsBar.fxml";

    private final Logger logger = LogsCenter.getLogger(SideTabsBar.class);

    private Consumer<String> consumer;

    /**
     * Constructor for SideTabsBar
     *
     * @param consumer
     */
    public SideTabsBar(Consumer<String> consumer) {
        super(FXML);
        this.consumer = consumer;
    }

    @FXML
    private void handleSwitchToClientTab() {
        consumer.accept(ClientListPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToServiceTab() {
        consumer.accept(ServiceListPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToAppointmentTab() {
        consumer.accept(AppointmentListPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToRevenueTab() {
        consumer.accept(RevenueListPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToExpenseTab() {
        consumer.accept(ExpenseListPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToScheduleTab() {
        consumer.accept(SchedulePanel.TAB_NAME);
    }
}
