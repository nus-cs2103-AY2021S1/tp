package nustorage.ui.uilogic;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nustorage.logic.commands.CommandResult;
import nustorage.logic.parser.exceptions.ParseException;

public class UiLogicManager implements UiLogic {

    public static final String COMMAND_WORD_NAVIGATION = "goto";
    public static final String INVENTORY_TAB = "inventory";
    public static final String FINANCE_TAB = "finance";

    @FXML
    private TabPane tabPane;

    public UiLogicManager(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    @Override
    public CommandResult execute(String commandText) throws ParseException {
        String command = commandText.split("_")[0];
        String param;
        if (command.equals(COMMAND_WORD_NAVIGATION)) {
            try {
                param = commandText.split("_")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParseException("Please specify a tab to switch to");
            }
            return goToTab(param);
        } else {
            throw new ParseException("This really shouldn't happen. How did you get here?");
        }
    }

    public CommandResult goToTab(String param) throws ParseException {

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

        if (param.equalsIgnoreCase(INVENTORY_TAB)) {
            selectionModel.select(0);
        } else if (param.equalsIgnoreCase(FINANCE_TAB)) {
            selectionModel.select(1);
        } else {
            throw new ParseException(String.format("A tab with the name of %s was not found", param));
        }

        return new CommandResult("Switched to " + param + " tab!");
    }
}
