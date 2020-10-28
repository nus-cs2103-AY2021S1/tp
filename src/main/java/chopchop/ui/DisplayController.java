package chopchop.ui;

import chopchop.MainApp;
import chopchop.logic.Logic;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

        this.displayAreaPlaceholder.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                this.displayWelcomeMessage();
                this.resetButtons();
            }
        });

        if (!logic.getFilteredRecipeList().isEmpty()) {
            this.displayRecipeList();
        } else if (!logic.getFilteredIngredientList().isEmpty()) {
            this.displayIngredientList();
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
        RecipeViewPanel recipeViewPanel = new RecipeViewPanel(recipeObservableList);
        displayAreaPlaceholder.getChildren().setAll(recipeViewPanel.getRoot());
        selectRecipeButton();
    }

    /**
     * Displays the RecipeDisplay on the swappable display region.
     */
    protected void displayRecipe(Recipe recipe) {
        RecipeDisplay recipeDisplay = new RecipeDisplay(recipe);
        displayAreaPlaceholder.getChildren().setAll(recipeDisplay.getRoot());
        selectRecipeButton();
    }

    /**
     * Displays the IngredientViewPanel on the swappable display region.
     */
    protected void displayIngredientList() {
        IngredientViewPanel ingredientViewPanel = new IngredientViewPanel(ingredientObservableList);
        displayAreaPlaceholder.getChildren().setAll(ingredientViewPanel.getRoot());
        selectIngredientButton();
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
        // To add more code.
        this.handleNotification();
    }
}
