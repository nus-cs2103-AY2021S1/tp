package seedu.address.logic.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Show the statistic and brief summary of the user's expenses.
 */
public class ShowStatisticCommand extends Command {

    public static final String COMMAND_WORD = "show-stats";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Show number of expenses by categories in ascending order. \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SHOW_STATISTIC_LABELS_SUCCESS = "Here is your expenses summary! \n";
    /**
     * Create a fixed length of String.
     * Here we use this command mainly to create a fixed length of empty space to simulate a table.
     * @param string the string to be created.
     * @param length the length of the String created.
     */
    public static String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

    //Reused from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/ with minor modifications
    /**
     * Sort the statistics by the total number of expenses in a certain category.
     * @param hashmap The data structure that store the statistic.
     */
    public static HashMap<String, Integer> sortByNumberOfExpense(HashMap<String, Integer> hashmap) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > listForMap = new LinkedList<Map.Entry<String, Integer> >(hashmap.entrySet());

        // Sort the list
        Collections.sort(listForMap, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> firstValue,
                               Map.Entry<String, Integer> secondValue) {
                return (firstValue.getValue()).compareTo(secondValue.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> tempMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> hm : listForMap) {
            tempMap.put(hm.getKey(), hm.getValue());
        }
        return tempMap;
    }

    //Reused from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/ with minor modifications
    /**
     * Sort the statistics by the total expenses in a certain category.
     * @param hashmap The data structure that store the statistic.
     */
    public static HashMap<String, Double> sortByTotalExpense(HashMap<String, Double> hashmap) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > listForMap = new LinkedList<Map.Entry<String, Double> >(hashmap.entrySet());

        // Sort the list
        Collections.sort(listForMap, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> firstValue,
                               Map.Entry<String, Double> secondValue) {
                return (secondValue.getValue()).compareTo(firstValue.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> tempMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> hm : listForMap) {
            tempMap.put(hm.getKey(), hm.getValue());
        }
        return tempMap;
    }

    /**
     * Executes the show statistic command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which the statistic and brief summary of the expenses shown.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(MESSAGE_SHOW_STATISTIC_LABELS_SUCCESS,
                false, false, true, true);
    }
}
