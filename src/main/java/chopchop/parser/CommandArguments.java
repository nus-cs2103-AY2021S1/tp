// CommandArguments.java

package chopchop.parser;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;

public class CommandArguments {

    private final String command;
    private final String target;
    private final String remaining;
    private final Map<String, List<String>> arguments;

    public CommandArguments(String command) {
        this.command    = command;
        this.target     = null;
        this.remaining  = null;
        this.arguments  = new HashMap<>();
    }

    public CommandArguments(String command, Map<String, List<String>> arguments) {
        this.command    = command;
        this.target     = null;
        this.remaining  = null;
        this.arguments  = new HashMap<>(arguments);
    }

    public CommandArguments(String command, String target, Map<String, List<String>> arguments) {
        this.command    = command;
        this.target     = target;
        this.remaining  = null;
        this.arguments  = new HashMap<>(arguments);
    }

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

    public List<String> getArgument(String name) {
        return Optional.ofNullable(this.arguments.get(name))
            .orElse(new ArrayList<>());
    }

    public Map<String, List<String>> getAllArguments() {
        return new HashMap<>(this.arguments);
    }
}
