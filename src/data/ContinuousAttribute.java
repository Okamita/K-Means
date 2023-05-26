package data;

class ContinuousAttribute extends Attribute{
    private final double max;
    private final double min;

    ContinuousAttribute(String name, int index, double max, double min) {
        super(name, index);
        this.max = max;
        this.min = min;
    }
    
    double getScaledValue(double v){
        return (v - min) / max - min;
    }
}
