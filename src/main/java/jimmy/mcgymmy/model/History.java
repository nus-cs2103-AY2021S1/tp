package jimmy.mcgymmy.model;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.Predicate;

import javafx.util.Pair;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.macro.MacroList;

class History {
    private final Stack<Pair<McGymmy, Pair<Predicate<Food>, MacroList>>> stack;

    History() {
        stack = new Stack<>();
    }

    void save(ModelManager modelManager) {
        McGymmy mcGymmy = new McGymmy(modelManager.getMcGymmy());
        Predicate<Food> predicate = modelManager.getFilterPredicate();
        MacroList macroList = modelManager.getMacroList();
        stack.push(new Pair<>(mcGymmy, new Pair<>(predicate, macroList)));
    }

    boolean empty() {
        return stack.empty();
    }

    void pop() throws EmptyStackException {
        assert !stack.empty() : "History is empty";
        stack.pop();
    }

    McGymmy getMcGymmy() throws EmptyStackException {
        assert !stack.empty() : "History is empty";
        return stack.peek().getKey();
    }

    Predicate<Food> getPredicate() throws EmptyStackException {
        assert !stack.empty() : "History is empty";
        return stack.peek().getValue().getKey();
    }

    MacroList getMacroList() throws EmptyStackException {
        assert !stack.empty() : "History is empty";
        return stack.peek().getValue().getValue();
    }
}

