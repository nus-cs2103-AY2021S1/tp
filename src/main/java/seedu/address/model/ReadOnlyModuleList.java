package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Module;

public interface ReadOnlyModuleList {
    ObservableList<Module> getModuleList();
}
