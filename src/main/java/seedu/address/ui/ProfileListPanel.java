package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing a list of Profiles from Persons.
 */
public class ProfileListPanel extends UiPart<Region> {

    public static final String FXML = "ProfileListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProfileListPanel.class);

    @FXML
    private ListView<Person> profileListView;

    /**
     * Creates a {@code ProfileListPanel} with the given {@code ObservableList}.
     */
    public ProfileListPanel(ObservableList<Person> personList) {
        super(FXML);
        update(personList);
        logger.info("ProfileListPanel created");
    }

    /**
     * Clears the profile list panel.
     */
    public void clear() {
        profileListView.setItems(FXCollections.observableArrayList());
        profileListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * Updates the {@code ProfileListPanel} with the given {@code ObservableList}
     */
    public void update(ObservableList<Person> personList) {
        profileListView.setItems(personList);
        profileListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Profile} using a {@code ProfileCard}.
     */
    class ProfileListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
