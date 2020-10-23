// AutoCompleter.java

package chopchop.logic.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import chopchop.commons.util.StringView;
import chopchop.commons.util.Strings;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;
import chopchop.logic.parser.CommandParser;
import chopchop.logic.parser.commands.CommandTarget;
import chopchop.model.Entry;
import chopchop.model.Model;

public class AutoCompleter {

    private int lastCompletionIndex = 0;
    private List<String> lastViableCompletions = null;

    /**
     * Resets the internal state of the completer, namely the last-provided completion. When
     * the user types into the text field, we should restart the completion cycling.
     */
    public void resetCompletionState() {
        this.lastCompletionIndex = 0;
        this.lastViableCompletions = null;
    }

    /**
     * Computes the completion for the given user input. If there is no completion
     * available, the string is returned as-is.
     *
     * @param parser the command parser
     * @param model  the model
     * @param orig   the input
     * @return       the auto-completed input
     */
    public String getCompletionForInput(CommandParser parser, Model model, String orig) {

        // can't read your mind.
        if (orig.isEmpty()) {
            return orig;
        }

        // first, try to parse the arguments. this doesn't run the command-specific parser,
        // just the one that splits arguments.
        var res = parser.parseArgs(orig);

        if (res.isError()) {
            return orig;
        }

        var args = res.getValue();
        switch (getRequiredCompletion(args, orig)) {

        case COMMAND_NAME:
            return completeCommand(args, orig);

        case TARGET_NAME:
            return completeTarget(args, orig);

        case ARGUMENT_NAME:
            return completeArgument(args, orig);

        case RECIPE_NAME:
            return completeRecipe(model, orig);

        case INGREDIENT_NAME:
            return completeIngredient(model, orig);

        // TODO: tags
        case TAG_NAME:
            return orig;

        case NONE: // fallthrough
        default:
            return orig;
        }
    }





    /**
     * Returns a completion for the command only.
     */
    private String completeCommand(CommandArguments args, String orig) {
        for (var cmd : Strings.COMMAND_NAMES) {
            if (cmd.startsWith(args.getCommand())) {
                return cmd + " ";
            }
        }

        return orig;
    }

    /**
     * Returns a completion for the target only.
     */
    private String completeTarget(CommandArguments args, String orig) {

        var target = args.getFirstWordFromRemaining();

        for (var t : CommandTarget.values()) {
            var ts = t.toString();
            if (ts.equals(target)) {
                return orig;
            } else if (ts.startsWith(target)) {
                return orig + ts.substring(target.length()) + " ";
            }
        }

        return orig;
    }

    /**
     * Returns a completion for the argument name only.
     */
    private String completeArgument(CommandArguments args, String orig) {
        assert args.getAllArguments().size() > 0;

        var lastArg = args.getAllArguments().get(args.getAllArguments().size() - 1);
        var argName = lastArg.fst().toString();

        // we could complete all arguments for you, but we can do something a little smarter
        // by looking at the current command+target combo to get the valid arguments.

        var cmd = args.getCommand();
        var tgt = args.getFirstWordFromRemaining();

        var validArguments = new ArrayList<ArgName>();

        if (cmd.equals(Strings.COMMAND_ADD)) {
            if (tgt.equals(CommandTarget.RECIPE.toString())) {
                validArguments.add(Strings.ARG_INGREDIENT);
                validArguments.add(Strings.ARG_QUANTITY);
                validArguments.add(Strings.ARG_STEP);
                validArguments.add(Strings.ARG_TAG);

            } else if (tgt.equals(CommandTarget.INGREDIENT.toString())) {

                validArguments.add(Strings.ARG_QUANTITY);
                validArguments.add(Strings.ARG_EXPIRY);
                validArguments.add(Strings.ARG_TAG);

            }
        }

        for (var validArg : validArguments) {
            var va = validArg.toString();

            if (va.equals(argName)) {
                return orig;
            } else if (va.startsWith(argName)) {
                return orig + va.substring(argName.length()) + " ";
            }
        }

        return orig;
    }







