package chopchop.ui;

import chopchop.MainApp;
import chopchop.logic.Logic;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    private final ObservableList<Recipe> recipeObservableList;
    private final ObservableList<Ingredient> ingredientObservableList;
    private final FilteredList<Recipe> recommendedRecipeObservableList;
    private final FilteredList<Recipe> expiringRecipeObservableList;

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
        this.recipeObservableList = logic.getFilteredRecipeList();
        this.ingredientObservableList = logic.getFilteredIngredientList();
        this.recommendedRecipeObservableList = logic.getRecommendedRecipeList();
        this.expiringRecipeObservableList = logic.getExpiringRecipeList();

        // TODO: Edit to account for loading of recipes/ingredients after UI
        this.recipeObservableList.addListener((ListChangeListener<Recipe>) c -> {
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
        this.ingredientObservableList.addListener((ListChangeListener<Ingredient>) c -> this.displayIngredientList());

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
        var recipeViewPanel = new RecipeViewPanel(this.recipeObservableList);
        this.displayAreaPlaceholder.getChildren().setAll(recipeViewPanel.getRoot());
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
        var ingredientViewPanel = new IngredientViewPanel(this.ingredientObservableList);
        this.displayAreaPlaceholder.getChildren().setAll(ingredientViewPanel.getRoot());
        this.selectIngredientButton();
    }

    /**
     * Displays the RecommendationViewPanel on the swappable display region.
     */
    protected void displayRecommendationList() {
        var recommendationViewPanel = new RecommendationViewPanel(this.recommendedRecipeObservableList,
                this.expiringRecipeObservableList);
        this.displayAreaPlaceholder.getChildren().setAll(recommendationViewPanel.getRoot());
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
