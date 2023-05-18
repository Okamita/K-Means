package data;
import utility.ArraySet;

class DiscreteAttribute extends Attribute
{
    private String values[];

    DiscreteAttribute(String name, int index, String values[]) {
        super(name, index);
        this.values = values;
    }

    int getNumberOfDistinctValues() {
        return values.length;
    }

    String getValue(int i){
        return values[i];
    }

    //Pensando ad idList come un array contenente le righe da controllare
	//Il primo for cicla per questo array cosi da controllare l'appartenenza del valore v
	//ad ogni riga, il secondo ciclo invece cicla tra i vari attributi
	int frequency(Data data, ArraySet idList, String v){
		int count = 0;

		for(int i = 0; i < idList.toArray().length ; i++){
			if(data.getAttributeValue(idList.toArray()[i], this.getIndex()) == v){
				count++;
			}
		}

		return count;
	}

}

