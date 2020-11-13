package seedu.address.ui;

import static seedu.address.logic.parser.ParserUtil.isDouble;
import static seedu.address.model.budget.Threshold.NO_THRESHOLD_MESSAGE;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoBox extends UiPart<Region> {
    public static final int PRIMARY_FONT_SIZE = 30;
    public static final int SECONDARY_FONT_SIZE = 23;
    public static final int SECOND_ROW_MAX_LENGTH = 7;
    public static final Font DEFAULT_FONT = Font.font("Doppio One Regular", PRIMARY_FONT_SIZE);

    private static final String FXML = "InfoBox.fxml";

    @javafx.fxml.FXML
    private StackPane datePane;

    @javafx.fxml.FXML
    private StackPane timePane;

    @javafx.fxml.FXML
    private StackPane greetingPane;

    @javafx.fxml.FXML
    private Text firstRowText;

    @javafx.fxml.FXML
    private Text secondRowText;

    @javafx.fxml.FXML
    private Text thirdRowText;

    private String greeting;

    private BooleanProperty isBudgetPage;
    private StringProperty secondRowString;
    private StringProperty thresholdString;


    /**
     * Creates a new InfoBox.
     *
     */
    public InfoBox() {
        super(FXML);
        this.isBudgetPage = new SimpleBooleanProperty(false);
        this.secondRowString = new SimpleStringProperty(getDefaultSecondRowText());
        this.thresholdString = new SimpleStringProperty(NO_THRESHOLD_MESSAGE);
        setMainPageInfoBoxText();
    }

    private void setMainPageInfoBoxText() {
        firstRowText.setText(getDefaultFirstRowText());
        setMainPageSecondRowText();
        thirdRowText.setText(getDefaultThirdRowText());
    }


    public static String getDefaultFirstRowText() {
        return new SimpleDateFormat("EEE, dd MMM").format(Calendar.getInstance().getTime());
    }

    private String getDefaultSecondRowText() {
        return new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());
    }

    public void setMainPageSecondRowText() {
        Thread clock = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    if (isBudgetPage.get()) {
                        String value = secondRowString.getValue();
                        handleSecondRowTextIsBudgetPage(value);
                    } else {
                        String value = getDefaultSecondRowText();
                        handleSecondRowTextIsMainPage(value);
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };
        clock.start();
    }

    private void handleSecondRowTextIsBudgetPage(String value) {
        setSecondRowFontSize(value);
        String threshold = thresholdString.get();
        assert isDouble(value);
        String outputValue = "$ " + value;
        if (threshold != NO_THRESHOLD_MESSAGE) {
            setExpenditureColor(secondRowText, threshold, outputValue, value);
        }
        secondRowText.setText(outputValue);
    }

    private void setSecondRowFontSize(String value) {
        if (value.length() > SECOND_ROW_MAX_LENGTH) {
            secondRowText.setFont(
                    Font.font(DEFAULT_FONT.getFamily(), SECONDARY_FONT_SIZE));
        } else {
            secondRowText.setFont(
                    Font.font(DEFAULT_FONT.getFamily(), PRIMARY_FONT_SIZE));
        }
    }

    private void setExpenditureColor(Text text, String threshold, String outputValue, String newValue) {
        assert threshold != NO_THRESHOLD_MESSAGE;
        assert isDouble(newValue);
        assert isDouble(threshold);

        Double newValueDouble = Double.parseDouble(newValue);
        Double thresholdValueDouble = Double.parseDouble(threshold);

        if (newValueDouble > thresholdValueDouble) {
            secondRowText.setFill(Color.RED);
        } else {
            secondRowText.setFill(Color.DARKGREEN);
        }
    }

    private void handleSecondRowTextIsMainPage(String value) {
        setClockColor();
        secondRowText.setText(getDefaultSecondRowText());
    }

    private void setClockColor() {
        secondRowText.setFill(Color.rgb(0, 0, 0));
    }

    public static String getDefaultThirdRowText() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String greeting = "";
        if (hour < 12) {
            greeting = "Good Morning!";
        } else if (hour < 17) {
            greeting = "Good Afternoon!";
        } else {
            greeting = "Good Evening!";
        }
        return greeting;
    }

    public Text getFirstRowText() {
        return firstRowText;
    }

    public Text getSecondRowText() {
        return secondRowText;
    }

    public Text getThirdRowText() {
        return thirdRowText;
    }

    public BooleanProperty getIsBudgetPageProp() {
        return isBudgetPage;
    }

    public StringProperty getSecondRowStringProp() {
        return secondRowString;
    }

    public StringProperty getThresholdStringProp() {
        return thresholdString;
    }
}
