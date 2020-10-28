package seedu.resireg.model.alias;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility functions for classes in the model.alias package.
 */
public class AliasUtils {
    /**
     * Converts a list of {@code CommandWordAlias} to a map of alias to command word.
     */
    public static Map<String, String> makeAliasToCommandWordMap(List<CommandWordAlias> aliasList) {
        return aliasList.stream().collect(Collectors.toMap(
            commandWordAlias -> commandWordAlias.getAlias().toString(),
            commandWordAlias -> commandWordAlias.getCommandWord().toString()
        ));
    }
}
