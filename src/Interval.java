public class Interval {
    int iBegin;
    int iEnd;

    public Interval(int iBegin, int iEnd) {
        this.iBegin = iBegin;
        this.iEnd = iEnd;
    }

    public int genValueInInterval() {
        return (int)(Math.random() * (this.iEnd - this.iBegin) + this.iBegin);
    }

    public int calcValeInGraph(int value, int start, int end) {
        if ((value < Settings.iValueMin) || (value > Settings.iValueMax)) {
            return 0;
        }
        int result = Settings.iValueMax - Settings.iValueMin;
        return result;
    }
}
