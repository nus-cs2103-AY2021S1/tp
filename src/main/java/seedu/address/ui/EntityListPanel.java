package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of Hairdressers.
 */
public abstract class EntityListPanel<T> extends UiPart<Region> {

    private static final String FXML = "EntityListPanel.fxml";

    @FXML
    protected Label labelName;

    @FXML
    private ListView<T> entityListView;

    /**
     * Creates a {@code EntityListPanel} with the given {@code ObservableList}.
     */
    public EntityListPanel(ObservableList<T> entityList) {
        super(FXML);
        entityListView.setItems(entityList);
        entityListView.setCellFactory(listView -> new EntityListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Entity} using a {@code Card}.
     */
    class EntityListViewCell extends ListCell<T> {
        @Override
        protected void updateItem(T entity, boolean empty) {
            super.updateItem(entity, empty);

            if (empty || entity == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(getCard(entity, getIndex() + 1).getRoot());
            }
        }
    }
    public abstract UiPart<Region> getCard(T entity, int index);
}
