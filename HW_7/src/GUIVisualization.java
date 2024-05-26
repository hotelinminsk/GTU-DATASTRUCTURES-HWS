import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is used to visualize the data points in a graph.
 * The data points are passed as a list of x and y coordinates.
 * The x and y coordinates are used to plot the graph.
 * The graph can be of two types: line or scatter.
 * The line graph connects the data points with a line.
 *
 */
public class GUIVisualization extends JFrame {
    // Data points to be plotted
    // xDataPoints contains the sizes we do the operations on
    private List<Integer> xDataPoints;
    // yDataPoints contains the time taken to do the operations
    private List<Long> yDataPoints;
    private String plotType = "line";

    public GUIVisualization(String title,String plotType,List<Integer> xDataPoints,List<Long> yDataPoints) {
       this.xDataPoints = xDataPoints;
       this.yDataPoints = yDataPoints;
        setTitle(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        setLocationRelativeTo(null);
        this.plotType = plotType;
    }

    public void setDataPoints(List<Integer> xDataPoints, List<Long> yDataPoints) {
        this.xDataPoints = xDataPoints;
        this.yDataPoints = yDataPoints;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int labelPadding = 20;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // Define the max value for Y to scale the graph properly
        long maxValue = getMaxValue();
        long minValue = getMinValue();

        // Adjusting minimum value to start the y-axis closer to zero
        minValue = Math.min(0, minValue);

        // Draw y-axis lines and labels
        int numberYDivisions = 10;
        for (int i = 0; i <= numberYDivisions; i++) {
            int x0 = padding + labelPadding;
            int x1 = width - padding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding);
            int y1 = y0;
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding + labelPadding + 1 + labelPadding, y0, x1, y1);
            g2.setColor(Color.BLACK);
            String yLabel = String.format("%.2f", ((maxValue - minValue) * ((i * 1.0) / numberYDivisions) + minValue));
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
        }

        // Draw x-axis lines and labels
        for (int i = 0; i < xDataPoints.size(); i++) {
            if (xDataPoints.size() > 1) {
                int x0 = i * (width - padding * 2 - labelPadding) / (xDataPoints.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = height - padding - labelPadding;
                int y1 = y0 - 4;
                if ((i % ((int) ((xDataPoints.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawLine(x0, height - padding - labelPadding - 1 - labelPadding, x0, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = xDataPoints.get(i).toString();
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // Draw the axes
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2f));

        // Draw the graph
        if (plotType.equals("line")) {
            for (int i = 0; i < xDataPoints.size() - 1; i++) {
                int x1 = i * (width - padding * 2 - labelPadding) / (xDataPoints.size() - 1) + padding + labelPadding;
                int y1 = height - padding - labelPadding - (int) ((Math.log(yDataPoints.get(i) * 1.0)) / Math.log(getMaxValue()) * (height - padding * 2 - labelPadding));
                int x2 = (i + 1) * (width - padding * 2 - labelPadding) / (yDataPoints.size() - 1) + padding + labelPadding;
                int y2 = height - padding - labelPadding - (int) ((Math.log(yDataPoints.get(i + 1) * 1.0)) / Math.log(getMaxValue()) * (height - padding * 2 - labelPadding));
                g2.drawLine(x1, y1, x2, y2); // Draw line between data points
            }
        } else if (plotType.equals("scatter")) {
            for (int i = 0; i < xDataPoints.size(); i++) {
                int x = i * (width - padding * 2 - labelPadding) / (xDataPoints.size() - 1) + padding + labelPadding;
                int y = height - padding - labelPadding - (int) ((Math.log(yDataPoints.get(i) * 1.0)) / Math.log(getMaxValue()) * (height - padding * 2 - labelPadding));
                g2.fillOval(x - 3, y - 3, 6, 6); // Draw data point as a small circle
            }

            g2.setStroke(oldStroke);
        }
    }
    //Helper methods to get the min and max values
    //for axis scaling
    private long getMinValue() {
        long min = Long.MAX_VALUE;
        for (Long y : yDataPoints) {
            min = Math.min(min, y);
        }
        return min;
    }

    private long getMaxValue() {
        long max = Long.MIN_VALUE;
        for (Long y : yDataPoints) {
            max = Math.max(max, y);
        }
        return max;
    }
}
