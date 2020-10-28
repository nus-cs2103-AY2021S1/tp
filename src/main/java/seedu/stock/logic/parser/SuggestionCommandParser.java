package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.stock.logic.commands.CommandWords.ADD_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.BOOKMARK_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.DELETE_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.FIND_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.FIND_EXACT_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.NOTE_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.NOTE_DELETE_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.NOTE_VIEW_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.PRINT_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.SORT_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.STATISTICS_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.UNBOOKMARK_COMMAND_WORD;
import static seedu.stock.logic.commands.CommandWords.UPDATE_COMMAND_WORD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LIST_TYPE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_STATISTICS_TYPE;

import java.util.List;
import java.util.Random;

import seedu.stock.commons.util.SortUtil.Field;
import seedu.stock.commons.util.SortUtil.Order;
import seedu.stock.commons.util.SuggestionUtil;
import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.BookmarkCommand;
import seedu.stock.logic.commands.CommandWords;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.commands.ExitCommand;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.FindExactCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.commands.ListCommand;
import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.logic.commands.NoteViewCommand;
import seedu.stock.logic.commands.PrintCommand;
import seedu.stock.logic.commands.SortCommand;
import seedu.stock.logic.commands.StatisticsCommand;
import seedu.stock.logic.commands.SuggestionCommand;
import seedu.stock.logic.commands.UnbookmarkCommand;
import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class SuggestionCommandParser implements Parser<SuggestionCommand> {

    public static final String MESSAGE_SUGGESTION = "Do you mean: \n";
    private String faultyCommandWord;
    private String commandWord;
    private String headerErrorMessage;
    private String bodyErrorMessage;

    /**
     * Constructs a new suggestion command parser.
     *
     * @param faultyCommandWord The command word to be corrected.
     */
    public SuggestionCommandParser(String faultyCommandWord) {
        this.faultyCommandWord = faultyCommandWord;
        commandWord = "";
        this.headerErrorMessage = "";
        this.bodyErrorMessage = "";
    }

    /**
     * Constructs a new suggestion command parser with error message from another parser.
     *
     * @param commandWord The command word to be executed if all went well.
     * @param errorMessage The parse error thrown from another parser.
     */
    public SuggestionCommandParser(String commandWord, String errorMessage) {
        String[] splitHeaderAndBody = errorMessage.split("\n", 2);
        this.commandWord = commandWord;
        faultyCommandWord = "";
        headerErrorMessage = splitHeaderAndBody[0];
        bodyErrorMessage = splitHeaderAndBody.length < 2 ? "" : splitHeaderAndBody[1];
    }

    /**
     * Parses {@code args} into a suggestion command.
     *
     * @param args The user input to be parsed.
     * @return A new suggestion command.
     * @throws ParseException If any parsing errors occurs.
     */
    @Override
    public SuggestionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_SERIAL_NUMBER, PREFIX_INCREMENT_QUANTITY, PREFIX_NEW_QUANTITY,
                        PREFIX_NAME, PREFIX_SOURCE, PREFIX_LOCATION, PREFIX_QUANTITY, PREFIX_FILE_NAME,
                        PREFIX_SORT_ORDER, PREFIX_SORT_FIELD, PREFIX_NOTE, PREFIX_NOTE_INDEX,
                        PREFIX_STATISTICS_TYPE, PREFIX_LIST_TYPE, PREFIX_LOW_QUANTITY
                );
        List<String> allCommandWords = CommandWords.getAllCommandWords();
        StringBuilder toBeDisplayed = new StringBuilder();

        String suggestedCommandWord = faultyCommandWord;
        int bestEditDistanceSoFar = Integer.MAX_VALUE;
        for (String commandWord: allCommandWords) {
            int currentEditDistance = SuggestionUtil.minimumEditDistance(faultyCommandWord, commandWord);
            if (currentEditDistance < bestEditDistanceSoFar) {
                bestEditDistanceSoFar = currentEditDistance;
                suggestedCommandWord = commandWord;
            }
        }

        if (suggestedCommandWord.equals(faultyCommandWord) && commandWord.equals("")) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        if (!commandWord.equals("")) {
            suggestedCommandWord = commandWord;
        }

        if (headerErrorMessage.equals("")) {
            toBeDisplayed.append(MESSAGE_UNKNOWN_COMMAND + "\n" + MESSAGE_SUGGESTION);
        } else {
            toBeDisplayed.append(headerErrorMessage + "\n" + MESSAGE_SUGGESTION);
        }

        switch (suggestedCommandWord) {
        case ExitCommand.COMMAND_WORD:
            generateExitSuggestion(toBeDisplayed, argMultimap);
            break;

        case HelpCommand.COMMAND_WORD:
            generateHelpSuggestion(toBeDisplayed, argMultimap);
            break;

        case ListCommand.COMMAND_WORD:
            generateListSuggestion(toBeDisplayed, argMultimap);
            break;

        case AddCommand.COMMAND_WORD:
            generateAddSuggestion(toBeDisplayed, argMultimap);
            break;

        case DeleteCommand.COMMAND_WORD:
            generateDeleteSuggestion(toBeDisplayed, argMultimap);
            break;

        case StatisticsCommand.COMMAND_WORD:
            generateStatisticsSuggestion(toBeDisplayed, argMultimap);
            break;

        case FindCommand.COMMAND_WORD:
            generateFindSuggestion(toBeDisplayed, argMultimap);
            break;

        case UpdateCommand.COMMAND_WORD:
            generateUpdateSuggestion(toBeDisplayed, argMultimap);
            break;

        case FindExactCommand.COMMAND_WORD:
            generateFindExactSuggestion(toBeDisplayed, argMultimap);
            break;

        case SortCommand.COMMAND_WORD:
            generateSortSuggestion(toBeDisplayed, argMultimap);
            break;

        case NoteCommand.COMMAND_WORD:
            generateNoteSuggestion(toBeDisplayed, argMultimap);
            break;

        case NoteDeleteCommand.COMMAND_WORD:
            generateDeleteNoteSuggestion(toBeDisplayed, argMultimap);
            break;

        case NoteViewCommand.COMMAND_WORD:
            generateNoteViewSuggestion(toBeDisplayed, argMultimap);
            break;

        case PrintCommand.COMMAND_WORD:
            generatePrintSuggestion(toBeDisplayed, argMultimap);
            break;

        case BookmarkCommand.COMMAND_WORD:
            generateBookmarkSuggestion(toBeDisplayed, argMultimap);
            break;

        case UnbookmarkCommand.COMMAND_WORD:
            generateUnbookmarkSuggestion(toBeDisplayed, argMultimap);
            break;

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        return new SuggestionCommand(toBeDisplayed.toString());
    }

    /**
     * Generates suggestion for faulty sort command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateSortSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(SORT_COMMAND_WORD);
        Prefix fieldPrefix = PREFIX_SORT_FIELD;
        Prefix orderPrefix = PREFIX_SORT_ORDER;

        if (!argMultimap.getValue(orderPrefix).isPresent()) {
            toBeDisplayed.append(" " + orderPrefix + CliSyntax.getDefaultDescription(orderPrefix));
        } else {
            String description = argMultimap.getValue(orderPrefix).get();
            String suggestedDescription = description;
            int bestEditDistanceSoFar = Integer.MAX_VALUE;
            for (Order order : Order.values()) {
                String orderDescription = order.toString().toLowerCase();
                int currentEditDistance = SuggestionUtil.minimumEditDistance(description, orderDescription);
                if (currentEditDistance < bestEditDistanceSoFar) {
                    bestEditDistanceSoFar = currentEditDistance;
                    suggestedDescription = orderDescription;
                }
            }
            toBeDisplayed.append(" " + orderPrefix + suggestedDescription);
        }

        if (!argMultimap.getValue(fieldPrefix).isPresent()) {
            toBeDisplayed.append(" " + fieldPrefix + CliSyntax.getDefaultDescription(fieldPrefix));
        } else {
            String description = argMultimap.getValue(fieldPrefix).get();
            String suggestedDescription = description;
            int bestEditDistanceSoFar = Integer.MAX_VALUE;
            for (Field field : Field.values()) {
                String fieldDescription = field.toString().toLowerCase();
                int currentEditDistance = SuggestionUtil.minimumEditDistance(description, fieldDescription);
                if (currentEditDistance < bestEditDistanceSoFar) {
                    bestEditDistanceSoFar = currentEditDistance;
                    suggestedDescription = fieldDescription;
                }
            }
            toBeDisplayed.append(" " + fieldPrefix + suggestedDescription);
        }

        generateBodyMessage(toBeDisplayed, SortCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty bookmark command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateBookmarkSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(BOOKMARK_COMMAND_WORD);

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        generateBodyMessage(toBeDisplayed, BookmarkCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty unbookmark command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateUnbookmarkSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(UNBOOKMARK_COMMAND_WORD);

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        generateBodyMessage(toBeDisplayed, UnbookmarkCommand.MESSAGE_USAGE);
    }



    /**
     * Generates suggestion for faulty find exact command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateFindExactSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_NAME, PREFIX_SOURCE,
                PREFIX_SERIAL_NUMBER, PREFIX_LOCATION);
        toBeDisplayed.append(FIND_EXACT_COMMAND_WORD);

        for (int i = 0; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            if (argMultimap.getValue(currentPrefix).isPresent()) {
                toBeDisplayed.append(" " + currentPrefix + argMultimap.getValue(currentPrefix).get());
            }
        }

        generateBodyMessage(toBeDisplayed, FindExactCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty update command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateUpdateSuggestion(StringBuilder toBeDisplayed,
            ArgumentMultimap argMultimap) throws ParseException {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_SERIAL_NUMBER,
                PREFIX_INCREMENT_QUANTITY, PREFIX_NEW_QUANTITY, PREFIX_NAME, PREFIX_SOURCE, PREFIX_LOCATION);
        toBeDisplayed.append(UPDATE_COMMAND_WORD);
        boolean isIncrementQuantityPresent = argMultimap.getValue(PREFIX_INCREMENT_QUANTITY).isPresent();
        boolean isNewQuantityPresent = argMultimap.getValue(PREFIX_NEW_QUANTITY).isPresent();

        if (isIncrementQuantityPresent == isNewQuantityPresent) {
            // both present or both not present
            int removeRng = new Random().nextInt(2);
            if (removeRng == 0) {
                allowedPrefixes.remove(PREFIX_INCREMENT_QUANTITY);
            } else {
                allowedPrefixes.remove(PREFIX_NEW_QUANTITY);
            }
        }

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        for (int i = 1; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            boolean isPresent = argMultimap.getValue(currentPrefix).isPresent();
            String description = "";
            if (isPresent) {
                description = argMultimap.getValue(currentPrefix).get();
            }
            boolean isEmpty = description.equals("");
            if (!isEmpty) {
                toBeDisplayed.append(" " + currentPrefix + description);
            } else if (isEmpty && isPresent) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            }
        }

        generateBodyMessage(toBeDisplayed, UpdateCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty find command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateFindSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_NAME, PREFIX_SOURCE,
                PREFIX_SERIAL_NUMBER, PREFIX_LOCATION);
        toBeDisplayed.append(FIND_COMMAND_WORD);

        for (int i = 0; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            if (argMultimap.getValue(currentPrefix).isPresent()) {
                toBeDisplayed.append(" " + currentPrefix + argMultimap.getValue(currentPrefix).get());
            }
        }

        generateBodyMessage(toBeDisplayed, FindCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty note command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateNoteSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(
                PREFIX_SERIAL_NUMBER, PREFIX_NOTE);
        toBeDisplayed.append(NOTE_COMMAND_WORD);

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        for (int i = 1; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            String description = "";
            if (argMultimap.getValue(currentPrefix).isPresent()) {
                description = argMultimap.getValue(currentPrefix).get();
            }
            boolean isEmpty = description.equals("");
            if (isEmpty) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            } else {
                toBeDisplayed.append(" " + currentPrefix + description);
            }
        }

        generateBodyMessage(toBeDisplayed, NoteCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty deletenote command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateDeleteNoteSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(
                PREFIX_SERIAL_NUMBER, PREFIX_NOTE_INDEX);
        toBeDisplayed.append(NOTE_DELETE_COMMAND_WORD);

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        for (int i = 1; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            String description = "";
            boolean isValidInteger = true;
            if (argMultimap.getValue(currentPrefix).isPresent()) {
                description = argMultimap.getValue(currentPrefix).get();
            }
            try {
                Integer.parseInt(description);
            } catch (NumberFormatException ex) {
                isValidInteger = false;
            }
            boolean isEmpty = description.equals("");
            if (isEmpty || !isValidInteger) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            } else {
                toBeDisplayed.append(" " + currentPrefix + description);
            }
        }

        generateBodyMessage(toBeDisplayed, NoteDeleteCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty noteview command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateNoteViewSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(NOTE_VIEW_COMMAND_WORD);

        if (!argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent()) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + CliSyntax.getDefaultDescription(PREFIX_SERIAL_NUMBER));
        }
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        for (String serialNumber : keywords) {
            toBeDisplayed.append(" " + PREFIX_SERIAL_NUMBER + serialNumber);
        }

        generateBodyMessage(toBeDisplayed, NoteViewCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty delete command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateDeleteSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_SERIAL_NUMBER);
        toBeDisplayed.append(DELETE_COMMAND_WORD);

        for (int i = 0; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            if (!argMultimap.getValue(currentPrefix).isPresent()) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            }
            List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
            for (String serialNumber : keywords) {
                boolean isEmpty = serialNumber.equals("");
                String description = isEmpty ? CliSyntax.getDefaultDescription(currentPrefix) : serialNumber;
                toBeDisplayed.append(" " + currentPrefix + description);
            }
        }

        generateBodyMessage(toBeDisplayed, DeleteCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty statistics command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateStatisticsSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(STATISTICS_COMMAND_WORD);

        Prefix typePrefix = PREFIX_STATISTICS_TYPE;
        String[] typeDescriptions = new String[]{"source", "source-qd-"};

        if (!argMultimap.getValue(typePrefix).isPresent()) {
            toBeDisplayed.append(" " + typePrefix + CliSyntax.getDefaultDescription(typePrefix));
        } else {
            String description = argMultimap.getValue(typePrefix).get();
            String suggestedDescription = description;
            int bestEditDistanceSoFar = Integer.MAX_VALUE;
            for (String typeDescription : typeDescriptions) {
                int currentEditDistance = SuggestionUtil.minimumEditDistance(description, typeDescription);
                if (currentEditDistance < bestEditDistanceSoFar) {
                    bestEditDistanceSoFar = currentEditDistance;
                    suggestedDescription = typeDescription;
                }
            }
            if (suggestedDescription.equals(typeDescriptions[0])) {
                toBeDisplayed.append(" " + typePrefix + suggestedDescription);
            } else {
                toBeDisplayed.append(" " + typePrefix + suggestedDescription + "<source-company>");
            }
        }

        generateBodyMessage(toBeDisplayed, StatisticsCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty add command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateAddSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_NAME, PREFIX_SOURCE,
                 PREFIX_QUANTITY, PREFIX_LOCATION, PREFIX_LOW_QUANTITY);
        toBeDisplayed.append(ADD_COMMAND_WORD);

        for (int i = 0; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            String description = "";
            if (argMultimap.getValue(currentPrefix).isPresent()) {
                description = argMultimap.getValue(currentPrefix).get();
            }
            boolean isEmpty = description.equals("");
            if (isEmpty) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            } else {
                toBeDisplayed.append(" " + currentPrefix + description);
            }
        }

        generateBodyMessage(toBeDisplayed, AddCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty list command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateListSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(CommandWords.LIST_COMMAND_WORD);

        Prefix typePrefix = PREFIX_LIST_TYPE;
        String[] typeDescriptions = new String[]{"all", "bookmark", "low"};

        if (!argMultimap.getValue(typePrefix).isPresent()) {
            toBeDisplayed.append(" " + typePrefix + CliSyntax.getDefaultDescription(typePrefix));
        } else {
            String description = argMultimap.getValue(typePrefix).get();
            String suggestedDescription = description;
            int bestEditDistanceSoFar = Integer.MAX_VALUE;
            for (String typeDescription : typeDescriptions) {
                int currentEditDistance = SuggestionUtil.minimumEditDistance(description, typeDescription);
                if (currentEditDistance < bestEditDistanceSoFar) {
                    bestEditDistanceSoFar = currentEditDistance;
                    suggestedDescription = typeDescription;
                }
            }
            toBeDisplayed.append(" " + typePrefix + suggestedDescription);
        }

        generateBodyMessage(toBeDisplayed, ListCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty help command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateHelpSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(CommandWords.HELP_COMMAND_WORD);

        generateBodyMessage(toBeDisplayed, HelpCommand.MESSAGE_USAGE);
    }

    /**
     * Generates suggestion for faulty exit command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generateExitSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        toBeDisplayed.append(CommandWords.EXIT_COMMAND_WORD);
    }

    /**
     * Generates suggestion for faulty print command.
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param argMultimap The parsed user input fields.
     */
    private void generatePrintSuggestion(StringBuilder toBeDisplayed, ArgumentMultimap argMultimap) {
        List<Prefix> allowedPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_FILE_NAME);
        toBeDisplayed.append(PRINT_COMMAND_WORD);

        for (int i = 0; i < allowedPrefixes.size(); i++) {
            Prefix currentPrefix = allowedPrefixes.get(i);
            if (!argMultimap.getValue(currentPrefix).isPresent()) {
                toBeDisplayed.append(" " + currentPrefix + CliSyntax.getDefaultDescription(currentPrefix));
            }
            List<String> keywords = argMultimap.getAllValues(PREFIX_FILE_NAME);

            if (keywords.size() == 0) {
                continue;
            }

            toBeDisplayed.append(" " + currentPrefix + keywords.get(0));
        }

        generateBodyMessage(toBeDisplayed, PrintCommand.MESSAGE_USAGE);
    }

    /**
     * Generates the suggestion body message to be displayed to the user
     *
     * @param toBeDisplayed The accumulated suggestion to be displayed to the user.
     * @param messageUsage The body message.
     */
    private void generateBodyMessage(StringBuilder toBeDisplayed, String messageUsage) {
        if (!bodyErrorMessage.equals("")) {
            toBeDisplayed.append("\n" + bodyErrorMessage);
        } else {
            toBeDisplayed.append("\n" + messageUsage);
        }
    }
}
