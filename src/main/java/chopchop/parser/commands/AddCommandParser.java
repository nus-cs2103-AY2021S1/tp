// AddCommandParser.java

package chopchop.parser.commands;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chopchop.util.Result;
import chopchop.util.StringView;

import chopchop.model.recipe.Recipe;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientReference;

import chopchop.model.attributes.Name;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.units.Count;

import chopchop.parser.ArgName;
import chopchop.parser.CommandArguments;
import chopchop.logic.parser.CliSyntax;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.AddRecipeCommand;
import chopchop.logic.commands.AddIngredientCommand;

import static chopchop.parser.commands.CommonParser.getFirstUnknownArgument;

public class AddCommandParser {

    /**
     * Parses an 'add' command. Syntax(es):
     * {@code add recipe NAME [/ingredient INGREDIENT_NAME [/qty QTY1]...]... [/step STEP]...}
     * {@code add ingredient NAME [/qty QUANTITY] [/expiry DATE]}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     an AddCommand, if the input was valid.
     */
    public static Result<? extends Command> parseAddCommand(CommandArguments args) {
        if (!args.getCommand().equals(CliSyntax.COMMAND_ADD)) {
            return Result.error("invalid command '%s' (expected '%s')", args.getCommand(), CliSyntax.COMMAND_ADD);
        }

        // java type inference sucks, so lambdas are out of the question.
        if (args.getTarget().isEmpty()) {
            return Result.error("'%s' command requires a target (either 'recipe' or 'ingredient')",
                CliSyntax.COMMAND_ADD);
        }

        var target = args.getTarget().get();

        if (target.equals(CliSyntax.TARGET_INGREDIENT)) {
            return parseAddIngredientCommand(args);
        } else if (target.equals(CliSyntax.TARGET_RECIPE)) {
            return parseAddRecipeCommand(args);
        } else {
            return Result.error("can only add recipes or ingredients ('%s' invalid)", target);
        }
    }

    /**
     * Parses an 'add ingredient' command. Syntax:
     * {@code add ingredient NAME [/qty QUANTITY] [/expiry DATE]}
     */
    private static Result<AddIngredientCommand> parseAddIngredientCommand(CommandArguments args) {
        assert args.getCommand().equals(CliSyntax.COMMAND_ADD)
            && args.getTarget().equals(Optional.of(CliSyntax.TARGET_INGREDIENT));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("ingredient name cannot be empty");
        }

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(CliSyntax.ARG_QUANTITY,
            CliSyntax.ARG_EXPIRY))).isPresent()) {

            return Result.error("unknown argument '%s'", foo.get());
        }

        var name = args.getRemaining().get();

        var qtys = args.getArgument(CliSyntax.ARG_QUANTITY);
        if (qtys.size() > 1) {
            return Result.error("multiple quantities specified");
        }

        var exps = args.getArgument(CliSyntax.ARG_EXPIRY);
        if (exps.size() > 1) {
            return Result.error("multiple expiry dates specified");
        }

        // looks weird, but basically this extracts the /qty and /expiry arguments (if present),
        // then constructs the command from it -- while returning any intermediate error messages.
        return Result.transpose(qtys
            .stream()
            .findFirst()
            .map(Quantity::parse))
            .then(qty -> Result.transpose(exps
                .stream()
                .findFirst()
                .map(e -> dummyParseExpiryDate(e)))
                .map(exp -> createAddIngredientCommand(name, qty, exp))
            );
    }

    /**
     * Parses an 'add ingredient' command. Syntax:
     * {@code add recipe NAME [/ingredient INGREDIENT_NAME [/qty QTY1]...]... [/step STEP]...}
     */
    private static Result<AddRecipeCommand> parseAddRecipeCommand(CommandArguments args) {
        assert args.getCommand().equals(CliSyntax.COMMAND_ADD)
            && args.getTarget().equals(Optional.of(CliSyntax.TARGET_RECIPE));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("recipe name cannot be empty");
        }

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(CliSyntax.ARG_QUANTITY,
            CliSyntax.ARG_INGREDIENT, CliSyntax.ARG_STEP))).isPresent()) {

            return Result.error("unknown argument '%s'", foo.get());
        }


        var name = args.getRemaining().get();

        return parseIngredientList(args)
            .map(ingrs -> createAddRecipeCommand(name, ingrs,
                    args.getAllArguments()
                        .stream()
                        .filter(p -> p.fst().equals(CliSyntax.ARG_STEP))
                        .map(p -> p.snd())
                        .map(x -> new Step(x))
                        .collect(Collectors.toList()))
            );
    }

    /**
     * Parse the list of ingredients.
     */
    private static Result<List<IngredientReference>> parseIngredientList(CommandArguments args) {

        // what is this, imperative code??
        var arglist = args.getAllArguments();

        var ingredients = new ArrayList<IngredientReference>();

        for (int i = 0; i < arglist.size(); i++) {

            var p = arglist.get(i);
            if (p.fst().equals(CliSyntax.ARG_INGREDIENT)) {

                var name = p.snd();
                Optional<Quantity> quantity = Optional.empty();

                // check the next argument for a quantity (which is optional)
                if (i + 1 < arglist.size()) {
                    var q = arglist.get(i + 1);
                    if (q.fst().equals(CliSyntax.ARG_QUANTITY)) {
                        var qty = Quantity.parse(q.snd());
                        if (qty.isError()) {
                            return Result.error(qty.getError());
                        } else {
                            quantity = Optional.of(qty.getValue());

                            // skip the quantity now that we've handled it.
                            i++;
                        }
                    }
                }

                ingredients.add(createIngredientReference(name, quantity));

            } else if (p.fst().equals(CliSyntax.ARG_QUANTITY)) {
                return Result.error("'%s' without ingredient in argument %d [/qty %s...]",
                    CliSyntax.ARG_QUANTITY, i + 1, new StringView(p.snd()).take(4));
            } else {
                // do nothing.
            }
        }

        return Result.of(ingredients);
    }












    private static IngredientReference createIngredientReference(String name, Optional<Quantity> qty) {
        return new IngredientReference(name, qty.orElse(Count.of(1)));
    }

    private static AddRecipeCommand createAddRecipeCommand(String name,
        List<IngredientReference> ingredients, List<Step> steps) {

        return new AddRecipeCommand(new Recipe(
            new Name(name), ingredients, steps
        ));
    }


    private static AddIngredientCommand createAddIngredientCommand(String name,
        Optional<Quantity> qty, Optional<String> expiry) {

        return new AddIngredientCommand(new Ingredient(new Name(name),
            qty.orElse(Count.of(1)),
            expiry.map(ExpiryDate::new).orElse(ExpiryDate.none())
        ));
    }

    private static Result<String> dummyParseExpiryDate(String date) {
        return Result.of(date);
    }
}
