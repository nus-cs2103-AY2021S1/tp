package seedu.address.logic.textfieldmodules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.SimulatedKeyPress.ENTER_EVENT;
import static seedu.address.testutil.SimulatedKeyPress.SHIFT_TAB_EVENT;
import static seedu.address.testutil.SimulatedKeyPress.TAB_EVENT;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class AutocompleteModuleTest {

    private String sampleCmdPrefix = "test/";
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;

    @BeforeAll
    private static void setHeadless() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
    }

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
        robot.sleep(500);
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
        robot.sleep(500);
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
        robot.sleep(500);
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
        robot.sleep(500);
        robot.write(prefix);

        for (int i = 0; i < sampleNameList.size() + 1; i++) {
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
        robot.sleep(500);
        robot.write(prefix);

        for (int i = 0; i < sampleNameList.size() + 1; i++) {
            textField.fireEvent(SHIFT_TAB_EVENT);
        }

        String expected = sampleCmdPrefix + prefix;
        String actual = textField.getText();

        assertEquals(expected, actual);
    }


}
