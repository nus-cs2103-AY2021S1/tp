package seedu.address.ui;

import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.event.ActionEvent;
import java.security.Key;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FzfModule {

    private Supplier<List<String>> suggestionGen;
    private boolean isFzfMode = false;
    private int fzfStartPos;
    private double fzfX;
    private double fzfY;

    private TextField query = new TextField() {
        {
            setSkin(createDefaultSkin());
        }
    };

    private TextField textField;

    ContextMenu fzfMenu;

    private FzfModule(TextField tf, Supplier<List<String>> supplier) {
        fzfMenu = new ContextMenu();
        this.suggestionGen = supplier;
        this.textField = tf;
        setupFzf();
    }
    public static FzfModule attachTo(TextField tf, Supplier<List<String>> supplier) {
        return new FzfModule(tf, supplier);
    }
    private MenuItem buildFzfMenuItem(String option, String query) {
        MenuItem mi = new MenuItem("", buildTextFlow(option, query));
        mi.setOnAction(e -> {
            textField.replaceText(fzfStartPos, fzfStartPos + this.query.getText().length(), option);
            toggleFzfOff();
        });
        return mi;
    }

    public void toggleFzfOn(int caretPos, double x, double y) {
        if (!isFzfMode) {
            System.out.println("Fzf Toggled On");
            fzfStartPos = caretPos;
            isFzfMode = true;
            fzfX = x;
            fzfY = y;
        }
    }

    public void toggleFzfOff() {
        if(isFzfMode){
            System.out.println("Fzf Toggled Off");
            isFzfMode = false;
            query.setText("");
        }
    }

    private void setupFzf() {
        textField.caretPositionProperty().addListener((unused1, unused2, newPosition) -> {
            if (newPosition.intValue() < fzfStartPos || newPosition.intValue() > fzfStartPos + query.getText().length()) {
                toggleFzfOff();
            }
        });

        textField.addEventFilter(KeyEvent.ANY, (event) -> {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        if (isFzfMode) {
                            toggleFzfOff();
                        }
                    }
                }
        );

        textField.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
                    if (event.isControlDown() && event.getCode() == KeyCode.SPACE) {
                        TextInputControl control = (TextInputControl) event.getSource();
                        Point2D pos = control.getInputMethodRequests().getTextLocation(0);

                        toggleFzfOff();
                        toggleFzfOn(control.getCaretPosition(), pos.getX(), pos.getY());
                    }
                    if(!isFzfMode) {
                        fzfMenu.hide();
                    }
                }
        );

        textField.addEventFilter(KeyEvent.ANY, (event) -> {
                    if (isFzfMode) {
                        if(event.getCode() == KeyCode.DOWN
                                || event.getCode() == KeyCode.UP
                        ) {
                            System.out.println(event.getCode());

                            fzfMenu.fireEvent(event.copyFor(fzfMenu,fzfMenu));
                            event.consume();
                            return;
                        }

                        // Redirect Event to TextField
                        query.fireEvent(event.copyFor(query,query));
                        String queryText = query.getText();

                        fzfMenu.getItems().clear();
                        fzfMenu.getItems().addAll(
                                suggestionGen.get().stream()
                                        .filter( x -> x.toLowerCase().contains(queryText.toLowerCase()))
                                        .map( x -> buildFzfMenuItem(x, queryText) )
                                        .collect(Collectors.toList())
                        );
                        if (fzfMenu.getItems().isEmpty()) {
                            fzfMenu.hide();
                        }

                        if(!fzfMenu.isShowing()) {
                            fzfMenu.show(textField, fzfX, fzfY);
                        }

                    }
                }
        );



    }

    private TextFlow buildTextFlow(String text, String query) {
        if (query.length() == 0) {
            Text t = new Text(text);
            t.setFill(Color.WHITE);
            return new TextFlow(t);
        }
        int filterIndex = text.toLowerCase().indexOf(query.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + query.length()));
        Text textFilter = new Text(text.substring(filterIndex,  filterIndex + query.length()));

        textBefore.setFill(Color.WHITE);
        textAfter.setFill(Color.WHITE);
        textFilter.setFill(Color.ORANGE);

        return new TextFlow(textBefore, textFilter, textAfter);
    }

}
