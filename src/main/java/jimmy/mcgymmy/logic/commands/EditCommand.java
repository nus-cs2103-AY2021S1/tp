package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.logic.parser.parameter.OptionalParameter;
import jimmy.mcgymmy.logic.parser.parameter.Parameter;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.food.Carbohydrate;
import jimmy.mcgymmy.model.food.Fat;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.food.Name;
import jimmy.mcgymmy.model.food.Protein;

/**
 * Edits the details of an existing food in the address book.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Food: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food already exists in the address book.";

    private Parameter<Index> indexParameter = this.addParameter(
            "index",
            "",
            "index number used in the displayed food list.",
            "2",
            ParserUtil::parseIndex
    );
    private OptionalParameter<Name> nameParameter = this.addOptionalParameter(
            "name",
            "n",
            "Name of food to add",
            "John Doe",
            ParserUtil::parseName
    );
    private OptionalParameter<Protein> proteinParameter = this.addOptionalParameter(
            "protein value",
            "p",
            "Protein value of food (g)",
            "10",
            ParserUtil::parseProtein
    );
    private OptionalParameter<Fat> fatParameter = this.addOptionalParameter(
            "fat value",
            "f",
            "Fat value of food (g)",
            "10",
            ParserUtil::parseFat
    );
    private OptionalParameter<Carbohydrate> carbParameter = this.addOptionalParameter(
        "carb value",
        "c",
        "Carbohydrate value of food (g)",
        "10",
        ParserUtil::parseCarb
    );

    void setParameters(Parameter<Index> indexParameter, OptionalParameter<Name> nameParameter,
                       OptionalParameter<Protein> proteinParameter, OptionalParameter<Fat> fatParameter,
                       OptionalParameter<Carbohydrate> carbParameter) {
        this.indexParameter = indexParameter;
        this.nameParameter = nameParameter;
        this.proteinParameter = proteinParameter;
        this.fatParameter = fatParameter;
        this.carbParameter = carbParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();
        Index index = indexParameter.consume();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Food foodToEdit = lastShownList.get(index.getZeroBased());

        Name newName = this.nameParameter.getValue().orElseGet(foodToEdit::getName);
        Protein newProtein = this.proteinParameter.getValue().orElseGet(foodToEdit::getProtein);
        Fat newFat = this.fatParameter.getValue().orElseGet(foodToEdit::getFat);
        Carbohydrate newCarb = this.carbParameter.getValue().orElseGet(foodToEdit::getCarbs);

        // as with AddCommand, address and get tags left as exercises
        Food editedFood = new Food(newName, newProtein, newFat, newCarb);

        if (foodToEdit != editedFood && model.hasFood(editedFood)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.setFood(foodToEdit, editedFood);
        model.updateFilteredFoodList(Model.PREDICATE_SHOW_ALL_FOODS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedFood));
    }
}
