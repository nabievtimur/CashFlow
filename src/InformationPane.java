import javax.swing.*;
import java.awt.*;

public class InformationPane extends JPanel{
    private JLabel title;
    private JLabel value;

    public InformationPane(String title, int value) {
        this.title = new JLabel(title + " - ");
        Font fontTitle = new Font("Helvetica", Font.PLAIN, 50);
        this.title.setFont(fontTitle);
        this.value = new JLabel("[ " + Integer.toString(value) + " ]");
        Font fontvalue = new Font("Helvetica", Font.BOLD, 50);
        this.value.setFont(fontvalue);
        super.setLayout(new FlowLayout());
        super.add(this.title);
        super.add(this.value);
    }

    public void setValue(int value) {
        if (value < 0) {
            value = 0;
        }
        if (value < 50) {
            this.value.setForeground(Color.RED);
        }
        else {
            this.value.setForeground(Color.BLACK);
        }
        this.value.setText("[" + Integer.toString(value) + "]");
    }
}
