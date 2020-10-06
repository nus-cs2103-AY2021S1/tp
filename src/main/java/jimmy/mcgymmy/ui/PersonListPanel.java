package jimmy.mcgymmy.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import jimmy.mcgymmy.commons.core.LogsCenter;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Food> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Food> foodList) {
        super(FXML);
        personListView.setItems(foodList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class PersonListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FoodCard(food, getIndex() + 1).getRoot());
            }
        }
    }

}
