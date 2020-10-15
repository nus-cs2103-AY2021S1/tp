package tp.cap5buddy.logic.parser;

import java.util.ArrayList;
import java.util.List;

import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the token of each user input.
 */
public class Tokenizer {

    private final String userInput;
    private final Prefix[] prefixes;

    /**
     * Constructs the tokenising object.
     *
     * @param userInput User input.
     * @param prefixes Array of prefixes.
     */
    public Tokenizer(String userInput, Prefix... prefixes) {
        this.userInput = userInput;
        this.prefixes = prefixes;
    }

    /**
     * Tokenizes a user input based on prefixes.
     *
     * @return String array containing command arguments.
     * @throws ParseException If the user input could not be parsed.
     */
    public ArgumentMap tokenize() throws ParseException {
        List<PrefixPosition> prefixPositions = findAllPrefixPositions();
        return extractArguments(this.userInput, prefixPositions);
    }

    /**
     *
     *
     * @return List of prefix positions
     * @throws ParseException If the input could not be parsed.
     */
    private List<PrefixPosition> findAllPrefixPositions() throws ParseException {
        List<PrefixPosition> prefixPositions = new ArrayList<>();
        for (Prefix prefix : this.prefixes) {
            PrefixPosition prefixPosition = findPrefixPosition(this.userInput, prefix);
            if (prefixPosition != null) {
                prefixPositions.add(prefixPosition);
            }
        }
        return prefixPositions;
    }

    /**
     * Finds the position of the prefix in the user input.
     *
     * @param userInput User input.
     * @param prefix Prefix.
     * @return Prefix position.
     * @throws ParseException If the same prefix is used more than once by the user.
     */
    private PrefixPosition findPrefixPosition(String userInput, Prefix prefix) throws ParseException {
        String prefixSearch = " " + prefix.getPrefix();
        int prefixIndex = userInput.indexOf(prefixSearch);

        boolean hasMultipleSamePrefixes = hasMultipleSamePrefixes(userInput, prefix, prefixIndex);
        if (hasMultipleSamePrefixes) {
            String error = "User input has multiple arguments for the same prefix";
            throw new ParseException(error);
        }
        return (prefixIndex == -1 ? null : new PrefixPosition(prefixIndex + 1, prefix));
    }

    /**
     * Determines if the same prefix is used more than once.
     *
     * @param userInput User input.
     * @param prefix Prefix.
     * @param currentPrefixIndex Index of the current prefix in the user input.
     * @return boolean.
     */
    private boolean hasMultipleSamePrefixes(String userInput, Prefix prefix, int currentPrefixIndex) {
        int nextPrefixIndex = userInput.indexOf(" " + prefix.getPrefix(), currentPrefixIndex + 1);
        boolean hasMultipleSamePrefix = nextPrefixIndex != -1;
        return hasMultipleSamePrefix;
    }

    /**
     * Extracts the command arguments using the list of prefix positions.
     *
     * @param userInput User input.
     * @param prefixPositions Prefix positions.
     * @return String array containing the command arguments.
     */
    private ArgumentMap extractArguments(String userInput, List<PrefixPosition> prefixPositions) {

        // Sort by start position
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getPrefixIndex() - prefix2.getPrefixIndex());

        // Insert a PrefixPosition to represent the preamble of the user input
        PrefixPosition preambleMarker = new PrefixPosition(0, new Prefix(""));
        prefixPositions.add(0, preambleMarker);

        PrefixPosition endPositionMarker = new PrefixPosition(userInput.length(), new Prefix(""));
        prefixPositions.add(endPositionMarker);

        // Map prefixes to their argument values (if any)
        ArgumentMap argumentMap = new ArgumentMap();

        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix prefix = prefixPositions.get(i).getPrefix();
            String prefixArgument = extractArgument(userInput, prefixPositions.get(i), prefixPositions.get(i + 1));
            argumentMap.put(prefix, prefixArgument);
        }
        return argumentMap;
    }

    /**
     * Extracts the argument of a prefix.
     *
     * @param userInput User input.
     * @param currentPrefixPosition Current index of the prefix.
     * @param nextPrefixPosition Next prefix index.
     * @return String containing the command argument for the current prefix.
     */
    private String extractArgument(String userInput,
                                  PrefixPosition currentPrefixPosition,
                                  PrefixPosition nextPrefixPosition) {

        Prefix prefix = currentPrefixPosition.getPrefix();
        int argumentIndex = currentPrefixPosition.getPrefixIndex() + prefix.getPrefix().length();
        String argument = userInput.substring(argumentIndex, nextPrefixPosition.getPrefixIndex());
        return argument.trim();
    }

}
