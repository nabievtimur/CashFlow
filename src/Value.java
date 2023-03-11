import java.util.ArrayList;
import java.util.LinkedList;

public class Value {
    private int iValue;
    private int iMax;
    private Cicle sCicle;
    private Cicle mCicle;
    private Cicle bCicle;

    private LinkedList<Integer> iHistory;

    public Value(int iValue, int duration, int grow, int max) {
        this.iValue = iValue;

        this.bCicle = new Cicle(
                new Interval(
                        duration - (int)(duration / 20),
                        duration + (int)(duration / 20)),
                new Interval(
                        grow - (int)(grow / 40),
                        grow + (int)(grow / 40)));

        duration /= 4;
        grow /= 4;
        this.mCicle = new Cicle(
                new Interval(
                        duration - (int)(duration / 20),
                        duration + (int)(duration / 20)),
                new Interval(
                        grow - (int)(grow / 40),
                        grow + (int)(grow / 40)));

        duration /= 4;
        grow /= 4;
        this.sCicle = new Cicle(
                new Interval(
                        duration - (int)(duration / 20),
                        duration + (int)(duration / 20)),
                new Interval(
                        grow - (int)(grow / 40),
                        grow + (int)(grow / 40)));

        this.iMax = max;
        this.iHistory = new LinkedList<>();
    }

    public int next() {
        float k = this.iMax / 2 - this.iValue;
        k /= this.iMax;
        this.iValue += this.sCicle.next(k) + this.mCicle.next(k) + this.bCicle.next(k);
        //this.iValue += this.iValue * 0.01 * Math.sin((this.getiMax() / 2 - this.iValue) / (this.iMax / 2)) * (this.iValue > this.iMax / 2 ? 1 : -1);
        if (this.iValue <= 0) {
            this.sCicle.setDirection(true);
            this.mCicle.setDirection(true);
            this.bCicle.setDirection(true);
        }
        if (this.iValue >= this.iMax) {
            this.sCicle.setDirection(false);
            this.mCicle.setDirection(false);
            this.bCicle.setDirection(false);
        }
        this.iHistory.addLast(this.iValue);
        while (this.iHistory.size() > 50) {
            this.iHistory.remove(0);
        }

        return this.iValue;
    }

    public int getiValue() { return this.iValue; };
    public int getiMax() { return this.iMax; }

    public LinkedList<Integer> getiHistory() {
        return iHistory;
    }
}
