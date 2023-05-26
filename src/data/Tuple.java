package data;

import java.util.Set;

public class Tuple {
    private final Item[] tuple;
    
    Tuple(int size) {
        tuple = new Item[size];
    }

    public int getLength() {
        return tuple.length;
    }

    public Item get (int i) {
        return tuple[i];
    }

    void add(Item c, int i) {
        tuple[i] = c;
    }

    public double getDistance(Tuple obj) {
        double d = 0.0;
        for (int i = 0; i < tuple.length; i++) {
            d += tuple[i].distance(obj.get(i));
        }
        return d;
    }

    public double avgDistance(Data data, Set<Integer> clusteredData) {
        double p=0.0, sumD=0.0;
        for (int clusteredDatum : clusteredData) {
            double d = getDistance(data.getItemSet(clusteredDatum));
            sumD += d;
        }
        p = sumD / clusteredData.size();
        return p;
    }
}
