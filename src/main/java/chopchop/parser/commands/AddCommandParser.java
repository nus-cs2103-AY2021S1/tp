// AddCommandParser.java

package chopchop.parser.commands;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chopchop.util.Result;
import chopchop.units.Quantity;
import chopchop.util.StringView;
import chopchop.parser.CommandArguments;

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
    public static Result<? extends CommandStub> parseAddCommand(CommandArguments args) {
        if (!args.getCommand().equals("add")) {
            return Result.error("invalid command '%s' (expected 'add')", args.getCommand());
        }

        // java type inference sucks, so lambdas are out of the question.
        if (args.getTarget().isEmpty()) {
            return Result.error("'add' command requires a target (either 'recipe' or 'ingredient')");
        }

        var target = args.getTarget().get();

        if (target.equals("ingredient")) {
            return parseAddIngredientCommand(args);
        } else if (target.equals("recipe")) {
            return parseAddRecipeCommand(args);
        } else {
            return Result.error("can only add recipes or ingredients ('%s' invalid)", target);
        }
    }

    /**
     * Parses an 'add ingredient' command. Syntax:
     * {@code add ingredient NAME [/qty QUANTITY] [/expiry DATE]}
     */
    private static Result<AddIngredientCommandStub> parseAddIngredientCommand(CommandArguments args) {
        assert args.getCommand().equals("add")
            && args.getTarget().equals(Optional.of("ingredient"));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("ingredient name cannot be empty");
        }

        Optional<String> foo;
        if ((foo = getFirstUnknownArgument(args, List.of("qty", "ingredient"))).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }

        var name = args.getRemaining().get();

        var qtys = args.getArgument("qty");
        if (qtys.size() > 1) {
            return Result.error("multiple quantities specified");
        }

        var exps = args.getArgument("expiry");
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
                .map(exp -> new AddIngredientCommandStub(name, qty, exp))
            );
    }

    /**
     * Parses an 'add ingredient' command. Syntax:
     * {@code add recipe NAME [/ingredient INGREDIENT_NAME [/qty QTY1]...]... [/step STEP]...}
     */
    private static Result<AddRecipeCommandStub> parseAddRecipeCommand(CommandArguments args) {
        assert args.getCommand().equals("add")
            && args.getTarget().equals(Optional.of("recipe"));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("recipe name cannot be empty");
        }

        Optional<String> foo;
        if ((foo = getFirstUnknownArgument(args, List.of("qty", "ingredient", "step"))).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }


        var name = args.getRemaining().get();

        return parseIngredientList(args)
            .map(ingrs ->
                new AddRecipeCommandStub(name, ingrs,
                    args.getAllArguments()
                        .stream()
                        .filter(p -> p.fst().equals("step"))
                        .map(p -> p.snd())
                        .map(x -> new RecipeStepStub(x))
                        .collect(Collectors.toList()))
            );
    }

    /**
     * Parse the list of ingredients.
     */
    private static Result<List<IngredientUsageStub>> parseIngredientList(CommandArguments args) {

        // what is this, imperative code??
        var arglist = args.getAllArguments();

        var ingredients = new ArrayList<IngredientUsageStub>();

        for (int i = 0; i < arglist.size(); i++) {

            var p = arglist.get(i);
            if (p.fst().equals("ingredient")) {

                var name = p.fst();
                Optional<Quantity> quantity = Optional.empty();

                // check the next argument for a quantity (which is optional)
                if (i + 1 < arglist.size()) {
                    var q = arglist.get(i + 1);
                    if (q.fst().equals("qty")) {
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

                ingredients.add(new IngredientUsageStub(name, quantity));

            } else if (p.fst().equals("qty")) {
                return Result.error("'/qty' without ingredient in argument %d [/qty %s...]",
                    i + 1, new StringView(p.snd()).take(4));
            } else {
                // do nothing.
            }
        }

        return Result.of(ingredients);
    }
















    private static Result<String> dummyParseExpiryDate(String date) {
        return Result.of(date);
    }

    private static interface CommandStub {
    }

    private static class RecipeStepStub {
        private final String step;

        public RecipeStepStub(String step) {
            this.step = step;
        }
    }

    private static class IngredientUsageStub {
        private final String name;
        private final Optional<Quantity> quantity;

        public IngredientUsageStub(String name, Optional<Quantity> qty) {
            this.name = name;
            this.quantity = qty;
        }
    }

    private static class AddRecipeCommandStub implements CommandStub {

        private final String name;
        private final List<RecipeStepStub> steps;
        private final List<IngredientUsageStub> ingredients;

        public AddRecipeCommandStub(String name, List<IngredientUsageStub> ingredients,
            List<RecipeStepStub> steps) {

            this.name = name;
            this.steps = steps;
            this.ingredients = ingredients;
        }
    }

    private static class AddIngredientCommandStub implements CommandStub {

        private final String name;
        private final Optional<Quantity> quantity;
        private final Optional<String> expiryDate;

        public AddIngredientCommandStub(String name, Optional<Quantity> qty, Optional<String> expiry) {
            this.name = name;
            this.quantity = qty;
            this.expiryDate = expiry;
        }
    }
}
