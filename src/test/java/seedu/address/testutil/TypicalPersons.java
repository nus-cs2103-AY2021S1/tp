package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_AI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_AI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_FIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_HANG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_MODEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project ALICE = new ProjectBuilder().withProjectName("Alice Pauline")
            .withProjectDescription("123, Jurong West Ave 6, #08-111").withRepoUrl("https://github.com/a/a.git")
            .withDeadline("21-03-2020 00:00:00")
            .withTags("friends").withTasks("Write DG", "Write user stories").build();
    public static final Project BENSON = new ProjectBuilder().withProjectName("Benson Meier")
            .withProjectDescription("311, Clementi Ave 2, #02-25")
            .withRepoUrl("https://github.com/b/b.git").withDeadline("21-03-2020 00:00:00")
            .withTags("owesMoney", "friends").withTasks("Practice presentation").build();
    public static final Project CARL = new ProjectBuilder().withProjectName("Carl Kurz")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/c/c.git")
            .withProjectDescription("wall street").withTasks("Read info pack").build();
    public static final Project DANIEL = new ProjectBuilder().withProjectName("Daniel Meier")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/d/d.git")
            .withProjectDescription("10th street").withTags("friends").build();
    public static final Project ELLE = new ProjectBuilder().withProjectName("Elle Meyer")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/e/e.git")
            .withProjectDescription("michegan ave").build();
    public static final Project FIONA = new ProjectBuilder().withProjectName("Fiona Kunz")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/f/f.git").withProjectDescription(
                "little tokyo").build();
    public static final Project GEORGE = new ProjectBuilder().withProjectName("George Best")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/g/g.git").withProjectDescription(
                "4th street").build();

    // Manually added
    public static final Project HOON = new ProjectBuilder().withProjectName("Hoon Meier")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl("https://github.com/h/h.git")
            .withProjectDescription("little india").build();
    public static final Project IDA = new ProjectBuilder().withProjectName("Ida Mueller")
            .withDeadline("21-03-2020 00:00:00").withRepoUrl(VALID_REPOURL_B)
            .withProjectDescription("chicago ave").build();

    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project AMY = new ProjectBuilder().withProjectName(VALID_PROJECT_NAME_AI)
            .withDeadline(VALID_DEADLINE_AI).withRepoUrl(VALID_REPOURL_A).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_AI)
            .withTags(VALID_PROJECT_TAG_FIEND).withTasks(VALID_PROJECT_TAG_DG, VALID_TASK_MODEL).build();
    public static final Project BOB = new ProjectBuilder().withProjectName(VALID_PROJECT_NAME_BOT)
            .withDeadline(VALID_DEADLINE_BOT).withRepoUrl(VALID_REPOURL_B).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_BOT)
            .withTags(VALID_PROJECT_TAG_HANG, VALID_PROJECT_TAG_FIEND).withTasks(VALID_PROJECT_TAG_DG).build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
