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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.SimulatedKeyPress.*;

@ExtendWith(ApplicationExtension.class)
class FzfModuleTest {
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;

    @Start
    private void start(Stage stage) {
        textField = new TextField();
        stage.setScene(new Scene(new StackPane(textField)));
        stage.show();

    }

    @Test
    public void textFieldWithFzfModule_lockInFirstSuggestion_success(FxRobot robot) {
       FzfModule.attachTo(textField, ()->sampleNameList);

        textField.fireEvent(CTRL_SPACE_EVENT);
        textField.fireEvent(DOWN_ARROW_EVENT);
        robot.write('\n');

        String expected = sampleNameList.get(0);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
}