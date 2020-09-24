// CommandArguments.java

package chopchop.parser;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;

/**
 * A container class to hold a parsed command, holding its:
 * - name
 * - target
 * - unnamed arguments
 * - named arguments
 *
 * For example, a command such as:
 * {@code add ingredient onions /quantity 500g /location fridge /location shelf}
 *
 * Would have the following properties:
 * {@code
 *      command     = "add"
 *      target      = "ingredient"
 *      remaining   = "onions"
 *      arguments   = {
 *                      "quantity": [ "500g" ],
 *                      "location": [ "fridge", "shelf" ]
 *                    }
 * }
 *
 * For named arguments, the name cannot be empty, while the value can be empty. For example,
 * {@code ... /tag /tag /tag} is valid, but {@code ... / owo / uwu} is not.
 *
 * This class makes no judgement on the contents of any of the properties. Note that the
 * ordering of arguments with different names are not preserved (eg. one cannot know that
 * {@code /quantity} came before {@code /location}), but the ordering of arguments with the
 * same name are preserved (eg. {@code /location fridge} will appear before {@code /location shelf}
 * in the list of arguments of a given name).
 */
public class CommandArguments {

    private final String command;
    private final String target;
    private final String remaining;
    private final Map<String, List<String>> arguments;

    /**
     * Constructs a set of command arguments consisting of only the command name.
     *
     * @param command   the name of the command
     */
    public CommandArguments(String command) {
        this.command    = command;
        this.target     = null;
        this.remaining  = null;
        this.arguments  = new HashMap<>();
    }

    /**
     * Constructs a set of command arguments consisting of the command name and
     * some number of named arguments.
     *
     * @param command   the name of the command
     * @param arguments a map of named arguments and their values
     */
    public CommandArguments(String command, Map<String, List<String>> arguments) {
        this.command    = command;
        this.target     = null;
        this.remaining  = null;
        this.arguments  = new HashMap<>(arguments);
    }

    /**
     * Constructs a set of command arguments consisting of the command name, the command
     * target, and some number of named arguments.
     *
     * @param command   the name of the command
     * @param target    the command target
     * @param arguments a map of named arguments and their values
     */
    public CommandArguments(String command, String target, Map<String, List<String>> arguments) {
        this.command    = command;
        this.target     = target;
        this.remaining  = null;
        this.arguments  = new HashMap<>(arguments);
    }

    /**
     * Constructs a set of command arguments consisting of the command name, the command
     * target, any remaining non-named arguments, and some number of named arguments.
     *
     * @param command   the name of the command
     * @param target    the command target
     * @param remaining any remaining non-named arguments
     * @param arguments a map of named arguments and their values
     */
    public CommandArguments(String command, String target, String remaining,
        Map<String, List<String>> arguments) {

        this.command    = command;
        this.target     = target;
        this.remaining  = remaining;
        this.arguments  = new HashMap<>(arguments);
    }

    public String getCommand() {
        return this.command;
    }

    public Optional<String> getTarget() {
        return Optional.ofNullable(this.target);
    }

    public Optional<String> getRemaining() {
        return Optional.ofNullable(this.remaining);
    }

    /**
     * Gets the arguments with the given name. Since it makes sense for some parameters to
     * be specified more than once, this method returns a list of all arguments with the
     * given name.
     */
    public List<String> getArgument(String name) {
        return Optional.ofNullable(this.arguments.get(name))
            .orElse(new ArrayList<>());
    }

    public Map<String, List<String>> getAllArguments() {
        return new HashMap<>(this.arguments);
    }
}
