package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//@@author lettuceman4-reused
//Reused from https://stackoverflow.com/questions/42065871/how-to-display-values-in-javafx-pie-chart
// with minor modifications
public class LabeledPieChart extends PieChart {
    private final Map<Data, Text> labels = new HashMap<>();

    /**
     * Creates a pie chart with labels indicating the percentage of each slice.
     */
    public LabeledPieChart() {
        super();
        this.getData().addListener((ListChangeListener.Change<? extends Data> c) -> {
            addLabels();
        });
    }

    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);

        double centerX = contentWidth / 2 + left;
        double centerY = contentHeight / 2 + top;

        layoutLabels(centerX, centerY);
        this.setLabelsVisible(false);
        // this.setPrefSize(50, 50);
    }

    private void addLabels() {

        for (Text label : labels.values()) {
            this.getChartChildren().remove(label);
        }
        labels.clear();

        for (Data data : getData()) {
            final Text dataText;
            final double displayValue = data.getPieValue();
            dataText = new Text(String.format("$%.2f", displayValue));
            labels.put(data, dataText);
            this.getChartChildren().add(dataText);
        }
    }

    private void layoutLabels(double centerX, double centerY) {

        double total = 0.0;
        for (Data d : this.getData()) {
            total += d.getPieValue();
        }
        double scale = (total != 0) ? 360 / total : 0;

        for (Map.Entry<Data, Text> entry : labels.entrySet()) {
            Data vData = entry.getKey();
            Text vText = entry.getValue();

            Region vNode = (Region) vData.getNode();
            Arc arc = (Arc) vNode.getShape();

            double start = arc.getStartAngle();

            double size = (isClockwise()) ? (-scale * Math.abs(vData.getPieValue()))
                                            : (scale * Math.abs(vData.getPieValue()));
            final double angle = normalizeAngle(start + (size / 2));
            final double sproutX = calcX(angle, arc.getRadiusX() / 2, centerX);
            final double sproutY = calcY(angle, arc.getRadiusY() / 2, centerY);

            vText.relocate(
                sproutX - vText.getBoundsInLocal().getWidth() + 15,
                sproutY - vText.getBoundsInLocal().getHeight());
            vText.setFont(Font.font("Century Gothic"));
            vText.setFill(Color.WHITE);
        }

    }

    private static double normalizeAngle(double angle) {
        double a = angle % 360;
        if (a <= -180) {
            a += 360;
        }
        if (a > 180) {
            a -= 360;
        }
        return a;
    }

    private static double calcX(double angle, double radius, double centerX) {
        return (double) (centerX + radius * Math.cos(Math.toRadians(-angle)));
    }

    private static double calcY(double angle, double radius, double centerY) {
        return (double) (centerY + radius * Math.sin(Math.toRadians(-angle)));
    }
}
