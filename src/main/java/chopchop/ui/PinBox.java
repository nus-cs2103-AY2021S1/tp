package chopchop.ui;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import chopchop.logic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class PinBox extends UiPart<Region> {
    private static final String EMPTY_PROMPT = "You haven't cooked anything yet.";
    private static final String FXML = "PinBox.fxml";
    private Logic logic;

    @FXML
    private TextArea pins;

    @FXML
    private TextArea header1;

    @FXML
    private TextArea header2;

    @FXML
    private TextArea body1;

    @FXML
    private TextArea body2;


    /**
     * Creates a {@code PinBox}.
     */
    public PinBox(Logic logic) {
        super(FXML);
        this.logic = logic;
        pins.setText("Statistics\n");
        header1.setText("Recently cooked recipe");
        body1.setText(EMPTY_PROMPT);
        header2.setText("Ingredients expiring soon");
    }

    public void setStatisticsToUser(String recentRecipes, String expiringIngredients) {
        requireAllNonNull(recentRecipes, expiringIngredients);
        body1.setText(recentRecipes);
        body2.setText(expiringIngredients);
    }
}
