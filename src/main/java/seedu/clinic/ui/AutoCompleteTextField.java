package seedu.clinic.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.AssignMacroCommand;
import seedu.clinic.logic.commands.ClearCommand;
import seedu.clinic.logic.commands.DeleteCommand;
import seedu.clinic.logic.commands.EditCommand;
import seedu.clinic.logic.commands.ExitCommand;
import seedu.clinic.logic.commands.FindCommand;
import seedu.clinic.logic.commands.HelpCommand;
import seedu.clinic.logic.commands.ListCommand;
import seedu.clinic.logic.commands.ListMacroCommand;
import seedu.clinic.logic.commands.RedoCommand;
import seedu.clinic.logic.commands.RemoveMacroCommand;
import seedu.clinic.logic.commands.UndoCommand;
import seedu.clinic.logic.commands.UpdateCommand;
import seedu.clinic.logic.commands.ViewCommand;


/**
 * A TextField with added implementation of "autocomplete" functionality.
 * Entries are based on Command_Usage.
 */
public class AutoCompleteTextField extends TextField {
    private static final SortedSet<String> entries = new TreeSet<>();
    private ContextMenu popUpEntries;

    /**
     * Constructor for autocomplete.
     */
    public AutoCompleteTextField() {
        super();
        this.setEntries();
        popUpEntries = new ContextMenu();
        textProperty().addListener((observableValue, s, s2) -> {
            if (getText().length() == 0) {
                popUpEntries.hide();
            } else {
                popUpEntries.hide();
                LinkedList<String> searchResult = new LinkedList<>();
                searchResult.addAll(entries.subSet(getText(), getText() + Character.MAX_VALUE));
                populatePopup(searchResult);
                if (!popUpEntries.isShowing()) {
                    popUpEntries.show(AutoCompleteTextField.this, Side.BOTTOM, 15, -180);
                }
            }
        });

        focusedProperty().addListener((observableValue, aBoolean, aBoolean2) ->
                popUpEntries.hide());
    }

    /**
     * Create the existing set of autocomplete entries.
     */
    private void setEntries() {
        entries.add(AddCommand.COMPULSORY_ADD_SUPPLIER_COMMAND);
        entries.add(AddCommand.COMPULSORY_ADD_WAREHOUSE_COMMAND);
        entries.add(AssignMacroCommand.COMPLETE_ASSIGN_MACRO_COMMAND);
        entries.add(ClearCommand.COMMAND_WORD);
        entries.add(DeleteCommand.COMPLETE_DELETE_SUPPLIER_COMMAND);
        entries.add(DeleteCommand.COMPLETE_DELETE_WAREHOUSE_COMMAND);
        entries.add(DeleteCommand.COMPLETE_DELETE_SUPPLIER_PRODUCT_COMMAND);
        entries.add(DeleteCommand.COMPLETE_DELETE_WAREHOUSE_PRODUCT_COMMAND);
        entries.add(EditCommand.COMPULSORY_EDIT_SUPPLIER_COMMAND);
        entries.add(EditCommand.COMPULSORY_EDIT_WAREHOUSE_COMMAND);
        entries.add(ExitCommand.COMMAND_WORD);
        entries.add(FindCommand.COMPLETE_FIND_SUPPLIER_COMMAND);
        entries.add(FindCommand.COMPLETE_FIND_WAREHOUSE_COMMAND);
        entries.add(ListCommand.COMMAND_WORD);
        entries.add(ListMacroCommand.COMMAND_WORD);
        entries.add(HelpCommand.COMMAND_WORD);
        entries.add(RedoCommand.COMMAND_WORD);
        entries.add(RemoveMacroCommand.COMPLETE_REMOVE_MACRO_COMMAND);
        entries.add(UndoCommand.COMMAND_WORD);
        entries.add(UpdateCommand.COMPULSORY_UPDATE_SUPPLIER_COMMAND);
        entries.add(UpdateCommand.COMPULSORY_UPDATE_WAREHOUSE_COMMAND);
        entries.add(ViewCommand.COMMAND_WORD);
    }

    /**
     * Populate the entry set matching the user input.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                setText(result);
                popUpEntries.hide();
            });
            menuItems.add(item);
        }
        popUpEntries.getItems().clear();
        popUpEntries.getItems().addAll(menuItems);
    }

}
