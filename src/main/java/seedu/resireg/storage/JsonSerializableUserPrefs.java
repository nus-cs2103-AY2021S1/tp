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
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "preferences")
class JsonSerializableUserPrefs {

    public static final String MESSAGE_DUPLICATE_ALIAS = "Alias list contains duplicate alias(es).";

    private final List<JsonAdaptedCommandWordAlias> commandWordAliases = new ArrayList<>();
    private final GuiSettings guiSettings;
    private final Path addressBookFilePath;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableUserPrefs(@JsonProperty("guiSettings") GuiSettings guiSettings,
                                     @JsonProperty("commandWordAliases") List<JsonAdaptedCommandWordAlias> aliases,
                                     @JsonProperty("addressBookFilePath") Path addressBookFilePath) {
        this.commandWordAliases.addAll(aliases);
        this.guiSettings = guiSettings;
        this.addressBookFilePath = addressBookFilePath;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableUserPrefs(ReadOnlyUserPrefs source) {
        commandWordAliases.addAll(source.getCommandWordAliases().stream().map(JsonAdaptedCommandWordAlias::new)
            .collect(Collectors.toList()));
        this.guiSettings = source.getGuiSettings();
        this.addressBookFilePath = source.getAddressBookFilePath();
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
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
        userPrefs.setAddressBookFilePath(addressBookFilePath);

        return userPrefs;
    }

}
