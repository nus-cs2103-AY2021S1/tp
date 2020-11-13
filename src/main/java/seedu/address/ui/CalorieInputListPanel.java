package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.calorie.Input;

/**
 * Panel containing a list of Inputs.
 */
public class CalorieInputListPanel extends UiPart<Region> {

    private static final String FXML = "CalorieInputListPanel.fxml";

    @FXML
    private ListView<Input> calorieInputListView;

    /**
     * Creates an empty {@code CalorieInputListPanel}.
     */
    public CalorieInputListPanel() {
        super(FXML);
    }

    /**
     * Updates the {@code CalorieInputListPanel} with the given {@code ObservableList}.
     *
     * @param inputList the list of Inputs to be displayed.
     */
    public void update(ObservableList<Input> inputList) {
        calorieInputListView.setItems(inputList);
        calorieInputListView.setCellFactory(listView -> new CalorieInputListViewCell());
    }

    /**
     * Clears the {@code CalorieInputListPanel} with an empty {@code ObservableList}.
     */
    public void clear() {
        calorieInputListView.setItems(FXCollections.observableArrayList());
        calorieInputListView.setCellFactory(listView -> new CalorieInputListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Input} using a {@code CalorieInputCard}.
     */
    class CalorieInputListViewCell extends ListCell<Input> {
        @Override
        protected void updateItem(Input calorieInput, boolean empty) {
            super.updateItem(calorieInput, empty);

            if (empty || calorieInput == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalorieInputCard(calorieInput, getIndex() + 1).getRoot());
            }
        }
    }

}
