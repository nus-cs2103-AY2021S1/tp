package seedu.expense.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;

public class JsonSerializableAliasMap {
    public static final String MESSAGE_DUPLICATE_ALIAS = "Alias list contains duplicate alias(es).";
    private final List<JsonAdaptedAliasEntry> aliases = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAliasMap} with the given alias entries.
     */
    public JsonSerializableAliasMap(@JsonProperty("aliases") List<JsonAdaptedAliasEntry> entries) {
        this.aliases.addAll(entries);
    }

    /**
     * Converts a given {@code AliasMap} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAliasMap}.
     */
    public JsonSerializableAliasMap(AliasMap source) {
        aliases.addAll(source.getAliasList().stream().map(JsonAdaptedAliasEntry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this alias map into the model's {@code AliasMap} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AliasMap toModelType() throws IllegalValueException {
        AliasMap aliasMap = new AliasMap();
        for (JsonAdaptedAliasEntry jsonAdaptedAliasEntry : aliases) {
            AliasEntry alias = jsonAdaptedAliasEntry.toModelType();
            if (aliasMap.hasAlias(alias)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALIAS);
            }
            aliasMap.addAlias(alias);
        }
        return aliasMap;
    }
}
