package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.logic.parser.parameter.Parameter;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.tag.Tag;

/**
 * Tags a food item with a Tag.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String SHORT_DESCRIPTION = "Add tags to the selected food item.";

    public static final String MESSAGE_SUCCESS = "New tag added: %s";
    public static final String MESSAGE_DUPLICATE_TAG = "Tag %s already exists in %s";

    private Parameter<Index> indexParameter = this.addParameter(
            "index",
            "",
            "Index of selected Food",
            "1",
            ParserUtil::parseIndex
    );

    private Parameter<Tag> tagParameter = this.addParameter(
            "Tag",
            "t",
            "Tag to be added to food",
            "Lunch",
            ParserUtil::parseTag
    );

    void setParameters(Parameter<Index> indexParameter, Parameter<Tag> tagParameter) {
        this.indexParameter = indexParameter;
        this.tagParameter = tagParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();
        Index index = indexParameter.consume();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToTag = lastShownList.get(index.getZeroBased());
        Tag tag = tagParameter.consume();

        if (foodToTag.hasTag(tag)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TAG, tag.tagName, foodToTag.getName().fullName));
        }

        Food newFood = foodToTag.addTag(tag);
        model.setFood(index, newFood); //To refresh the card
        return new CommandResult(String.format(MESSAGE_SUCCESS, tag.tagName));
    }
}
