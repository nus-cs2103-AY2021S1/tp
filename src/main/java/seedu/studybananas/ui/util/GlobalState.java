package seedu.studybananas.ui.util;

import javafx.stage.Stage;
import seedu.studybananas.logic.Logic;
import seedu.studybananas.ui.commons.CommandBox;
import seedu.studybananas.ui.commons.HelpWindow;
import seedu.studybananas.ui.commons.ResponsePopUp;

public class GlobalState {
    private static GlobalState instance;
    private CommandBox scheduleCommandBox;
    private CommandBox flashcardCommandBox;
    private CommandBox quizCommandBox;
    private Stage primaryStage;
    private HelpWindow helpWindow;
    private ResponsePopUp responsePopUp;

    private Logic logic;


    private GlobalState() { }

    public static synchronized GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }
    public void setScheduleCommandBox(CommandBox scheduleCommandBox) {
        this.scheduleCommandBox = scheduleCommandBox;
    }

    public void setFlashcardCommandBox(CommandBox flashcardCommandBox) {
        this.flashcardCommandBox = flashcardCommandBox;
    }

    public void setQuizCommandBox(CommandBox quizCommandBox) {
        this.quizCommandBox = quizCommandBox;
    }

    public void focusScheduleCommandBox() {
        scheduleCommandBox.setCommandBoxToFocus();
    }

    public void focusFlashcardCommandBox() {
        flashcardCommandBox.setCommandBoxToFocus();
    }

    public void focusQuizCommandBox() {
        quizCommandBox.setCommandBoxToFocus();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public void setHelpWindow(HelpWindow helpWindow) {
        this.helpWindow = helpWindow;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public ResponsePopUp getResponsePopUp() {
        return responsePopUp;
    }

    public void setResponsePopUp(ResponsePopUp responsePopUp) {
        this.responsePopUp = responsePopUp;
    }
}
