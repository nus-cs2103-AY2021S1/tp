package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final List<Person> currentPersonList;
    private final List<Person> originalPersonList;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        this.currentPersonList = personList;
        this.originalPersonList = personList;
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, List<Person> originalPersonList) {
        super(FXML);
        this.currentPersonList = personList;
        this.originalPersonList = originalPersonList;
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                int actualIndex = getActualIndex(person);
                boolean isPersonList = currentPersonList.equals(originalPersonList);
                setGraphic(new PersonCard(person, isPersonList, getIndex() + 1, actualIndex).getRoot());
            }
        }

        protected int getActualIndex(Person person) {
            int actualIndex = 0;
            for (int i = 0; i < originalPersonList.size(); i++) {
                if (originalPersonList.get(i).equals(person)) {
                    actualIndex = i + 1;
                    break;
                }
            }
            return actualIndex;
        }
    }

}
