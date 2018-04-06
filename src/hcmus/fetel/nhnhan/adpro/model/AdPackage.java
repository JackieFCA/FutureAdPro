package hcmus.fetel.nhnhan.adpro.model;

public class AdPackage {
    private int duration;
    private double value;

    public AdPackage(int duration, double value) {
        super();
        this.duration = duration;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "AdPackage [duration=" + duration + ", value=" + value + "]";
    }

}
