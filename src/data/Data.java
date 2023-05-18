package data;
import java.util.Random;
import utility.ArraySet;

public class Data
{
    private Object data[][];
    private int numberOfExamples;
    private Attribute attributeSet[] ;

    public Data(){

        numberOfExamples = 14;

        attributeSet = new Attribute[5];

        String outLookValues[] = { "overcast", "rain", "sunny" };
        String temperatureValues[] = { "hot", "mild", "cool" };
        String humidityValues[] = { "high", "normal" };
        String windValues[] = { "weak", "strong" };
        String playTennisValues[] = { "yes", "no" };

        attributeSet[0] = new DiscreteAttribute("Outlook", 0, outLookValues);
        attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);
        attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);
        attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);
        attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, playTennisValues);

        data = new Object [14][5];

		data[0][0] = "sunny";
        data[1][0] = "sunny";
        data[2][0] = "overcast";
        data[3][0] = "rain";
        data[4][0] = "rain";
        data[5][0] = "rain";
        data[6][0] = "overcast";
        data[7][0] = "sunny";
        data[8][0] = "sunny";
        data[9][0] = "rain";
        data[10][0] = "sunny";
        data[11][0] = "overcast";
        data[12][0] = "overcast";
        data[13][0] = "rain";

        data[0][1] = "hot";
        data[1][1] = "hot";
        data[2][1] = "hot";
        data[3][1] = "mild";
        data[4][1] = "cool";
        data[5][1] = "cool";
        data[6][1] = "cool";
        data[7][1] = "mild";
        data[8][1] = "cool";
        data[9][1] = "mild";
        data[10][1] = "mild";
        data[11][1] = "mild";
        data[12][1] = "hot";
        data[13][1] = "mild";

        data[0][2] = "high";
        data[1][2] = "high";
        data[2][2] = "high";
        data[3][2] = "high";
        data[4][2] = "normal";
        data[5][2] = "normal";
        data[6][2] = "normal";
        data[7][2] = "high";
        data[8][2] = "normal";
        data[9][2] = "normal";
        data[10][2] = "normal";
        data[11][2] = "high";
        data[12][2] = "normal";
        data[13][2] = "high";

        data[0][3] = "weak";
        data[1][3] = "strong";
        data[2][3] = "weak";
        data[3][3] = "weak";
        data[4][3] = "weak";
        data[5][3] = "strong";
        data[6][3] = "strong";
        data[7][3] = "weak";
        data[8][3] = "weak";
        data[9][3] = "weak";
        data[10][3] = "strong";
        data[11][3] = "strong";
        data[12][3] = "weak";
        data[13][3] = "strong";

        data[0][4] = "no";
        data[1][4] = "no";
        data[2][4] = "yes";
        data[3][4] = "yes";
        data[4][4] = "yes";
        data[5][4] = "no";
        data[6][4] = "yes";
        data[7][4] = "no";
        data[8][4] = "yes";
        data[9][4] = "yes";
        data[10][4] = "yes";
        data[11][4] = "yes";
        data[12][4] = "yes";
        data[13][4] = "no";
    }

    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    public int getNumberOfAttributes() {
        return attributeSet.length;
    }

    Attribute getAttribute(int index) {
        return attributeSet[index];
    }

    public Object getAttributeValue( int exampleIndex,  int attributeIndex) {
        return data[exampleIndex][attributeIndex];
    }

    public String toString() {

		StringBuilder stringBuilded = new StringBuilder();

		for(int i = 0; i < getNumberOfAttributes(); i++)
		{
			stringBuilded.append(attributeSet[i] + ", ");
		}

		stringBuilded.append("\n");

		for(int x = 0; x < numberOfExamples; x++){

			stringBuilded.append(x + ": ");
			for(int y = 0; y < getNumberOfAttributes(); y++){
	
				stringBuilded.append(data[x][y]  + ",");
			}
			stringBuilded.append("\n");
		}

		return stringBuilded.toString();
	}

	public Tuple getItemSet(int index){
			Tuple tuple = new Tuple(getNumberOfAttributes());
			for(int i = 0; i < getNumberOfAttributes(); i++){
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet[i],(String) data[index][i]),i);
			}
			return tuple;
	}

	public int[] sampling(int k) {
        int[] centroidIndexes = new int[k]; //choose k random different centroids in data.
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            boolean found = false;
            int c;
            do {
                found = false;
                c = rand.nextInt(getNumberOfExamples()); // verify that centroid[c] is not equal to a centroide already stored in CentroidIndexes
                for (int j = 0; j < i; j++)
                    if (compare(centroidIndexes[j], c)) {
                        found = true;
                        break;
                    }
            }
            while (found);
            centroidIndexes[i] = c;
        }
        return centroidIndexes;
    }

	private boolean compare(int i, int j){
		for(int x = 0; x < getNumberOfAttributes(); x++){
			if(data[i][x] != data[j][x]){
				return false;
			}
		}

		return true;
	}

	Object computePrototype(ArraySet idList, Attribute attribute){
		return computePrototype(idList, (DiscreteAttribute) attribute);
	}

	//Copiata 1
    String computePrototype(ArraySet idList, DiscreteAttribute attribute) {
        int max = attribute.frequency(this, idList, attribute.getValue(0)), tmp;
        String prototype = attribute.getValue(0);
        for (int i = 1; i < attribute.getNumberOfDistinctValues(); i++) {
            tmp = attribute.frequency(this, idList, attribute.getValue(i));
            if (tmp > max) {
                max = tmp;
                prototype = attribute.getValue(i);
            }
        }
        return prototype;
    }
}