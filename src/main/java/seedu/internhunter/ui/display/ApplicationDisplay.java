package seedu.internhunter.ui.display;

import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_DATA_LIST;
import static seedu.internhunter.ui.display.DisplayKeyList.APPLICATION_DISPLAY_KEY_LIST;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * A UI component that displays all the information of a {@code ApplicationItem}.
 */
public class ApplicationDisplay extends InformationDisplay<ApplicationItem> {

    /**
     * A predicate that checks whether the current key is requirements.
     */
    private final Predicate<String> isRequirements = key -> key.equals(REQUIREMENTS_DISPLAY_NAME);

    /**
     * A function that returns back the same string. Mainly for polymorphism usage.
     */
    private final Function<String, String> returnSameString = string -> string;

    /**
     * Creates a {@code ApplicationDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param applicationItem The application item to be displayed.
     */
    private ApplicationDisplay(Stage primaryStage, ApplicationItem applicationItem) {
        super(primaryStage, applicationItem);

        initializeApplicationDisplayGui();
    }

    /**
     * Factory method that creates and displays the particular application item's information at that index.
     *
     * @param applicationItems The list of application items.
     * @param index The Index of the display to be displayed.
     * @param primaryStage The stage in which this display should show.
     * @return An Optional containing the display information of the Application at that particular Index.
     */
    public static Optional<ApplicationDisplay> getApplicationDisplay(
        ObservableList<ApplicationItem> applicationItems, int index, Stage primaryStage) {

        if (IS_EMPTY_DATA_LIST.test(applicationItems)) {
            return Optional.empty();
        }
        return Optional.of(new ApplicationDisplay(primaryStage, applicationItems.get(index)));
    }

    /**
     * Sets the job title and information of the {@code applicationItem} for display.
     */
    private void initializeApplicationDisplayGui() {
        setJobTitle();
        setApplicationInformation();
    }

    /**
     * Sets the job title of the {@code applicationItem} for display.
     */
    private void setJobTitle() {
        Object jobTitle = mapping.get(JOB_TITLE_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * Sets the information of the {@code applicationItem} for display.
     */
    private void setApplicationInformation() {
        setInformation(editString, returnSameString, isRequirements, TabName.APPLICATION, APPLICATION_DISPLAY_KEY_LIST);
    }

}
