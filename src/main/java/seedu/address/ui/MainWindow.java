package seedu.address.ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.recipe.CloseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Recipe;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static String themeColor = "#0088c7;";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private RecipeListPanel recipeListPanel;
    private IngredientListPanel ingredientListPanel;
    private ConsumptionListPanel consumptionListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private Scene mainScene;

    @FXML
    private BorderPane container;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private VBox leftPanel;

    @FXML
    private HBox nav;

    @FXML
    private Label recipeOption;

    @FXML
    private Label fridgeOption;

    @FXML
    private Label consumptionOption;


    private JFXDrawersStack drawersStack;
    private JFXDrawer leftDrawer;
    private ScrollPane leftDrawerPane;

    private boolean isShowRecipe = true;
    private boolean isShowIngredient = false;
    private boolean isShowCalories = false;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getWishfulShrinkingFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        this.leftDrawer = new JFXDrawer();
        leftDrawer.setResizeContent(true);
        leftDrawer.setOverLayVisible(false);
        leftDrawer.setResizableOnDrag(true);
        leftDrawer.setId("LEFT");
        this.drawersStack = new JFXDrawersStack();
        leftDrawer.prefWidthProperty().bind(leftPanel.widthProperty());
        fillRecipePanel();
    }

    void fillRecipePanel() {
        recipeListPanel = new RecipeListPanel(logic.getFilteredRecipeList());
        listPanelPlaceholder.getChildren().add(recipeListPanel.getRoot());
        nav.getChildren().get(0).setStyle("-fx-background-color: " + themeColor);
        recipeOption.setStyle("-fx-text-fill: white;");
        fridgeOption.setStyle("");
        consumptionOption.setStyle("");
        nav.getChildren().get(1).setStyle("");
        nav.getChildren().get(2).setStyle("");
    }

    void fillIngredientPanel() {
        ingredientListPanel = new IngredientListPanel(logic.getFilteredIngredientList());
        listPanelPlaceholder.getChildren().add(ingredientListPanel.getRoot());
        nav.getChildren().get(1).setStyle("-fx-background-color: " + themeColor);
        fridgeOption.setStyle("-fx-text-fill: white;");
        recipeOption.setStyle("");
        consumptionOption.setStyle("");
        nav.getChildren().get(0).setStyle("");
        nav.getChildren().get(2).setStyle("");
    }

    void fillConsumptionPanel() {
        consumptionListPanel = new ConsumptionListPanel(logic.getFilteredConsumptionList());
        listPanelPlaceholder.getChildren().add(consumptionListPanel.getRoot());
        nav.getChildren().get(2).setStyle("-fx-background-color: " + themeColor);
        consumptionOption.setStyle("-fx-text-fill: white;");
        fridgeOption.setStyle("");
        recipeOption.setStyle("");
        nav.getChildren().get(0).setStyle("");
        nav.getChildren().get(1).setStyle("");
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.setResizable(true);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.setX(500);
        primaryStage.setY(100);
        leftPanel.setPrefWidth(400);
        nav.setPrefWidth(120);
        //Responsive resizing
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            final double leftPanelSize = primaryStage.getWidth() / 2.5;
            final double leftDrawerPaneSize = primaryStage.getWidth() / 2.6;
            leftPanel.setPrefWidth(leftPanelSize);
            leftDrawer.setMinWidth(leftDrawerPaneSize);
            leftDrawer.setMaxWidth(leftDrawerPaneSize);
            if (leftDrawerPane != null) {
                leftDrawerPane.setMinWidth(leftDrawerPaneSize);
                leftDrawerPane.setMaxWidth(leftDrawerPaneSize);
            }
        };
        primaryStage.widthProperty().addListener(stageSizeListener);

        final ObservableList<String> stylesheets = mainScene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                MainWindow.class.getResource("/css/wishful-shrinking.css").toExternalForm(),
                MainWindow.class.getResource("/css/wishful-shrinking-components.css").toExternalForm());
        primaryStage.show();
    }
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    private void showDrawer(Recipe selected) {
        double drawerSize = primaryStage.getWidth() / 2.6;
        leftDrawer.setDefaultDrawerSize(drawerSize);
        //keep drawer width updated
        leftDrawer.setMaxWidth(drawerSize);
        leftDrawer.setMinWidth(drawerSize);
        leftDrawerPane = new ScrollPane();
        leftDrawerPane.setFitToWidth(true);
        leftDrawerPane.setContent(new SingleRecipeCard(selected).getRoot());
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e ->
                leftDrawer.setSidePane(leftDrawerPane));
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(0.05), e -> {
            listPanelPlaceholder.getChildren().add(drawersStack);
        });
        final KeyFrame kf3 = new KeyFrame(Duration.seconds(0.1), e -> {
            drawersStack.toggle(leftDrawer, true);
        });
        final Timeline timeline = new Timeline(kf1, kf2, kf3);
        Platform.runLater(timeline::play);
    }
    private void hideDrawer() {
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e ->
                drawersStack.toggle(leftDrawer, false));
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(1), e -> {
            listPanelPlaceholder.getChildren().remove(drawersStack);
        });
        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException,
            ParseException, IOException, URISyntaxException {
        try {
            CommandResult commandResult;
            //handle closing drawer
            if (commandText.trim().equals(CloseCommand.COMMAND_WORD)) {
                if (leftDrawer.isClosed()) {
                    commandResult = new CommandResult("Drawer is already closed!");
                    logger.info("Result: " + commandResult.getFeedbackToUser());
                    resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
                    return commandResult;
                }
                hideDrawer();
            } else if (leftDrawer.isOpened()) {
                CommandException commandException =
                        new CommandException("Close drawer first! \n Hint: type \"close\"");
                throw commandException;
            }
            commandResult = logic.execute(commandText);

            //handle showing single recipe
            Recipe selected = commandResult.getRecipe();
            if (selected != null) {
                showDrawer(selected);
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowIngredient() && !isShowIngredient) {
                isShowIngredient = true;
                isShowRecipe = false;
                isShowCalories = false;
                listPanelPlaceholder.getChildren().clear();
                fillIngredientPanel();
            }

            if (commandResult.isShowRecipe() && !isShowRecipe) {
                isShowIngredient = false;
                isShowRecipe = true;
                isShowCalories = false;
                listPanelPlaceholder.getChildren().clear();
                fillRecipePanel();
            }

            if (commandResult.isShowConsumption() && !isShowCalories) {
                isShowIngredient = false;
                isShowRecipe = false;
                isShowCalories = true;
                listPanelPlaceholder.getChildren().clear();
                fillConsumptionPanel();
            }
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (IOException | URISyntaxException e) {
            resultDisplay.setFeedbackToUser("URL is invalid! or WIFI is needed to download the image!");
            throw e;
        }
    }
}
