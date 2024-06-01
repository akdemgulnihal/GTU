import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class GUIVisualization extends JFrame {
    private List<Integer> dataPointsX; // List to store x-axis data points (node numbers)
    private List<Long> dataPointsY; // List to store y-axis data points (time)
    private String plotType; // Type of plot ("line" or "scatter")

    /**
     * This constructor takes two parameters one of which is plotType(Scatter or line )
     * and the other one is List add/remove or search values stored in relevant index
     * 
     * @param plotType
     * @param timeList
     * 
     */
    public GUIVisualization(String plotType,List<Long> timeList) {
        this.plotType = plotType; // Set the plot type
        this.dataPointsX = new ArrayList<>(); // Initialize x-axis data points list
        this.dataPointsY = new ArrayList<>(); // Initialize y-axis data points list

        // Populate data points
        for (int i = 1; i < timeList.size(); i++) {
            dataPointsX.add(i); // Add index to x-axis data points list. X show the node index
            dataPointsY.add(timeList.get(i)); // Add time to y-axis data points list        Y show the time consumption

        }

        setTitle("Performance Graph Visualization"); // Set the title of the window
        setSize(800, 600); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        setLocationRelativeTo(null); // Center the window on the screen
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass's paint method
        drawGraph(g); // Draw the graph
    }

    private void drawGraph(Graphics g) {
        int width = getWidth(); // Get the width of the window
        int height = getHeight(); // Get the height of the window
        int padding = 200; // Padding around the graph
        int labelPadding = 20; // Padding for labels

        Graphics2D g2 = (Graphics2D) g; // Cast Graphics to Graphics2D for better rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing

        // Draw white background for the graph
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // Create hatch marks and grid lines for y axis.
        int numberYDivisions = 4; // Number of divisions for the y-axis
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = width - padding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding);
            int y1 = y0;
            if (dataPointsY.size() > 0) {
                g2.setColor(Color.LIGHT_GRAY); // Set color for grid lines
                g2.drawLine(padding + labelPadding + 1 + labelPadding, y0, x1, y1); // Draw grid line
                g2.setColor(Color.BLACK); // Set color for labels
                String yLabel = ((int) ((getMaxYValue() * ((i * 1.0) / numberYDivisions)) * 100)) / 1000.0 + ""; // Generate y-axis label
                FontMetrics metrics = g2.getFontMetrics(); // Get font metrics for label width
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 1) - 3); // Draw y-axis label
            }
        }

       // Create hatch marks and grid lines for x-axis.
int increment = 100; // Increment for x-axis labels
for (int i = 0; i < dataPointsX.size(); i++) {
    if (dataPointsX.size() > 1) {
        int x0 = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
        int x1 = x0;
        int y0 = height - padding - labelPadding;
        int y1 = y0 - 4;

        // Skip labeling for x = 0
        if (dataPointsX.get(i) != 0 && dataPointsX.get(i) % increment == 0) { // Label at every increment except x = 0
            g2.setColor(Color.LIGHT_GRAY); // Set color for grid lines
            g2.drawLine(x0, height - padding - labelPadding - 1, x1, padding); // Draw grid line
            g2.setColor(Color.BLACK); // Set color for labels
            String xLabel = dataPointsX.get(i) + ""; // Generate x-axis label
            FontMetrics metrics = g2.getFontMetrics(); // Get font metrics for label width
            int labelWidth = metrics.stringWidth(xLabel);
            g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3); // Draw x-axis label
        }
        g2.drawLine(x0, y0, x1, y1); // Draw x-axis hatch mark
    }
}

        // Draw axis lines.
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding); // y-axis
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding); // x-axis

        // Draw the actual graph.
        Stroke oldStroke = g2.getStroke();
        g2.setColor(Color.BLUE); // Set color for the graph
        g2.setStroke(new BasicStroke(2f)); // Set stroke for the graph

        if (plotType.equals("line")) {
            for (int i = 0; i < dataPointsX.size() - 1; i++) {
                int x1 = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y1 = height - padding - labelPadding - (int) ((dataPointsY.get(i) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                int x2 = (i + 1) * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y2 = height - padding - labelPadding - (int) ((dataPointsY.get(i + 1) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                g2.drawLine(x1, y1, x2, y2); // Draw line between data points
            }
        } else if (plotType.equals("scatter")) {
            for (int i = 0; i < dataPointsX.size(); i++) {
                int x = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y = height - padding - labelPadding - (int) ((dataPointsY.get(i) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                g2.fillOval(x - 3, y - 3, 6, 6); // Draw data point as a small circle
            }
        }

        g2.setStroke(oldStroke); // Restore original stroke
    }

    private long getMaxYValue() {
        long max = Long.MIN_VALUE; // Initialize max value to minimum possible value
        for (Long y : dataPointsY) {
            max = Math.max(max, y); // Find maximum y value
        }
        return max; // Return maximum y value
    }

    //public static void main(int nodeNumber, long time) {
    //    SwingUtilities.invokeLater(() -> {
    //        String plotType = "scatter"; // Change to "scatter" for scatter plot
//
    //        GUIVisualization frame = new GUIVisualization(plotType,nodeNumber,time); // Create a new instance of GUIVisualization
    //        frame.setVisible(true); // Make the frame visible
    //    });
    //}
}
