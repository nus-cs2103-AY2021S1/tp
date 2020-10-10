package chopchop.ui;

import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class RecipeViewPanel extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "RecipeViewPanel.fxml";
    private static final int ROWS = 3;
    private static final int START_COL = -1;

    // Only 3 rows of recipes will be displayed.
    private ObservableList<Recipe> recipeObservableList;

    @FXML
    private ScrollPane recipePanel;

    @FXML
    private GridPane recipeGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code ObservableList}.
     */
    public RecipeViewPanel(ObservableList<Recipe> recipeList) {
        super(FXML);
        recipeObservableList = recipeList;
        recipeObservableList.addListener(new ListChangeListener<Recipe>() {
            @Override
            public void onChanged(Change<? extends Recipe> c) {
                refresh();
            }
        });
        populate();
    }

    private int calculate_row(int index) {
        return index % ROWS;
    }

    /**
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
        int row;
        int col = START_COL;
        for (int i = 0; i < recipeObservableList.size(); i++) {
            Recipe recipe = recipeObservableList.get(i);
            RecipeCard recipeCard = new RecipeCard(recipe);
            row = calculate_row(i);
            if (row == 0) {
                col++;
            }
            recipeGridView.add(recipeCard.getRoot(), col, row);
        }
    }

    /**
     * Refresh the recipe grid view.
     */
    private void refresh() {
        recipeGridView.getChildren().clear();
        populate();
    }

}
