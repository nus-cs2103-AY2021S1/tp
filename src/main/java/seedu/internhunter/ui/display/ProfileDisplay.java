package seedu.internhunter.ui.display;

import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_DATA_LIST;
import static seedu.internhunter.ui.display.DisplayKeyList.PROFILE_DISPLAY_KEY_LIST;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * A UI component that displays all the information of a {@code ProfileItem}.
 */
public class ProfileDisplay extends InformationDisplay<ProfileItem> {

    /**
     * A function that removes the bracket of the string and indent each attribute.
     */
    private final Function<String, String> editString = string
        -> string.substring(1, string.length() - 1).replaceAll(", ", "\n");

    /**
     * A predicate that checks if the current key is a descriptor.
     */
    private final Predicate<String> isDescriptors = key -> key.equals(DESCRIPTORS_DISPLAY_NAME);

    /**
     * A function that formats the information of the profile with bullet points.
     */
    private final Function<String, String> formatProfileDetail = string -> {
        string = BULLET_WITH_ONE_SPACE + string;
        return string.replaceAll("\n", "\n" + BULLET_WITH_ONE_SPACE);
    };

    /**
     * Creates a {@code ProfileDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param profileItem The profile item to be displayed.
     */
    private ProfileDisplay(Stage primaryStage, ProfileItem profileItem) {
        super(primaryStage, profileItem);

        initializeProfileDisplayGui();
    }

    /**
     * Factory method that creates and displays the particular profile item's information at that index.
     *
     * @param profileItems The list of profile items.
     * @param index The Index of the display to be displayed.
     * @param primaryStage The stage in which this display should show.
     * @return An Optional containing the display information of the profile at that particular Index.
     */
    public static Optional<ProfileDisplay> getProfileDisplay(
        ObservableList<ProfileItem> profileItems, int index, Stage primaryStage) {

        if (IS_EMPTY_DATA_LIST.test(profileItems)) {
            return Optional.empty();
        }
        return Optional.of(new ProfileDisplay(primaryStage, profileItems.get(index)));
    }

    /**
     * Sets the title and information of the {@code profileItem} for display.
     */
    private void initializeProfileDisplayGui() {
        setDisplayName();
        setProfileInformation();
    }

    /**
     * Sets the title of the {@code profileItem} for display.
     */
    private void setDisplayName() {
        Object title = mapping.get(TITLE_DISPLAY_NAME);
        setInformationTitle(title.toString());
    }

    /**
     * Sets the information of the {@code profileItem} for display.
     */
    private void setProfileInformation() {
        setInformation(editString, formatProfileDetail, isDescriptors, TabName.PROFILE, PROFILE_DISPLAY_KEY_LIST);
    }

}
