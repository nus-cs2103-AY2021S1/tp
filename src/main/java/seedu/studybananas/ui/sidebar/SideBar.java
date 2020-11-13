package seedu.studybananas.ui.sidebar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.studybananas.logic.Logic;
import seedu.studybananas.ui.UiPart;
import seedu.studybananas.ui.util.Observable;
import seedu.studybananas.ui.util.Observer;
import seedu.studybananas.ui.util.SingletonUiState;
import seedu.studybananas.ui.util.UiStateType;

public class SideBar extends UiPart<Region> implements Observer<UiStateType> {
    private static final String FXML = "SideBar.fxml";

    private final Image scheduleImage = new Image(this.getClass()
            .getResourceAsStream("/images/icon_schedule.png"));
    private final Image flashcardsImage = new Image(this.getClass()
            .getResourceAsStream("/images/icon_flashcards.png"));
    private final Image quizImage = new Image(this.getClass()
            .getResourceAsStream("/images/icon_quiz.png"));

    private final List<SideBarTab> studyBananasTabs = Arrays.asList(
            new SideBarTab(scheduleImage, "SCHEDULE"),
            new SideBarTab(flashcardsImage, "FLASHCARDS"),
            new SideBarTab(quizImage, "QUIZ"));

    private SingletonUiState uiState;

    @FXML
    private VBox sideBar;

    /**
     * Constructor of the SideBar, construct it with the {@primaryStage} and the Logic.
     * @param primaryStage used by {@Code SideBarHelpMenu}
     * @param logic used by {@Code SideBarHelpMenu}
     */
    public SideBar(Stage primaryStage, Logic logic) {
        super(FXML);

        //subscribe to UiState
        uiState = SingletonUiState.getInstance();
        subscribe(uiState);

        //set label
        List<Node> tabs = studyBananasTabs.stream().map(tab -> tab.getRoot()).collect(Collectors.toList());
        sideBar.getChildren().addAll(tabs);

        VBox padding = new VBox();
        padding.setMinHeight(330);

        //temporary set the middle empty part
        sideBar.getChildren().add(padding);

        //set sidebar help menu
        sideBar.getChildren().add(new SideBarHelpMenu(primaryStage, logic).getRoot());

    }


    @Override
    public void subscribe(Observable news) {
        news.register(this);
    }

    @Override
    public void update(UiStateType state) {
    }
}
