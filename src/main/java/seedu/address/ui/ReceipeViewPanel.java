package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class ReceipeViewPanel extends UiPart<Region> {
    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "RecipeViewPanel.fxml";
    private ObservableList<Recipe> recipeObservableList;

    @FXML
    private ScrollPane recipePanel;
    @FXML
    private GridPane recipeGridView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public RecipeViewPanel(ObservableList<Recipe> recipeList) {
        super(FXML);
        recipeObservableList.setItems(recipeList);
    }


}
