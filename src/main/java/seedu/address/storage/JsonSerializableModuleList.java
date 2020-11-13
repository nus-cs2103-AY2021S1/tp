package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Trackr;
import seedu.address.model.module.Module;

@JsonRootName(value = "modulelist")
public class JsonSerializableModuleList {
    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleList(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyModuleList} into this class for Jackson use.
     *
     * @param source The module list that gets converted, future changes to this will not affect
     * the created {@code JsonSerializableModuleList}.
     */
    public JsonSerializableModuleList(ReadOnlyTrackr<Module> source) {
        assert source != null;
        modules.addAll(source.getList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module list into the model's {@code ModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Trackr toModelType() throws IllegalValueException {
        Trackr moduleList = new Trackr();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (moduleList.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            moduleList.addModule(module);
        }
        return moduleList;
    }


}
