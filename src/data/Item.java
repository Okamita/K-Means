package data;
import java.util.*;

public abstract class Item {
    Attribute attribute;
    Object value;

    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    Attribute getAttribute() {
        return attribute;
    }

    Object getValue() {
        return value;
    }

    public String toString() {
        return this.value + " ";
    }

    abstract double distance(Object a);

    public void update(Data data, Set<Integer>  clusteredData) {
        value = data.computePrototype(clusteredData, attribute);
    }
}
