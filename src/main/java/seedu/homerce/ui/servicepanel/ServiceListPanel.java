package seedu.homerce.ui.servicepanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.model.service.Service;
import seedu.homerce.ui.Panel;

/**
 * Panel containing the list of services.
 */
public class ServiceListPanel extends Panel {
    public static final String TAB_NAME = "Services";

    private static final String FXML = "servicepanel/ServiceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ServiceListPanel.class);

    @FXML
    private ListView<Service> serviceListView;

    /**
     * Creates a {@code ServiceListPanel} with the given {@code ObservableList}.
     */
    public ServiceListPanel(ObservableList<Service> serviceList) {
        super(FXML);
        serviceListView.setItems(serviceList);
        serviceListView.setCellFactory(listView -> new ServiceListPanel.ServiceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Service} using a {@code ServiceCard}.
     */
    class ServiceListViewCell extends ListCell<Service> {
        @Override
        protected void updateItem(Service service, boolean empty) {
            super.updateItem(service, empty);

            if (empty || service == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ServiceCard(service, getIndex() + 1).getRoot());
            }
        }
    }
}
