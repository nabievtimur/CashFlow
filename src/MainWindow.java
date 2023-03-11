import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeListener;
import java.nio.charset.CharsetEncoder;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;

public class MainWindow extends JFrame {
    private JGraphPanel Graphs;
    private JPanel Controls;
    private Game game;

    private JButton update;
    private ArrayList<InformationPane> Values;

    public final String VALUTSTRING = "Валюта";
    public final String VALUTPAPER = "Акции";
    public final String VALUTUNMOVABLE = "Недвижимость";

    public final int BOUND = 30;

    public String getRandomName() {
        Character[] alf = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z'};

        String ret = "";
        int size = 2 + (int) (Math.random() * 2);
        for (int i = 0; i < size; i++) {
            ret += alf[(int) (Math.random() * alf.length)];
        }
        return ret;
    }

    public MainWindow() {
        super("CashFlow");
        this.setBounds(0, 0, 2500, 1400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.game = new Game();
        for (int i = 0; i < 500; i += 1) {
            this.game.next();
        }

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        this.Controls = new JPanel();
        GridLayout boxLayout = new GridLayout(2, 2, 10, 10);
        this.Controls.setLayout(boxLayout);

        JPanel containerGraphics = new JPanel();
        BoxLayout containerGraphicsLayout = new BoxLayout(containerGraphics, BoxLayout.Y_AXIS);
        containerGraphics.setLayout(containerGraphicsLayout);

        JPanel containerGraphicsLabel = new JPanel();
        containerGraphicsLabel.setLayout(new FlowLayout());

        Dimension paneCerdSize = new Dimension(1000, 500);

        // Создаем панель с менеджером расположения CardLayout
        JPanel MyCards = new JPanel(new CardLayout());
        MyCards.setBounds(20, 20, paneCerdSize.width - 40, paneCerdSize.height - 40);
        MyCards.setBorder(new LineBorder(Color.BLACK));
        //MyCards.setBounds(100, 100, 500, 500);

        containerGraphics.add(containerGraphicsLabel);

        // Создание "cards".
        JGraphPanel card1 = new JGraphPanel(this.game.getValues(), 3000);
        card1.setSize(MyCards.getWidth(), MyCards.getHeight());
        card1.setBounds(20, 20, MyCards.getWidth() - 40, MyCards.getHeight() - 40);
        card1.setBorder(new LineBorder(Color.BLUE));

        //JGraphPanel card2 = new JGraphPanel(new Value[]{ game.getRUBLE(), game.getEURO(), game.getDOLLAR() }, 600);
        //card2.setSize(MyCards.getWidth(), MyCards.getHeight());
        //card2.setBounds(20, 20, MyCards.getWidth() - 40, MyCards.getHeight() - 40 );
        //card2.setBorder(new LineBorder(Color.RED));

        //JGraphPanel card3 = new JGraphPanel(new Value[]{ game.getHOUSES(), game.getLANDS() }, 20000);
        //card3.setSize(MyCards.getWidth(), MyCards.getHeight());
        //card3.setBounds(20, 20, MyCards.getWidth() - 40, MyCards.getHeight() - 40 );
        //card3.setBorder(new LineBorder(Color.CYAN));

        JComboBox<String> combobox = new JComboBox<String>(
                new String[]{VALUTSTRING, VALUTPAPER, VALUTUNMOVABLE});
        combobox.setEditable(false);
        combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = combobox.getSelectedItem().toString();
                if (item == VALUTSTRING) {
                    ((CardLayout) MyCards.getLayout()).first(MyCards);
                    card1.repaint();
                }
                //if (item == VALUTPAPER)
                //{
                //    ((CardLayout)MyCards.getLayout()).first(MyCards);
                //    ((CardLayout)MyCards.getLayout()).next(MyCards);
                //    card2.repaint();
                //}
                //if (item == VALUTUNMOVABLE)
                //{
                //    ((CardLayout)MyCards.getLayout()).last(MyCards);
                //    card3.repaint();
                //}
            }
        });

        this.update = new JButton("Событие на рынке");
        this.update.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        this.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
                card1.repaint();
                //card2.repaint();
                //card3.repaint();
            }
        });
        containerGraphicsLabel.add(this.update);

        // Помещаем JComboBox в JPanel для наглядности.
        containerGraphicsLabel.add(combobox);

        // Добавляем вкладки
        MyCards.add(card1, VALUTSTRING);
        //MyCards.add(card2, VALUTPAPER);
        //MyCards.add(card3, VALUTUNMOVABLE);

        containerGraphics.add(MyCards);

        this.Controls.add(containerGraphics);

        JPanel containerPaper = new JPanel();
        BoxLayout layoutPaper = new BoxLayout(containerPaper, BoxLayout.Y_AXIS);
        containerPaper.setLayout(layoutPaper);

        this.Values = new ArrayList<>();
        for (Value value : this.game.getValues()) {
            InformationPane pane = new InformationPane(this.getRandomName(), value.getiValue());
            containerPaper.add(pane);
            this.Values.add(pane);
        }

        this.Controls.add(containerPaper);

        container.add(this.Controls);
    }

    void next() {
        this.game.next();
        Iterator<Value> itValue = this.game.getValues().iterator();
        for (InformationPane pane : this.Values) {
            pane.setValue(itValue.next().getiValue());
        }
    }
}
