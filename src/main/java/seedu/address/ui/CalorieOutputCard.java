package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.calorie.Output;

/**
 * An UI component that displays information of a {@code Output}.
 */
public class CalorieOutputCard extends UiPart<Region> {

    private static final String FXML = "CalorieOutputListCard.fxml";

    public final Output calorieOutput;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label calorieCount;
    @FXML
    private Label time;
    @FXML
    private Label exercise;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CalorieOutputCard(Output calorieOutput, int displayedIndex) {
        super(FXML);
        this.calorieOutput = calorieOutput;
        id.setText(displayedIndex + ". ");
        time.setText("Time: " + calorieOutput.getTime().toString());
        exercise.setText("Exercise: " + calorieOutput.getExercise().toString());
        calorieCount.setText("Calories: " + calorieOutput.getCalorieCount().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalorieInputCard)) {
            return false;
        }

        // state check
        CalorieOutputCard card = (CalorieOutputCard) other;
        return id.getText().equals(card.id.getText())
                && calorieOutput.equals(card.calorieOutput);
    }

}
