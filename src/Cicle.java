public class Cicle {
    private boolean bDirection;
    private int iDuration;
    private Interval DurationInterval;
    private Interval GrowInterval;

    public Cicle(Interval DurationInterval, Interval GrowInterval) {
        this.bDirection = (Math.random() > 0.5 ? true : false);
        this.DurationInterval = DurationInterval;
        this.GrowInterval = GrowInterval;
        this.iDuration = this.DurationInterval.genValueInInterval();
    }

    public int sign(float k) {
        return (Math.random() > 0.66 ? (this.bDirection ? -1 : 1) : (this.bDirection ? 1 : -1));
    }

    public int next(float k) {
        int result = GrowInterval.genValueInInterval() * this.sign(k);
        if (result * k < 0) {
            if (Math.random() < Math.abs(k)) {
                result *= -1;
            }
        }
        this.iDuration += 1;
        if (this.iDuration <= 0) {
            this.bDirection = !this.bDirection;
            this.iDuration = this.DurationInterval.genValueInInterval();
        }
        return result;
    }

    public void setDirection(boolean bDirection) {
        this.bDirection = bDirection;
        this.iDuration = this.DurationInterval.genValueInInterval();
    }
}
