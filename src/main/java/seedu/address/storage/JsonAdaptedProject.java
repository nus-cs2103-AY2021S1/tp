package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String deadline;
    private final String repoUrl;
    private final String projectDescription;
    private final List<JsonAdaptedTag> projectTagged = new ArrayList<>();
    private final List<JsonAdaptedTask> projectOccupied = new ArrayList<>();
    //    private final List<JsonParticipation> participations = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName,
                              @JsonProperty("deadline") String deadline,
                              @JsonProperty("repoUrl") String repoUrl,
                              @JsonProperty("projectDescription") String projectDescription,
                              @JsonProperty("projectTag") List<JsonAdaptedTag> projectTagged,
                              @JsonProperty("occupied") List<JsonAdaptedTask> projectOccupied
    //                              @JsonProperty("participations") List<JsonParticipation> participations
    ) {
        this.projectName = projectName;
        this.deadline = deadline;
        this.repoUrl = repoUrl;
        this.projectDescription = projectDescription;
        if (projectTagged != null) {
            this.projectTagged.addAll(projectTagged);
        }
        if (projectOccupied != null) {
            this.projectOccupied.addAll(projectOccupied);
        }
        //        if (participations != null) {
        //            this.participations.addAll(participations);
        //        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().fullProjectName;
        deadline = source.getDeadline().toString();
        repoUrl = source.getRepoUrl().value;
        projectDescription = source.getProjectDescription().value;
        projectTagged.addAll(source.getProjectTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        projectOccupied.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        //        participations.addAll(source.getParticipationList().stream()
        //                .map(JsonParticipation::new)
        //                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {

        final List<ProjectTag> projectProjectTags = new ArrayList<>();
        for (JsonAdaptedTag projectTag : projectTagged) {
            projectProjectTags.add(projectTag.toModelType());
        }
        final List<Task> projectTasks = new ArrayList<>();

        if (projectName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidProjectName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelProjectName = new ProjectName(projectName);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (repoUrl == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, RepoUrl.class.getSimpleName()));
        }
        if (!RepoUrl.isValidRepoUrl(repoUrl)) {
            throw new IllegalValueException(RepoUrl.MESSAGE_CONSTRAINTS);
        }
        final RepoUrl modelRepoUrl = new RepoUrl(repoUrl);

        if (projectDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectDescription.class.getSimpleName()));
        }
        if (!ProjectDescription.isValidProjectDescription(projectDescription)) {
            throw new IllegalValueException(ProjectDescription.MESSAGE_CONSTRAINTS);
        }
        final ProjectDescription modelProjectDescription = new ProjectDescription(projectDescription);

        final Set<ProjectTag> modelProjectTags = new HashSet<>(projectProjectTags);
        final Set<Task> modelTasks = new HashSet<>(projectTasks);
        Project p = new Project(modelProjectName, modelDeadline, modelRepoUrl, modelProjectDescription,
                modelProjectTags, null, modelTasks);

        //        for (JsonParticipation participation : participations) {
        //
        //            Participation part = participation.toModelType();
        //
        //            Person person = part.getPerson();
        //            p.addExistingParticipation(part);
        //            person.addProject(p);
        //
        //        }
        return p;
    }

}
