package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
/**
 * Edits the details of an existing project in the main catalogue.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the project identified "
            + "by the index number used in the displayed project list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PROJECT_NAME + "PROJECTNAME] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_REPOURL + "REPOURL] "
            + "[" + PREFIX_DESCRIPTION + "PROJECTDESCRIPTION] "
            + "[" + PREFIX_PROJECT_TAG + "PROJECT TAG]...\n"
            + "[" + PREFIX_TASK + "TASK]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "29-02-2020 00:00:00 "
            + PREFIX_REPOURL + "https://github.com/a/a.git";

    public static final String MESSAGE_EDIT_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the main catalogue.";

    private final Index index;
    private final EditProjectDescriptor editProjectDescriptor;

    /**
     * @param index of the project in the filtered project list to edit
     * @param editProjectDescriptor details to edit the project with
     */
    public EditCommand(Index index, EditProjectDescriptor editProjectDescriptor) {
        requireNonNull(index);
        requireNonNull(editProjectDescriptor);

        this.index = index;
        this.editProjectDescriptor = new EditProjectDescriptor(editProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!projectToEdit.isSameProject(editedProject) && model.hasProject(editedProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject));
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * edited with {@code editProjectDescriptor}.
     */
    private static Project createEditedProject(Project projectToEdit, EditProjectDescriptor editProjectDescriptor) {
        assert projectToEdit != null;

        ProjectName updatedProjectName = editProjectDescriptor.getProjectName().orElse(projectToEdit.getProjectName());
        Deadline updatedDeadline = editProjectDescriptor.getDeadline().orElse(projectToEdit.getDeadline());
        RepoUrl updatedRepoUrl = editProjectDescriptor.getRepoUrl().orElse(projectToEdit.getRepoUrl());
        ProjectDescription updatedProjectDescription = editProjectDescriptor.getProjectDescription()
            .orElse(projectToEdit.getProjectDescription());
        Set<ProjectTag> updatedProjectTags = editProjectDescriptor.getProjectTags().orElse(
            projectToEdit.getProjectTags());
        Set<Task> updatedTasks = editProjectDescriptor.getTasks().orElse(projectToEdit.getTasks());

        return new Project(updatedProjectName, updatedDeadline, updatedRepoUrl, updatedProjectDescription,
                updatedProjectTags, new HashMap<>(), updatedTasks);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editProjectDescriptor.equals(e.editProjectDescriptor);
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {
        private ProjectName projectName;
        private Deadline deadline;
        private RepoUrl repoUrl;
        private ProjectDescription projectDescription;
        private Set<ProjectTag> projectTags;
        private Set<Task> tasks;

        public EditProjectDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code projectTags} is used internally.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setProjectName(toCopy.projectName);
            setDeadline(toCopy.deadline);
            setRepoUrl(toCopy.repoUrl);
            setProjectDescription(toCopy.projectDescription);
            setTags(toCopy.projectTags);
            setTasks(toCopy.tasks);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(projectName, deadline, repoUrl, projectDescription, projectTags, tasks);
        }

        public void setProjectName(ProjectName projectName) {
            this.projectName = projectName;
        }

        public Optional<ProjectName> getProjectName() {
            return Optional.ofNullable(projectName);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setRepoUrl(RepoUrl repoUrl) {
            this.repoUrl = repoUrl;
        }

        public Optional<RepoUrl> getRepoUrl() {
            return Optional.ofNullable(repoUrl);
        }

        public void setProjectDescription(ProjectDescription projectDescription) {
            this.projectDescription = projectDescription;
        }

        public Optional<ProjectDescription> getProjectDescription() {
            return Optional.ofNullable(projectDescription);
        }

        /**
         * Sets {@code projectTags} to this object's {@code projectTags}.
         * A defensive copy of {@code projectTags} is used internally.
         */
        public void setTags(Set<ProjectTag> projectTags) {
            this.projectTags = (projectTags != null) ? new HashSet<>(projectTags) : null;
        }

        /**
         * Returns an unmodifiable project tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code projectTags} is null.
         */
        public Optional<Set<ProjectTag>> getProjectTags() {
            return (projectTags != null) ? Optional.of(Collections.unmodifiableSet(projectTags)) : Optional.empty();
        }

        /**
         * Sets {@code tasks} to this object's {@code tasks}.
         * A defensive copy of {@code tasks} is used internally.
         */
        public void setTasks(Set<Task> tasks) {
            this.tasks = (tasks != null) ? new HashSet<>(tasks) : null;
        }

        /**
         * Returns an unmodifiable task set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tasks} is null.
         */
        public Optional<Set<Task>> getTasks() {
            return (tasks != null) ? Optional.of(Collections.unmodifiableSet(tasks)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProjectDescriptor)) {
                return false;
            }

            // state check
            EditProjectDescriptor e = (EditProjectDescriptor) other;

            return getProjectName().equals(e.getProjectName())
                    && getDeadline().equals(e.getDeadline())
                    && getRepoUrl().equals(e.getRepoUrl())
                    && getProjectDescription().equals(e.getProjectDescription())
                    && getProjectTags().equals(e.getProjectTags())
                    && getTasks().equals(e.getTasks());
        }
    }
}
