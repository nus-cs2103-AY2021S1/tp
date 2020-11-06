package seedu.address.logic.textfieldmodules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.SimulatedKeyPress.CTRL_SPACE_EVENT;
import static seedu.address.testutil.SimulatedKeyPress.DOWN_ARROW_EVENT;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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
class FzfModuleTest {
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;
    private FzfModule fzfModule;
    private StackPane stackPane;

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
        fzfModule = FzfModule.attachTo(textField, () -> sampleNameList);
        stackPane = new StackPane(textField);
        stage.setScene(new Scene(stackPane));
        stage.show();

    }

    @Test
    public void textFieldWithFzfModule_lockInFirstSuggestion_success(FxRobot robot) {

        textField.fireEvent(CTRL_SPACE_EVENT);
        textField.fireEvent(DOWN_ARROW_EVENT);
        robot.sleep(500);
        robot.write('\n');

        String expected = sampleNameList.get(0);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void textFieldWithFzfModule_nonEmptyQuery_validNumberOfSuggestions(FxRobot robot) {

        String query = "br";

        textField.fireEvent(CTRL_SPACE_EVENT);
        robot.sleep(1000);
        robot.write(query);

        long expected = sampleNameList.stream().filter(x -> x.toLowerCase().contains(query)).count();
        long actual = fzfModule.getMenu().getItems().stream().count();

        assertEquals(expected, actual);
    }
}