    private <T extends Entry> Optional<String> completeNamedItem(String orig, List<T> entries) {

        var words = new StringView(orig).words();
        assert !words.isEmpty();

        // the partial item name.
        var partial = words.get(words.size() - 1);
        assert !partial.isEmpty();

        // the entire command string *except* the partial item name.
        var allExceptLast = orig.stripTrailing().substring(0,
            orig.stripTrailing().length() - partial.length());

        // make a copy of the list, then sort by name length.
        var sortedList = new ArrayList<>(entries);
        sortedList.sort((a, b) -> {
            return a.getName().length() - b.getName().length();
        });

        if (this.lastViableCompletions == null) {
            this.lastViableCompletions = new ArrayList<String>();

            for (var entry : sortedList) {
                var name = entry.getName();

                if (name.startsWith(partial)) {
                    this.lastViableCompletions.add(name);
                }
            }
        }

        assert this.lastViableCompletions != null;

        if (this.lastViableCompletions.isEmpty()) {
            return Optional.empty();
        } else {

            // this should always hold, because:
            // (a) we always perform the modulo at the end
            // (b) the list of viableCompletions should not change as long as the
            //     internal state is not reset
            // (c) if 'partial' changed due to user input, then we are supposed to be reset.

            assert this.lastCompletionIndex < this.lastViableCompletions.size();

            var completion = this.lastViableCompletions.get(this.lastCompletionIndex);
            this.lastCompletionIndex = (this.lastCompletionIndex + 1) % this.lastViableCompletions.size();

            return Optional.of(allExceptLast + completion + " ");
        }
    }



    /**
     * Returns a completion for the recipe name only.
     */
    private String completeRecipe(Model model, String orig) {

        return completeNamedItem(orig, model.getRecipeBook().getEntryList())
            .orElse(orig);
    }

    /**
     * Returns a completion for the ingredient name only.
     */
    private String completeIngredient(Model model, String orig) {

        return completeNamedItem(orig, model.getIngredientBook().getEntryList())
            .orElse(orig);
    }

    /**
     * Returns the required kind of completion for the current state of the user input.
     */
    private RequiredCompletion getRequiredCompletion(CommandArguments args, String orig) {

        if (args.getAllArguments().isEmpty()) {

            // if there's no remaining bits, then you clearly want the command name.
            if (args.getRemaining().isEmpty()) {
                return RequiredCompletion.COMMAND_NAME;
            }

            var cmd = args.getCommand();
            var sv = new StringView(args.getRemaining());

            if (commandRequiresTarget(cmd) && sv.words().size() == 1) {
                return RequiredCompletion.TARGET_NAME;
            }

            // eg. 'find recipe' -- there's nothing to complete.
            if (!commandRequiresItemReference(cmd)) {
                return RequiredCompletion.NONE;
            }

            if (commandRequiresTarget(cmd)) {
                var target = args.getFirstWordFromRemaining();
                return CommandTarget.of(target)
                    .map(tgt -> {
                        switch (tgt) {
                        case RECIPE:
                            return RequiredCompletion.RECIPE_NAME;

                        case INGREDIENT:
                            return RequiredCompletion.INGREDIENT_NAME;

                        default:
                            return RequiredCompletion.NONE;
                        }
                    }).orElse(RequiredCompletion.NONE);
            } else if (commandRequiresItemReference(cmd)) {

                if (cmd.equals(Strings.COMMAND_MAKE)) {
                    return RequiredCompletion.RECIPE_NAME;
                } else if (cmd.equals(Strings.COMMAND_USE)) {
                    return RequiredCompletion.INGREDIENT_NAME;
                } else {
                    return RequiredCompletion.NONE;
                }

            } else {
                return RequiredCompletion.NONE;
            }

        } else {

            // get the last argument.
            // congratulations, it's 2020 and you're dumb langauge collection library
            // has neither a front() nor back() method.
            var last = args.getAllArguments().get(args.getAllArguments().size() - 1);
            var lastArg = last.fst();
            var lastVal = last.snd();

            if (!lastVal.isEmpty()) {

                // now we should check what the name is.
                if (lastArg.equals(Strings.ARG_INGREDIENT)) {
                    return RequiredCompletion.INGREDIENT_NAME;
                } else if (lastArg.equals(Strings.ARG_TAG)) {
                    return RequiredCompletion.TAG_NAME;
                } else {
                    return RequiredCompletion.NONE;
                }
            } else if (orig.endsWith(" ")) {

                // a bit dirty, but if the last char in the raw input was a space,
                // then we shouldn't complete anything (since the argument name was
                // already finished.
                return RequiredCompletion.NONE;

            } else {
                // complete the argument name.
                return RequiredCompletion.ARGUMENT_NAME;
            }
        }
    }

    private boolean commandRequiresTarget(String commandName) {
        return List.of(
            Strings.COMMAND_ADD,
            Strings.COMMAND_LIST,
            Strings.COMMAND_FIND,
            Strings.COMMAND_DELETE
        ).indexOf(commandName) >= 0;
    }

    private boolean commandRequiresItemReference(String commandName) {
        return List.of(
            Strings.COMMAND_USE,
            Strings.COMMAND_MAKE,
            Strings.COMMAND_DELETE
        ).indexOf(commandName) >= 0;
    }

    enum RequiredCompletion {
        NONE,

        COMMAND_NAME,
        TARGET_NAME,
        ARGUMENT_NAME,
        RECIPE_NAME,
        INGREDIENT_NAME,
        TAG_NAME
    }
}
