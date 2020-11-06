package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.testutil.EditTeammateDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class EditTeammateCommandTest {

    @Test
    public void execute_noFieldsModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A).withPersonName("Changes name").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
        Person.getAllPeople().clear();
    }

    @Test
    public void execute_phoneModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A).withPhone("37189382").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
        Person.getAllPeople().clear();
        Project.getAllProjects().clear();
    }

    @Test
    public void execute_emailModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A).withEmail("new@gmail.com").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
        Person.getAllPeople().clear();
        Project.getAllProjects().clear();
    }

    @Test
    public void execute_addressModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A).withAddress("209 Yishun").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
        Person.getAllPeople().clear();
        Project.getAllProjects().clear();
    }

    @Test
    public void execute_multipleFieldsModified_success() throws ParseException {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

        Person originalTeammate = new PersonBuilder(TypicalPersons.DESC_A).build();
        Person editedTeammate = new PersonBuilder(TypicalPersons.DESC_A)
            .withPersonName("Yi Lin").withEmail("geniaaz@hotmail.com").withPhone("349938211").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTeammateCommand.EditTeammateDescriptor descriptor = new EditTeammateDescriptorBuilder(editedTeammate)
            .build();
        EditTeammateCommand editTeammateCommand = new EditTeammateCommand(
            ParsePersonUtil.parseGitUserIndex("Sparrow32"), descriptor);

        String expectedMessage = String.format(EditTeammateCommand.MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        project.addParticipation(originalTeammate);
        model.addPerson(originalTeammate);

        expectedModel.addPerson(editedTeammate);
        projectCopy.addParticipation(editedTeammate);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandSuccess(editTeammateCommand, model, expectedMessage, expectedModel);
        Person.getAllPeople().clear();
        Project.getAllProjects().clear();
    }

    @Test
    public void equal() {

        //editTeammate A
        Person editedTeammateA = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorA = new EditTeammateDescriptorBuilder(
            editedTeammateA).withPhone("4838883").build();

        EditTeammateCommand editA = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorA);

        //editTeammate B
        Person editedTeammateB = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorB = new EditTeammateDescriptorBuilder(
            editedTeammateB).withPhone("4838883").build();

        EditTeammateCommand editB = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorB);

        //editTeammate C
        Person editedTeammateC = new PersonBuilder(TypicalPersons.DESC_B).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorC = new EditTeammateDescriptorBuilder(
            editedTeammateC).withPhone("4838883").build();

        EditTeammateCommand editC = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorC);

        //editTeammate Name
        Person editedTeammateName = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorName = new EditTeammateDescriptorBuilder(
            editedTeammateA).withTeammatetName("New Name").build();

        EditTeammateCommand editName = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorName);

        //editTeammate Phone
        Person editedTeammatePhone = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorPhone = new EditTeammateDescriptorBuilder(
            editedTeammateA).withTeammatetName("New Name").build();

        EditTeammateCommand editPhone = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorPhone);

        //editTeammate Email
        Person editedTeammateEmail = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorEmail = new EditTeammateDescriptorBuilder(
            editedTeammateA).withTeammatetName("New Name").build();

        EditTeammateCommand editEmail = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorEmail);

        //editTeammate Address
        Person editedTeammateAddress = new PersonBuilder(TypicalPersons.DESC_A).build();
        EditTeammateCommand.EditTeammateDescriptor descriptorAddress = new EditTeammateDescriptorBuilder(
            editedTeammateA).withTeammatetName("New Name").build();

        EditTeammateCommand editAddress = new EditTeammateCommand(new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A),
            descriptorAddress);


        // same object -> returns true
        assertEquals(editA, editA);

        // same values -> returns true
        assertEquals(editA, editB);

        // different types -> return false
        assertNotEquals(editA, 1);

        // null -> returns false
        assertNotEquals(editA, null);

        // different Teammate edited -> returns false
        assertNotEquals(editA, editC);

        // changed name -> returns false
        assertNotEquals(editA, editName);

        // changed phone -> returns false
        assertNotEquals(editA, editPhone);

        // changed email -> returns false
        assertNotEquals(editA, editEmail);

        // changed address -> returns false
        assertNotEquals(editA, editAddress);
    }
}
