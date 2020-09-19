package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.project.Project;

/**
 * An Immutable MainCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "maincatalogue")
class JsonSerializableMainCatalogue {

    public static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";

    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMainCatalogue} with the given projects.
     */
    @JsonCreator
    public JsonSerializableMainCatalogue(@JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.projects.addAll(projects);
    }

    /**
     * Converts a given {@code ReadOnlyMainCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMainCatalogue}.
     */
    public JsonSerializableMainCatalogue(ReadOnlyMainCatalogue source) {
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
    }

    /**
     * Converts this main catalogue into the model's {@code MainCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MainCatalogue toModelType() throws IllegalValueException {
        MainCatalogue mainCatalogue = new MainCatalogue();
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (mainCatalogue.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            mainCatalogue.addProject(project);
        }
        return mainCatalogue;
    }

}
