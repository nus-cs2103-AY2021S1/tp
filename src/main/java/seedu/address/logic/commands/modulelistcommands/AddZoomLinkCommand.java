package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ZoomLink;

/**
 * Represents the AddZoomLinkCommand class.
 */
public class AddZoomLinkCommand extends Command {

    public static final String COMMAND_WORD = "addzoomlink";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a zoom link to the module. "
            + "Parameters: " + "INDEX (must be a positive integer) "
            + PREFIX_ZOOM_LINK + "ZOOM LINK "
            + "Example: " + COMMAND_WORD + " "
            + "1" + PREFIX_ZOOM_LINK + "www.zoom.com";

    private final int moduleID;
    private final ZoomLink zoomLink;

    /**
     * Creates and initialises a new AddZoomLinkCommand object.
     *
     * @param moduleIndex Zero based index of the module in the list of modules.
     * @param zoomLink String containing the zoom link to be added to the module.
     */
    public AddZoomLinkCommand(int moduleIndex, ZoomLink zoomLink) {
        this.moduleID = moduleIndex;
        this.zoomLink = zoomLink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Module> lastShownList = model.getFilteredModuleList();

        if (moduleID >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToAddLink = lastShownList.get(moduleID);
        Module updatedModule = moduleToAddLink.addZoomLink("", zoomLink);
        model.setModule(moduleToAddLink, updatedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.commitModuleList();
        return new CommandResult(createSuccessMessage(updatedModule.getName().fullName));
    }

    /**
     * Creates a success message when the zoom link has been successfully added to the desired module.
     *
     * @param moduleName String containing the name of the module.
     * @return String containing the success message.
     */
    public String createSuccessMessage(String moduleName) {
        String message = this.zoomLink + " has been successfully added to " + moduleName;
        return message;
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return False since the sessions has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
