package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project APEAKAPP = new ProjectBuilder().withProjectName("Apeakapp")
            .withProjectDescription("An app for you to speak your mind")
            .withRepoUrl("https://github.com/a/a.git")
            .withDeadline("21-03-2020 00:00:00")
            .withTags("linguistics")
            .withTasks(SampleDataUtil.getTask1(), SampleDataUtil.getTask2())
            .build();
    public static final Project BRICK = new ProjectBuilder().withProjectName("Brick No Meier")
            .withProjectDescription("To destroy the brick and mortar")
            .withRepoUrl("https://github.com/b/b.git")
            .withDeadline("21-03-2020 00:00:00")
            .withTags("amazon", "evil")
            .withTasks(SampleDataUtil.getTask3())
            //            .withMeetings("2020-10-10T15:30:00")
            //            .withPeople("Bob", "Niaz")
            .build();
    public static final Project CARACTIVE = new ProjectBuilder().withProjectName("Caractive")
            .withDeadline("21-03-2020 00:00:00")
            .withRepoUrl("https://github.com/c/c.git")
            .withProjectDescription("best car app for tesla")
            .withTags("electric")
            .withTasks(SampleDataUtil.getTask4()).build();
    public static final Project DAYUM = new ProjectBuilder().withProjectName("Dayum Snap")
            .withDeadline("21-03-2020 00:00:00")
            .withRepoUrl("https://github.com/d/d.git")
            .withProjectDescription("yet another stories app")
            .withTags("instagram" , "snapchat").build();
    public static final Project ELFIE = new ProjectBuilder().withProjectName("Elfie save")
            .withDeadline("21-03-2020 00:00:00")
            .withRepoUrl("https://github.com/e/e.git")
            .withProjectDescription("save elves from persecution").build();
    public static final Project FANS = new ProjectBuilder().withProjectName("Fans")
            .withDeadline("21-03-2020 00:00:00")
            .withRepoUrl("https://github.com/f/f.git")
            .withProjectDescription("an app only for fans").build();
    public static final Project GERRY = new ProjectBuilder().withProjectName("Gerry Mander")
            .withDeadline("21-03-2020 00:00:00")
            .withRepoUrl("https://github.com/g/g.git")
            .withProjectDescription("Call us to rig any election").build();

    // Manually added
    public static final Project HOON = new ProjectBuilder().withProjectName("Hoon Meier")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/h/h.git")
            .withProjectDescription("Made with love and git").build();
    public static final Project IDA = new ProjectBuilder().withProjectName("Ida Mueller")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl(VALID_REPOURL_B)
            .withProjectDescription("german quality software").build();

    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project AI = new ProjectBuilder().withProjectName(VALID_PROJECT_NAME_A)
            .withDeadline(VALID_DEADLINE_A).withRepoUrl(VALID_REPOURL_A).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_A)
            .withTags(VALID_PROJECT_TAG_B).withTasks(SampleDataUtil.getTask5(), SampleDataUtil.getTask6()).build();
    public static final Project BOT = new ProjectBuilder().withProjectName(VALID_PROJECT_NAME_B)
            .withDeadline(VALID_DEADLINE_B).withRepoUrl(VALID_REPOURL_B).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_B)
            .withTags(VALID_PROJECT_TAG_A, VALID_PROJECT_TAG_B).withTasks(SampleDataUtil.getTask5()).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code MainCatalogue} with all the typical projects.
     */
    public static MainCatalogue getTypicalMainCatalogue() {
        MainCatalogue ab = new MainCatalogue();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(APEAKAPP, BRICK, CARACTIVE, DAYUM, ELFIE, FANS, GERRY));
    }
}
