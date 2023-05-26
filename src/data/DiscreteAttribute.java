package data;

import java.util.*;




public class DiscreteAttribute extends Attribute {
    private final TreeSet<String> values= new TreeSet<>();


    DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        Collections.addAll(this.values, values);

    }

    int getNumberOfValues() {
        return values.size();
    }

    public Iterator<String> iterator() {
        return values.iterator();
    }

    int frequency(Data data, Set<Integer> idList, String v) {
        int count = 0;
        for (int i: idList) {
            if (data.getAttributeValue(i,this.getIndex()).equals(v))
                count++;
            }
        return count;
    }
}
