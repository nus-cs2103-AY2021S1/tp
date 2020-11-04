package seedu.address.logic.textfieldmodules;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AutocompleteModuleTest {
    private final KeyEvent TAB_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.TAB,
            false,
            false,
            false,
            false
    );
    private final KeyEvent SHIFT_TAB_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.TAB,
            true,
            false,
            false,
            false
    );

    private final KeyEvent ENTER_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.ENTER,
            false,
            false,
            false,
            false
    );

    private String sampleCmdPrefix = "test/";
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;

    @Start
    private void start(Stage stage) {
        textField = new TextField();
        stage.setScene(new Scene(new StackPane(textField)));
        stage.show();
    }

    @Test
    public void textFieldWithAutocompleteModule_getFirstSuggestionWithEmptyPrefix_success(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        textField.fireEvent(TAB_EVENT);

        String expected = sampleCmdPrefix + sampleNameList.get(0);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithAutocompleteModule_getFirstSuggestionWithNonEmptyPrefix_success(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        String prefix = "br";
        String firstResultMatchingPrefix = sampleNameList.stream()
                .filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                .findFirst()
                .orElse("");

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        robot.write(prefix);
        textField.fireEvent(TAB_EVENT);

        String expected = sampleCmdPrefix + firstResultMatchingPrefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithAutocompleteModule_lockInFirstSuggestions_success(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        String prefix = "br";
        String firstResultMatchingPrefix = sampleNameList.stream()
                .filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                .findFirst()
                .orElse("");

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        robot.write(prefix);
        textField.fireEvent(TAB_EVENT);
        textField.fireEvent(ENTER_EVENT);

        String expected = firstResultMatchingPrefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithAutocompleteModule_getFirstSuggestionWithPrefixWithNoMatch_prefixReturned(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        String prefix = "Ergot";
        String firstResultMatchingPrefix = sampleNameList.stream()
                .filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                .findFirst()
                .orElse("");

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        robot.write(prefix);
        textField.fireEvent(TAB_EVENT);

        String expected = sampleCmdPrefix + prefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithAutocompleteModule_iterateThroughAllSuggestionsForward_success(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        String prefix = "br";

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        robot.write(prefix);

        for (int i = 0 ; i < sampleNameList.size() + 1; i++) {
            textField.fireEvent(TAB_EVENT);
        }

        String expected = sampleCmdPrefix + prefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithAutocompleteModule_iterateThroughAllSuggestionsBackward_success(FxRobot robot) {
        // Setup
        AutocompleteModule ac = AutocompleteModule.attachTo(textField);
        ac.addSuggestions(sampleCmdPrefix, () -> sampleNameList);

        String prefix = "br";
        String firstResultMatchingPrefix = sampleNameList.stream()
                .filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                .findFirst()
                .orElse("");

        // Simulate user trigger ac
        textField.setText(sampleCmdPrefix);
        textField.end();
        robot.write(prefix);

        for (int i = 0 ; i < sampleNameList.size() + 1; i++) {
            textField.fireEvent(SHIFT_TAB_EVENT);
        }

        String expected = sampleCmdPrefix + prefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }


}