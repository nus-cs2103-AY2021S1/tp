package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * The tests do not cover invalid Person valid index because invalid person is covered by other test,
 * and would be unnecessary to add here to overlap in testing.
 */
public class DeletePersonCommandTest {

    @Test
    public void execute_validIndexValidPersonNotAddedToList_throwsCommandException() {
        Person.getAllPeople().clear();
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        assertThrows(CommandException.class, () -> deletePersonCommand.execute(model)); //
    }

    @Test
    public void execute_invalidIndexValidPersonNotAddedToList_throwsCommandException() {
        Person.getAllPeople().clear();
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(GIT_USERINDEX_SECOND_TEAMMATE);
        assertThrows(CommandException.class, () -> deletePersonCommand.execute(model)); //
    }

    @Test
    public void execute_invalidGitUserIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        Person person = DESC_A;
        project.addParticipation(person);

        Participation participation = project.getParticipation(GIT_USERINDEX_SECOND_TEAMMATE.getGitUserNameString());
        model.addPerson(person);
        assertThrows(NullPointerException.class, () -> model.addParticipation(participation));
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirst = new DeletePersonCommand(
                GIT_USERINDEX_FIRST_TEAMMATE);
        DeletePersonCommand deleteSecond = new DeletePersonCommand(
                GIT_USERINDEX_FIRST_TEAMMATE);
        DeletePersonCommand deleteThird = new DeletePersonCommand(
                GIT_USERINDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertTrue(deleteFirst.equals(deleteFirst));

        // same values -> returns true
        assertTrue(deleteFirst.equals(deleteSecond));

        // different types -> returns false
        assertFalse(deleteFirst.equals(1));

        // null -> returns false
        assertFalse(deleteFirst.equals(null));

        // different gitUserName -> returns false
        assertFalse(deleteFirst.equals(deleteThird));
    }
}
