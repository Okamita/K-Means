package data;
public class DiscreteItem extends Item{
    DiscreteItem (DiscreteAttribute attribute, Object value) {
        super(attribute, value);
    }
    double distance(Object a) {
        DiscreteItem b = (DiscreteItem) a;
        if (this.getValue().equals(b.getValue())) { return 0; }
        return 1;
        }
    }

