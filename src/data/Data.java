package data;

import java.util.*;

import utility.ArraySet;

public class Data {
    // Le visibilità di classi , attributi e metodi devono essere decise dagli studenti	
    List<Example> data;
    private int numberOfExamples;
    List<Attribute> attributeSet=new LinkedList<Attribute>();

    public Data() {

        //data

        TreeSet<Example> tempData = new TreeSet<Example>();
        Example ex0 = new Example();
        Example ex1 = new Example();
        Example ex2 = new Example();
        Example ex3 = new Example();
        Example ex4 = new Example();
        Example ex5 = new Example();
        Example ex6 = new Example();
        Example ex7 = new Example();
        Example ex8 = new Example();
        Example ex9 = new Example();
        Example ex10 = new Example();
        Example ex11 = new Example();
        Example ex12 = new Example();
        Example ex13 = new Example();

        //PRIMI ATTRIBUTI
        ex0.add("sunny");
        ex1.add("sunny");
        ex2.add("overcast");
        ex3.add("rain");
        ex4.add("rain");
        ex5.add("rain");
        ex6.add("overcast");
        ex7.add("sunny");
        ex8.add("sunny");
        ex9.add("rain");
        ex10.add("sunny");
        ex11.add("overcast");
        ex12.add("overcast");
        ex13.add("rain");

        //SECONDI ATTRIBUTI
        ex0.add("hot");
        ex1.add("hot");
        ex2.add("hot");
        ex3.add("mild");
        ex4.add("cool");
        ex5.add("cool");
        ex6.add("cool");
        ex7.add("mild");
        ex8.add("cool");
        ex9.add("mild");
        ex10.add("mild");
        ex11.add("mild");
        ex12.add("hot");
        ex13.add("mild");

        //TERZI ATTRIBUTI
        ex0.add("high");
        ex1.add("high");
        ex2.add("high");
        ex3.add("high");
        ex4.add("normal");
        ex5.add("normal");
        ex6.add("normal");
        ex7.add("high");
        ex8.add("normal");
        ex9.add("normal");
        ex10.add("normal");
        ex11.add("high");
        ex12.add("normal");
        ex13.add("high");

        //QUARTI ATTRIBUTI
        ex0.add("weak");
        ex1.add("strong");
        ex2.add("weak");
        ex3.add("weak");
        ex4.add("weak");
        ex5.add("strong");
        ex6.add("strong");
        ex7.add("weak");
        ex8.add("weak");
        ex9.add("weak");
        ex10.add("strong");
        ex11.add("strong");
        ex12.add("weak");
        ex13.add("strong");

        //QUINTI ATTRIBUTI
        ex0.add("no");
        ex1.add("no");
        ex2.add("yes");
        ex3.add("yes");
        ex4.add("yes");
        ex5.add("no");
        ex6.add("yes");
        ex7.add("no");
        ex8.add("yes");
        ex9.add("yes");
        ex10.add("yes");
        ex11.add("yes");
        ex12.add("yes");
        ex13.add("no");

        tempData.add(ex0);
        tempData.add(ex1);
        tempData.add(ex2);
        tempData.add(ex3);
        tempData.add(ex4);
        tempData.add(ex5);
        tempData.add(ex6);
        tempData.add(ex7);
        tempData.add(ex8);
        tempData.add(ex9);
        tempData.add(ex10);
        tempData.add(ex11);
        tempData.add(ex12);
        tempData.add(ex13);

        data = new ArrayList<Example>(tempData);
        System.out.println(data.size());
        numberOfExamples = data.size();


        String[] outLookValues = new String[3];
        outLookValues[0] = "overcast";
        outLookValues[1] = "rain";
        outLookValues[2] = "sunny";

        String[] temperatureValues = new String[3];
        temperatureValues[0] = "hot";
        temperatureValues[1] = "mild";
        temperatureValues[2] = "cold";

        String[] humidityValues = new String[2];
        humidityValues[0] = "high";
        humidityValues[1] = "normal";

        String[] windValues = new String[2];
        windValues[0] = "weak";
        windValues[1] = "strong";

        String[] playTennisValues = new String[2];
        playTennisValues[0] = "yes";
        playTennisValues[1] = "no";

        attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));
        attributeSet.add(new DiscreteAttribute("Temperature", 1, temperatureValues));
        attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));
        attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));
        attributeSet.add(new DiscreteAttribute("PlayTennis", 4, playTennisValues));

    }

    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    public int getNumberOfAttributes() {
        return attributeSet.size();
    }

    /*Attribute[] getAttributeSchema(){return attributeSet;}*/

    public Object getAttributeValue(int exampleIndex, int attributeIndex) {
        return data.get(exampleIndex).get(attributeIndex);
    }

    public Tuple getItemSet(int index) {
        Tuple tuple = new Tuple(attributeSet.size());
        for (Attribute at : attributeSet)
            tuple.add(new DiscreteItem((DiscreteAttribute) at, (String) data.get(index).get(at.getIndex())), at.getIndex());
        return tuple;
    }

    public int[] sampling(int k) throws OutOfRangeSampleSize {
        if (k <= 0 || k > data.size()) {
            throw new OutOfRangeSampleSize("Devi inserire un numero intero compreso tra 1 e " + data.size());
        }
        int[] centroidIndexes = new int[k];
        //choose k random different centroids in data.
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            boolean found = false;
            int c;
            do {
                found = false;
                c = rand.nextInt(getNumberOfExamples());
                for (int j = 0; j < i; j++)
                    if (compare(centroidIndexes[j], c)) {
                        found = true;
                        break;
                    }
            } while (found);
            centroidIndexes[i] = c;
        }
        return centroidIndexes;
    }

    private boolean compare(int i, int j) {
        for (Attribute at : attributeSet) {
            if (!data.get(i).get(at.getIndex()).equals(data.get(j).get(at.getIndex())))
                return false;
        }
        return true;
    }

    Object computePrototype(Set<Integer> idList, Attribute attribute) {
        return computePrototype(idList, (DiscreteAttribute) attribute);
    }

    private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute)
    {
        Iterator<String> it=attribute.iterator();
        String first=it.next();
        int max=attribute.frequency(this,idList,first);
        int tmp;
        String prototype=first;
        String stringTemp;
        while(it.hasNext())
        {
            stringTemp=it.next();
            tmp=attribute.frequency(this,idList,stringTemp);
            if (tmp>max)
            {
                max=tmp;
                prototype=stringTemp;
            }
        }
        return prototype;
    }

    private int countDistinctTuples() {
        int count = 0;
        for (int i = 0; i < numberOfExamples; i++) {
            boolean found = false;
            for (int j = 0; j < i; j++)
                if (compare(i, j)) {
                    found = true;
                    break;
                }
            if (!found)
                count++;
        }
        return count;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int i=0;
        for (Attribute at:attributeSet) {
            s.append(at.getName()).append(i == attributeSet.size() - 1 ? "\n" : ",");
            i++;
        }
        i=0;
        for (Example ex:data)
        {
            s.append(i).append(":").append(ex.toString()).append("\n");
            i++;
        }

        return s.toString();
    }

    static class Example implements Comparable<Example> {
        private final List<Object> example = new ArrayList<Object>();

        private void add(Object o) {
            example.add(o);
        }

        private Object get(int i) {
            return example.get(i);
        }

        public int compareTo(Example ex) {
            int result = 0;
            Iterator<Object> questo = this.example.iterator();
            Iterator<Object> parametro = ex.example.iterator();
            while (questo.hasNext() && parametro.hasNext()) {
                Object o1 = questo.next();
                Object o2 = parametro.next();
                if (!o1.equals(o2)) {
                    result = ((Comparable) o1).compareTo(o2);
                    break;
                }
            }
            return result;
        }

        public String toString()
        {
            StringBuilder s= new StringBuilder();
            for (Object o:example)
                s.append(o).append(",");
            return s.toString();
        }
    }
}


    