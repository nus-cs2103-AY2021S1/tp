//@@author fall9x

package chopchop.ui;

import chopchop.MainApp;
import chopchop.logic.Logic;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Controller class for the swappable display region.
 */
public class DisplayController extends UiPart<Region> {
    private static final String FXML = "DisplayPanel.fxml";
    private static final String WELCOME_MESSAGE = "Welcome to ChopChop, a food recipe management system!";
    private static final String NOTIFICATION_MESSAGE = "Feature will be coming soon!!";

    private final TextDisplay textDisplay;
    private final RecipeViewPanel recipeViewPanel;
    private final IngredientViewPanel ingredientViewPanel;
    private final RecommendationViewPanel recommendationViewPanel;

    @FXML
    private StackPane displayAreaPlaceholder;

    @FXML
    private Button recipeButton;

    @FXML
    private Button ingredientButton;

    @FXML
    private Button recommendationButton;

    /**
     * Creates a {@code DisplayController} with the given {@code Logic}.
     * @param logic
     */
    public DisplayController(Logic logic) {
        super(FXML);
        this.textDisplay = new TextDisplay(WELCOME_MESSAGE);
        this.recipeViewPanel = new RecipeViewPanel(logic.getFilteredRecipeList());
        this.ingredientViewPanel = new IngredientViewPanel(logic.getFilteredIngredientList());
        this.recommendationViewPanel = new RecommendationViewPanel(logic.getRecommendedRecipeList(),
                logic.getExpiringRecipeList());

        // TODO: Edit to account for loading of recipes/ingredients after UI
        logic.getFilteredRecipeList().addListener((ListChangeListener<Recipe>) c -> {
            c.next();

            /*
             * TODO: Make logic more robust
             * Check if a recipe was replaced in the recipe book, with an extra check to account for
             * updateFilteredRecipeList(PREDICATE_SHOW_ALL_ENTRIES).
             */
            if (c.wasReplaced() && !c.getAddedSubList().equals(c.getRemoved())) {
                this.displayRecipe(c.getAddedSubList().get(c.getAddedSize() - 1));
            } else if (c.wasAdded()) {
                this.displayRecipe(c.getAddedSubList().get(c.getAddedSize() - 1));
            } else {
                this.displayRecipeList();
            }
        });

        logic.getFilteredIngredientList().addListener((ListChangeListener<Ingredient>) c ->
                this.displayIngredientList());

        if (!logic.getFilteredRecipeList().isEmpty()) {
            this.displayRecipeList();
        } else {
            this.displayWelcomeMessage();
        }
    }

    /**
     * Displays the RecipeViewPanel on the swappable display region.
     */
    protected void displayWelcomeMessage() {
        this.displayAreaPlaceholder.getChildren().setAll(this.textDisplay.getRoot());
    }

    /**
     * Displays the RecipeViewPanel on the swappable display region.
     */
    protected void displayRecipeList() {
        this.displayAreaPlaceholder.getChildren().setAll(this.recipeViewPanel.getRoot());
        this.selectRecipeButton();
    }

    /**
     * Displays the RecipeDisplay on the swappable display region.
     */
    protected void displayRecipe(Recipe recipe) {
        var recipeDisplay = new RecipeDisplay(recipe);
        this.displayAreaPlaceholder.getChildren().setAll(recipeDisplay.getRoot());
        this.selectRecipeButton();
    }

    /**
     * Displays the IngredientViewPanel on the swappable display region.
     */
    protected void displayIngredientList() {
        this.displayAreaPlaceholder.getChildren().setAll(this.ingredientViewPanel.getRoot());
        this.selectIngredientButton();
    }

    /**
     * Displays the RecommendationViewPanel on the swappable display region.
     */
    protected void displayRecommendationList() {
        this.displayAreaPlaceholder.getChildren().setAll(this.recommendationViewPanel.getRoot());
        this.selectRecommendationButton();
    }

    /**
     * Opens the notification window or focuses on it if it's already opened.
     */
    public void handleNotification() {
        var image = new ImageView(MainApp.class.getResource("/images/timer.png").toExternalForm());
        image.setFitHeight(40);
        image.setFitWidth(40);

        var alert = new Alert(Alert.AlertType.INFORMATION, NOTIFICATION_MESSAGE);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setGraphic(image);
        alert.showAndWait();
    }

    /**
     * Resets buttons in navigation bar to default style.
     */
    private void resetButtons() {
        this.recipeButton.getStyleClass().remove("tab-button-selected");
        this.ingredientButton.getStyleClass().remove("tab-button-selected");
        this.recommendationButton.getStyleClass().remove("tab-button-selected");
    }

    /**
     * Changes the recipe button style to selected.
     */
    private void selectRecipeButton() {
        this.resetButtons();
        this.recipeButton.getStyleClass().add("tab-button-selected");
    }

    private void selectIngredientButton() {
        this.resetButtons();
        this.ingredientButton.getStyleClass().add("tab-button-selected");
    }

    private void selectRecommendationButton() {
        this.resetButtons();
        this.recommendationButton.getStyleClass().add("tab-button-selected");
    }

    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleRecipePanel() {
        this.displayRecipeList();
    }

    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleIngredientPanel() {
        this.displayIngredientList();
    }

    /**
     * Displays the recommendations panel.
     */
    @FXML
    public void handleRecommendations() {
        this.displayRecommendationList();
    }
}
