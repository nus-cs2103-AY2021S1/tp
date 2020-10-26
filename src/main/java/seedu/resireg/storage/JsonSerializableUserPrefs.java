package seedu.resireg.storage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * An Immutable ResiReg that is serializable to JSON format.
 */
@JsonRootName(value = "preferences")
class JsonSerializableUserPrefs {

    public static final String MESSAGE_DUPLICATE_ALIAS = "Alias list contains duplicate alias(es).";

    private final List<JsonAdaptedCommandWordAlias> commandWordAliases = new ArrayList<>();
    private final GuiSettings guiSettings;
    private final Path resiRegFilePath;
    private final int daysStoredInBin;

    /**
     * Constructs a {@code JsonSerializableResiReg} with the given students.
     */
    @JsonCreator
    public JsonSerializableUserPrefs(@JsonProperty("guiSettings") GuiSettings guiSettings,
                                     @JsonProperty("commandWordAliases") List<JsonAdaptedCommandWordAlias> aliases,
                                     @JsonProperty("addressBookFilePath") Path resiRegFilePath,
                                     @JsonProperty("daysStoredInBin") int daysStoredInBin) {
        this.commandWordAliases.addAll(aliases);
        this.guiSettings = guiSettings;
        this.resiRegFilePath = resiRegFilePath;
        this.daysStoredInBin = daysStoredInBin;
    }

    /**
     * Converts a given {@code ReadOnlyResiReg} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResiReg}.
     */
    public JsonSerializableUserPrefs(ReadOnlyUserPrefs source) {
        commandWordAliases.addAll(source.getCommandWordAliases().stream().map(JsonAdaptedCommandWordAlias::new)
            .collect(Collectors.toList()));
        this.guiSettings = source.getGuiSettings();
        this.resiRegFilePath = source.getResiRegFilePath();
        this.daysStoredInBin = source.getDaysStoredInBin();
    }

    /**
     * Converts this address book into the model's {@code ResiReg} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UserPrefs toModelType() throws IllegalValueException {
        UserPrefs userPrefs = new UserPrefs();
        for (JsonAdaptedCommandWordAlias jsonAdaptedCommandWordAlias : commandWordAliases) {
            CommandWordAlias commandWordAlias = jsonAdaptedCommandWordAlias.toModelType();
            if (userPrefs.hasAlias(commandWordAlias)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALIAS);
            }
            userPrefs.addAlias(commandWordAlias);
        }

        userPrefs.setGuiSettings(guiSettings);
        userPrefs.setResiRegFilePath(resiRegFilePath);
        userPrefs.setDaysStoredInBin(daysStoredInBin);
        return userPrefs;
    }

}
