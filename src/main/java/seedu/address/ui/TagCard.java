package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagTask;

/**
 * An UI component that displays information of a {@code Tag}.
 */
public class TagCard extends UiPart<Region> {

    private static final String FXML = "TagListCard.fxml";

    private final Tag tag;

    @FXML
    private HBox tagCardPane;
    @FXML
    private Label id;
    @FXML
    private Label tagName;
    @FXML
    private Label actualId;
    @FXML
    private Hyperlink meetingLink;
    @FXML
    private HBox linkBox;
    @FXML
    private FlowPane persons;
    @FXML
    private HBox personsBox;
    @FXML
    private Label tasks;
    @FXML
    private Label tasksHeader;
    @FXML
    private VBox tasksBox;
    /**
     * Creates a {@code TagCard} with the given {@code Tag}, index, and {@code personList} to display.
     */
    public TagCard(Tag tag, boolean isTagList, int displayedIndex, int actualIndex, List<Person> personList) {
        super(FXML);
        requireAllNonNull(tag, personList);
        assert displayedIndex > 0 : "Displayed index should be greater than 0";
        this.tag = tag;
        id.setText(displayedIndex + ". ");
        tagName.setText(tag.getTagName().tagName);

        initializeActualIndex(isTagList, actualIndex);
        initializeLinks();
        initializePersonList(personList);
        initializeTaskList(tag);
    }

    /**
     * Initializa the actual Index for each {@Code TagCard}
     */
    private void initializeActualIndex(boolean isTagList, int actualIndex) {
        if (isTagList) {
            actualId.setVisible(false);
        } else {
            actualId.setText("(Actual Index: " + actualIndex + ")");
        }
    }

    /**
     * Initialize the link for each {@Code TagCard}
     */
    private void initializeLinks() {
        tag.getMeetingLink().ifPresentOrElse(link -> {
            meetingLink.setText(link.toString());
            setHyperlinkAction(meetingLink, link.link);
        }, () -> {
                linkBox.setVisible(false);
                linkBox.setManaged(false);
            });
    }

    /**
     * Initialize the person list for each {@Code TagCard}
     */
    private void initializePersonList(List<Person> personList) {
        if (!personList.isEmpty()) {
            personList.stream()
                    .sorted(Comparator.comparing(person -> person.getName().fullName))
                    .forEach(person -> persons.getChildren().add(new Label(person.getName().fullName)));
        } else {
            personsBox.setVisible(false);
            personsBox.setManaged(false);
        }
    }

    /**
     * Initialize the task list for each {@Code TagCard}
     */
    private void initializeTaskList(Tag tag) {
        List<TagTask> tagTasksList = tag.getTagTasks();
        if (!tagTasksList.isEmpty()) {
            String taskList = "";
            char start = 'a';
            for (TagTask task : tagTasksList) {
                taskList += "\t" + start + ". " + task.toString() + "\n";
                start++;
            }
            tasksHeader.setText("Tasks: ");
            tasks.setText(taskList);
        } else {
            tasksBox.setVisible(false);
            tasksBox.setManaged(false);
        }
    }

    /**
     * Sets the {@code hyperlink} to open an {@code url} when selected.
     */
    private void setHyperlinkAction(Hyperlink hyperlink, URL url) {
        requireAllNonNull(hyperlink, url);

        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!Desktop.isDesktopSupported()) {
                    return;
                }
                try {
                    Desktop.getDesktop().browse(url.toURI());
                } catch (URISyntaxException e) {
                    new DialogWindow("Link cannot be converted to URI format").show();
                } catch (IOException e) {
                    new DialogWindow("Unable to launch browser").show();
                }
            }
        });
    }

    /**
     * Indicates whether some obj object is "equal to" this one.
     * Two {@code TagCard}s are equivalent if they represent the same {@code Tag}.
     *
     * @param obj the reference object with which to compare
     * @return true if the two objects are equivalent, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TagCard)) {
            return false;
        }
        TagCard tagCard = (TagCard) obj;
        return tag.equals(tagCard.tag);
    }

    /**
     * Returns a hash code value for {@code TagCard}.
     * The hash code value for {@code TagCard} is equivalent to
     * the hash code value of the {@code Tag} contained within.
     *
     * @return a hash code value for this {@code TagCard}
     */
    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
