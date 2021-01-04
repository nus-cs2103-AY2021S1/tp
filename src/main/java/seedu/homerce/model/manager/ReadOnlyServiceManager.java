package seedu.homerce.model.manager;

import javafx.collections.ObservableList;
import seedu.homerce.model.service.Service;

/**
 * Unmodifiable view of an homerce
 */
public interface ReadOnlyServiceManager {
    /**
     * Returns an unmodifiable view of the services list.
     * This list will not contain any duplicate services.
     */
    ObservableList<Service> getServiceList();
}
