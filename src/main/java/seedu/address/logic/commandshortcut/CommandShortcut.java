package seedu.address.logic.commandshortcut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commandshortcut.exceptions.CommandWordException;

public class CommandShortcut {
    private static final int MAX_LENGTH = 20;
    private CommandWord commandWord;
    private HashSet<String> shortcutSet = new HashSet<>();

    public CommandShortcut(CommandWord commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Constructs a new command Shortcut with a command and aliases
     * @param command Command to be associated with
     * @param aliases Aliases to be initialised with
     */
    public CommandShortcut(CommandWord command, List<String> aliases) {
        this.commandWord = command;
        shortcutSet.addAll(aliases.stream().filter(alias -> !alias.equals(commandWord.getDefaultWord()))
                .collect(Collectors.toList()));
    }

    /**
     * Add an shortcut
     * @param shortcut Alias to be added
     * @throws CommandWordException if shortcut already exists, empty or contains white spaces
     */
    public void addShortcut(String shortcut) throws CommandWordException {
        if (shortcutExist(shortcut)) {
            throw new CommandWordException(shortcut + " already exists!");
        }
        if (shortcut.equals("")) {
            throw new CommandWordException(shortcut + "cannot be empty!");
        }

        if (shortcut.matches(".*\\s.*")) {
            throw new CommandWordException("Shortcut cannot contain white spaces!");
        }

        if (shortcut.length() > MAX_LENGTH) {
            throw new CommandWordException("Shortcut cannot be more than 20 characters!");
        }

        shortcutSet.add(shortcut);
    }


    public boolean shortcutExist(String shortcut) {
        return shortcut.equals(commandWord.getDefaultWord()) || shortcutSet.contains(shortcut);
    }

    /**
     * Remove an shortcut
     * @param shortcut Alias to be removed
     * @throws CommandWordException
     */
    public void removeShortcut(String shortcut) throws CommandWordException {
        if (!shortcutExist(shortcut)) {
            throw new CommandWordException(shortcut + " does not exists!");
        }
        if (commandWord.getDefaultWord().equals(shortcut)) {
            throw new CommandWordException("Cannot remove default word!");
        }

        shortcutSet.remove(shortcut);
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public boolean checkCommandWord(CommandWord word) {
        return word == commandWord;
    }

    public ArrayList<String> getAllShortcuts() {
        return new ArrayList<>(shortcutSet);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(commandWord.toString());
        str.append(": ");
        Iterator<String> it = shortcutSet.iterator();
        while (it.hasNext()) {
            str.append(it.next());
            if (it.hasNext()) {
                str.append(", ");
            }
        }
        return str.toString();
    }
}
