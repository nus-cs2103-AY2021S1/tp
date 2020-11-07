package seedu.address.logic.textfieldmodules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class FzfModuleTestBase extends HeadlessTestBase {
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;
    private FzfModule fzfModule;
    private StackPane stackPane;

    @Start
    private void start(Stage stage) {
        textField = new TextField();
        fzfModule = FzfModule.attachTo(textField, () -> sampleNameList);
        stackPane = new StackPane(textField);
        stage.setScene(new Scene(stackPane));
        stage.show();

    }

    @Test
    public void textFieldWithFzfModule_lockInFirstSuggestion_success(FxRobot robot) {

        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .press(KeyCode.ENTER);
        robot.release(new KeyCode[]{});

        String expected = sampleNameList.get(0);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void textFieldWithFzfModule_nonEmptyQuery_validNumberOfSuggestions(FxRobot robot) {

        String query = "br";

        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .write(query);
        robot.release(new KeyCode[]{});

        long expected = sampleNameList.stream().filter(x -> x.toLowerCase().contains(query)).count();
        long actual = fzfModule.getMenu().getItems().stream().count();

        assertEquals(expected, actual);
    }
}
