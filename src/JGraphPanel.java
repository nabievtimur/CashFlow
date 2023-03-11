import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JGraphPanel extends JPanel {
    ArrayList<Value> values;
    int max;

    void paintGraphics(Graphics g, Value val, Color color) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        Point prev = new Point(this.getWidth() / 50, this.getHeight() - (int) ((double) (this.getHeight() * 0.9) / (double) val.getiMax() * (double) val.getiHistory().get(0)));
        g2.setColor(color);
        for (int i = 1; i < 50; i++) {
            Point next = new Point(this.getWidth() / 50 * i, this.getHeight() - (int) ((double) (this.getHeight() * 0.9) / (double) val.getiMax() * (double) val.getiHistory().get(i)));
            g2.drawLine(prev.x, prev.y < 0 ? 0 : prev.y, next.x, next.y < 0 ? 0 : next.y);
            prev = next;
        }
    }

    void updateGraficCard(Graphics g) {
        Color colors[] = new Color[]{
                new Color(0x33cccc),
                new Color(0x0099ff),
                new Color(0x9966ff),
                new Color(0xff00ff),
                new Color(0x00ff00),
                new Color(0xff0000),
                new Color(0x00ff00),
                new Color(0x0ff0ff),
                new Color(0xffff00)};
        int i = 0;
        for (Value value : this.values) {
            paintGraphics(g, value, colors[i]);
            i++;
        }
    }

    public JGraphPanel(ArrayList<Value> values, int max) {
        super();
        this.values = values;
        this.max = max;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        updateGraficCard(g);
    }
}
