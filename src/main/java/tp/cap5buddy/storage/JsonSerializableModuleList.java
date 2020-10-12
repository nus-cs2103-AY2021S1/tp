package tp.cap5buddy.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;


/**
 * An Immutable ModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableModuleList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleList(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ModuleList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModuleList}.
     */
    public JsonSerializableModuleList(ModuleList source) {
        modules.addAll(source.getModules().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module list into the model's {@code ModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleList toModelType() throws IllegalValueException {
        ModuleList moduleList = new ModuleList();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            moduleList.addModule(module);
        }
        return moduleList;
    }

}
