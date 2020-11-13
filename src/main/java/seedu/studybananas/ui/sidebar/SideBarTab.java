package seedu.studybananas.ui.sidebar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.studybananas.ui.UiPart;
import seedu.studybananas.ui.util.Observable;
import seedu.studybananas.ui.util.Observer;
import seedu.studybananas.ui.util.SingletonUiState;
import seedu.studybananas.ui.util.UiStateType;

public class SideBarTab extends UiPart<Region> implements Observer<UiStateType> {
    private static final String FXML = "SideBarTab.fxml";
    private static final String BUTTON_FOCUSED_BACKGROUND_COLOR = "-fx-background-color: #E2B603";

    @FXML
    private Button tab;
    @FXML
    private ImageView tabImage;
    @FXML
    private Label tabText;

    private SingletonUiState uiState;
    private UiStateType tabType;

    /**
     * Constructor for sidebar tab.
     */
    public SideBarTab(Image image, String description) {
        super(FXML);

        //subscribe to the UiState
        uiState = SingletonUiState.getInstance();
        subscribe(uiState);

        tabImage.setImage(image);
        tabText.setText(description);

        switch (description) {
        case "SCHEDULE":
            this.tabType = UiStateType.SCHEDULE;
            tabText.setStyle("-fx-text-fill: #0066ff; "
                    + "-fx-font-weight: bold;"); //Schedule button is focused when the app starts
            break;
        case "FLASHCARDS":
            this.tabType = UiStateType.FLASHCARD;
            break;
        case "QUIZ":
            this.tabType = UiStateType.QUIZ;
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    @FXML
    private void handleTabPressed() {
        uiState.updateState(this.tabType);
    }

    @Override
    public void subscribe(Observable news) {
        news.register(this);
    }

    @Override
    public void update(UiStateType state) {
        if (this.tabType == state) {
            tabText.setStyle("-fx-text-fill: #0066ff; "
                    + "-fx-font-weight: bold;");
        } else {
            tabText.setStyle("-fx-text-fill: #cccccc; "
                    + "-fx-font-weight: 100;");
        }
    }


}
