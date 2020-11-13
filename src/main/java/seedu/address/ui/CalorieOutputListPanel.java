package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.calorie.Output;

/**
 * Panel containing a list of Outputs.
 */
public class CalorieOutputListPanel extends UiPart<Region> {

    private static final String FXML = "CalorieOutputListPanel.fxml";

    @FXML
    private ListView<Output> calorieOutputListView;

    /**
     * Creates an empty {@code CalorieOutputListPanel}.
     */
    public CalorieOutputListPanel() {
        super(FXML);
    }

    /**
     * Updates the {@code CalorieOutputListPanel} with the given {@code ObservableList}.
     *
     * @param outputList the list of Outputs to be displayed.
     */
    public void update(ObservableList<Output> outputList) {
        calorieOutputListView.setItems(outputList);
        calorieOutputListView.setCellFactory(listView -> new CalorieOutputListViewCell());
    }

    /**
     * Clears the {@code CalorieOutputListPanel} with an empty {@code ObservableList}.
     */
    public void clear() {
        calorieOutputListView.setItems(FXCollections.observableArrayList());
        calorieOutputListView.setCellFactory(listView -> new CalorieOutputListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Output} using a {@code CalorieOutputCard}.
     */
    class CalorieOutputListViewCell extends ListCell<Output> {
        @Override
        protected void updateItem(Output calorieOutput, boolean empty) {
            super.updateItem(calorieOutput, empty);

            if (empty || calorieOutput == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalorieOutputCard(calorieOutput, getIndex() + 1).getRoot());
            }
        }
    }

}
