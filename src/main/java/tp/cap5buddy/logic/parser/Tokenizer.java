package tp.cap5buddy.logic.parser;

import java.util.ArrayList;
import java.util.List;

import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the token of each user input.
 */
public class Tokenizer {
    private static final int SIZE = 6; // updates as the number of prefixes increases.
    private String[] words = new String[SIZE];
    private String input;

    /**
     * Constructs the tokenising object.
     *
     * @param input
     */
    public Tokenizer(String input) {
        this.input = input;
        breakDown();
    }

    public Tokenizer() {

    }

    /**
     * Tokenizes a user input based on prefixes.
     *
     * @param userInput User input
     * @param prefixes Array of prefixes
     * @return String array containing command arguments
     * @throws ParseException If the user input could not be parsed.
     */
    public String[] tokenize(String userInput, Prefix... prefixes) throws ParseException {
        List<PrefixPosition> prefixPositions = findAllPrefixPositions(userInput, prefixes);
        return extractArguments(userInput, prefixPositions);
    }

    /**
     *
     *
     * @param userInput User input
     * @param prefixes Array of prefixes
     * @return List of prefix positions
     * @throws ParseException If the input could not be parsed.
     */
    public List<PrefixPosition> findAllPrefixPositions(String userInput, Prefix... prefixes) throws ParseException {
        List<PrefixPosition> prefixPositions = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            PrefixPosition prefixPosition = findPrefixPosition(userInput, prefix);
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
    public PrefixPosition findPrefixPosition(String userInput, Prefix prefix) throws ParseException {
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
    public boolean hasMultipleSamePrefixes(String userInput, Prefix prefix, int currentPrefixIndex) {
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
    public String[] extractArguments(String userInput, List<PrefixPosition> prefixPositions) {
        String[] results = new String[SIZE];
        PrefixPosition endPositionMarker = new PrefixPosition(userInput.length(), new Prefix(""));
        prefixPositions.add(endPositionMarker);
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            String argument = extractArgument(userInput, prefixPositions.get(i), prefixPositions.get(i + 1));
            results[i] = argument;
        }
        return results;
    }

    /**
     * Extracts the argument of a prefix.
     *
     * @param userInput User input.
     * @param currentPrefixPosition Current index of the prefix.
     * @param nextPrefixPosition Next prefix index.
     * @return String containing the command argument for the current prefix.
     */
    public String extractArgument(String userInput,
                                  PrefixPosition currentPrefixPosition,
                                  PrefixPosition nextPrefixPosition) {

        Prefix prefix = currentPrefixPosition.getPrefix();
        int argumentIndex = currentPrefixPosition.getPrefixIndex() + prefix.getPrefix().length();
        String argument = userInput.substring(argumentIndex, nextPrefixPosition.getPrefixIndex());
        return argument.trim();
    }

    private void breakDown() {
        String[] temp = this.input.split(" ");
        for (int i = 0; i < temp.length; i++) {
            String word = temp[i];
            String prefix = word.substring(0, 2); // to extract the prefix
            if (Prefix.isPrefix(word)) {
                word = word.substring(2); // to remove the prefix from the actual word
            }
            if (prefix.equals(PrefixList.MODULE_NAME_PREFIX.toString())) {
                this.words[0] = word;
            } else if (prefix.equals(PrefixList.MODULE_LINK_PREFIX.toString())) {
                this.words[1] = word;
            } else if (prefix.equals(PrefixList.MODULE_NEWNAME_PREFIX.toString())) {
                this.words[2] = word;
            } else if (prefix.equals(PrefixList.MODULE_VIEW_PREFIX.toString())) {
                this.words[3] = word;
            } else if (prefix.equals(PrefixList.MODULE_DELETE_PREFIX.toString())) {
                this.words[4] = word;
            } else if (prefix.equals(PrefixList.MODULE_INDEX_PREFIX.toString())) {
                this.words[4] = word;
            } else {
                // throws error as invalid prefix is found
            }
        }
    }

    public String[] getWords() {
        return this.words;
    }

    private class PrefixPosition {
        private int prefixIndex;
        private final Prefix prefix;

        public PrefixPosition(int startPosition, Prefix prefix) {
            this.prefixIndex = startPosition;
            this.prefix = prefix;
        }

        public int getPrefixIndex() {
            return this.prefixIndex;
        }

        public Prefix getPrefix() {
            return this.prefix;
        }
    }
}
