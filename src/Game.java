import java.util.ArrayList;
import java.util.List;

public class Game {
    public final int values = 8;
    private ArrayList<Value> valuesList;

    public Game() {
        this.valuesList = new ArrayList<>();
        for (int i = 0; i < this.values; i++) {
            int max = (1500 + (int)(Math.random() * 1500));
            Value value = new Value(
                    (new Interval(50, 100)).genValueInInterval(),
                    10 + (int)(Math.random() * 40),
                    max / (10 + (int)(Math.random() * 20)),
                    max);
            this.valuesList.add(value);
        }
    }

    void next() {
        for (Value value: this.valuesList) {
            value.next();
        }
    }

    ArrayList<Value> getValues() {
        return this.valuesList;
    }
}
